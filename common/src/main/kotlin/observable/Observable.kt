package observable

import net.minecraft.resources.Identifier
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import observable.net.Channel
import observable.net.C2SPacket
import observable.net.S2CPacket
import observable.net.*
import observable.server.ObservableCommands
import observable.server.Profiler
import observable.server.Tracing
import observable.server.ProfilingData
import observable.util.Constants
import observable.util.Platform
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Observable {
    const val MOD_ID = "observable"
    val LOGGER: Logger = LogManager.getLogger(MOD_ID)
    val CHANNEL by lazy { Platform.INSTANCE.createChannel(Identifier.fromNamespaceAndPath(MOD_ID, "main")) }
    val PROFILER = Profiler()
    var SERVER_INSTANCE: MinecraftServer? = null
    var RESULTS: ProfilingData? = null
    
    fun clearResults() {
        RESULTS = null
    }

    fun hasPermission(player: ServerPlayer): Boolean {
        val server = player.level().server
        return server.playerList.isOp(player.nameAndId())
    }

    fun onInitialize() {
        LOGGER.info("Starting Observable Common-side initialization...")
        
        Constants.load()
        Tracing.init()
        PROFILER.init()
        observable.server.Remapper.init()

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
            player?.let {
                CHANNEL.sendToPlayer(
                    it,
                    if (hasPermission(it)) S2CPacket.Availability.Available else S2CPacket.Availability.NoPermissions
                )
            }
        }
    }
}
