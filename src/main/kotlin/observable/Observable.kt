package observable

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.client.KeyMapping
import net.minecraft.resources.Identifier
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import observable.client.ProfileScreen
import observable.net.BetterChannel
import observable.net.C2SPacket
import observable.net.S2CPacket
import observable.server.ObservableCommands
import observable.server.Profiler
import observable.server.Tracing
import observable.server.ProfilingData
import observable.util.Constants
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import net.minecraft.client.renderer.MultiBufferSource
import org.joml.Matrix4fStack
import org.joml.Matrix4fc
import net.minecraft.world.phys.Vec3

interface Bridge {
    fun clear()
}

interface WorldRenderer {
    fun render(stack: Matrix4fStack, bufferSource: MultiBufferSource, camera: Vec3, modelViewMatrix: Matrix4fc, delta: net.minecraft.client.DeltaTracker, collector: net.minecraft.client.renderer.SubmitNodeCollector, cameraState: net.minecraft.client.renderer.state.level.CameraRenderState)
}

object Observable : ModInitializer {
    const val MOD_ID = "observable"
    val LOGGER: Logger = LogManager.getLogger(MOD_ID)
    val CHANNEL = BetterChannel(Identifier.fromNamespaceAndPath(MOD_ID, "main"))
    val PROFILER = Profiler()
    var SERVER_INSTANCE: MinecraftServer? = null
    var RESULTS: ProfilingData? = null
    val PROFILE_SCREEN by lazy { ProfileScreen() }
    var isOverlayEnabled = true

    @JvmField val KEY_OPEN_SETTINGS = net.minecraft.client.KeyMapping("key.observable.settings", 85, observable.client.ProfilerBridge.CATEGORY)
    @JvmField val KEY_TOGGLE_OVERLAY = net.minecraft.client.KeyMapping("key.observable.overlay", 82, observable.client.ProfilerBridge.CATEGORY)

    fun clearResults() {
        RESULTS = null
    }

    fun openProfileScreen() {
        observable.client.ProfilerBridge.openProfileScreen()
    }

    fun clientInit() {
        LOGGER.info("Starting Observable Client-side initialization...")
        observable.client.Overlay.init()
        observable.client.ProfilerBridge.setBridge(observable.client.Overlay)
        observable.client.ProfilerBridge.setWorldRenderer(observable.client.Overlay)
        observable.client.ProfilerBridge.setHudRenderer(observable.client.Overlay::renderHud)
        
        net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper.registerKeyMapping(KEY_OPEN_SETTINGS)
        net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper.registerKeyMapping(KEY_TOGGLE_OVERLAY)
        observable.client.ProfilerBridge.setScreenOpener {
            net.minecraft.client.Minecraft.getInstance().setScreen(PROFILE_SCREEN)
        }

        CHANNEL.register { t: S2CPacket.ProfilingStarted, _ ->
            PROFILE_SCREEN.action = ProfileScreen.Action.TPSProfilerRunning(t.endMillis)
        }

        CHANNEL.register { _: S2CPacket.ProfilingCompleted, _ ->
            PROFILE_SCREEN.action = ProfileScreen.Action.TPSProfilerCompleted
        }

        CHANNEL.register { t: S2CPacket.ProfilingResult, _ ->
            RESULTS = t.data
            PROFILE_SCREEN.action = ProfileScreen.Action.NewProfile(30)
            observable.client.Overlay.loadSync()
        }

        CHANNEL.register { t: S2CPacket.Availability, _ ->
            PROFILE_SCREEN.action = when (t) {
                S2CPacket.Availability.Available -> ProfileScreen.Action.NewProfile(30)
                S2CPacket.Availability.NoPermissions -> ProfileScreen.Action.NO_PERMISSIONS
            }
        }

        LOGGER.info("Observable Client-side initialization complete.")
    }

    fun hasPermission(player: ServerPlayer): Boolean {
        val server = (player.level() as ServerLevel).server
        return server.playerList.isOp(player.nameAndId())
    }

    override fun onInitialize() {
        LOGGER.info("Starting Observable Common-side initialization...")
        net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.SERVER_STARTED.register { server ->
            SERVER_INSTANCE = server
        }
        net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.SERVER_STOPPING.register {
            SERVER_INSTANCE = null
        }

        Constants.load()
        Tracing.init()
        PROFILER.init()
        observable.server.Remapper.init()

        ObservableCommands.register()

        CHANNEL.register { t: C2SPacket.InitProfile, player ->
            if (player == null) return@register
            if (!hasPermission(player)) {
                LOGGER.info("${player.name.string} lacks permissions to start profiling")
                return@register
            }
            if (PROFILER.notProcessing) {
                PROFILER.runWithDuration(player, t.duration, t.sample)
            }
            LOGGER.info("${player.gameProfile.name} started profiler for ${t.duration} s")
        }

        CHANNEL.register { _: C2SPacket.RequestAvailability, player ->
            (player as? ServerPlayer)?.let {
                CHANNEL.sendToPlayer(
                    it,
                    if (hasPermission(it)) S2CPacket.Availability.Available else S2CPacket.Availability.NoPermissions
                )
            }
        }
    }
}
