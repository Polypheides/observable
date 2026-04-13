package observable.client

import net.minecraft.util.Util
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.screens.ConfirmLinkScreen
import net.minecraft.client.gui.screens.Screen
import observable.Observable
import observable.client.ObservableClient
import net.minecraft.network.chat.Component
import observable.net.C2SPacket
import observable.net.*
import kotlin.math.roundToInt

class ProfileScreen : Screen(Component.translatable("screen.observable.profile")) {

    sealed class Action {
        companion object {
            val DEFAULT = NewProfile(30)
            val UNAVAILABLE = ObservableStatus("text.observable.unavailable")
            val NO_PERMISSIONS = ObservableStatus("text.observable.no_permissions")
        }

        data class NewProfile(var duration: Int) : Action()

        data class TPSProfilerRunning(val endTime: Long) : Action()

        data object TPSProfilerCompleted : Action()

        data class ObservableStatus(val text: String) : Action()

        data class Custom(val text: String) : Action()

        val statusMsg
            get() =
                when (this) {
                    is NewProfile -> "Duration (scroll): $duration seconds"
                    is TPSProfilerRunning ->
                        "Running for another %.1f seconds"
                            .format(
                                ((endTime - System.currentTimeMillis()).toDouble() / 1e3).coerceAtLeast(
                                    0.0
                                )
                            )
                    is TPSProfilerCompleted -> "Profiling finished, please wait..."
                    is ObservableStatus -> Component.translatable(text).string
                    is Custom -> text
                }
    }

    var action: Action = Action.UNAVAILABLE
        set(value) {
            field = value
            startBtn?.active = (value is Action.NewProfile)
        }
    var startBtn: Button? = null
    var sample = false

    private fun openLink(dest: String) {
        val mc = Minecraft.getInstance()
        mc.setScreen(
            ConfirmLinkScreen(
                { bl: Boolean ->
                    if (bl) {
                        Util.getPlatform().openUri(java.net.URI(dest))
                    }
                    mc.setScreen(this)
                },
                dest,
                true
            )
        )
    }

    private fun button(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        component: Component,
        onPress: () -> Unit
    ): Button {
        val btn = Button.builder(component) { onPress() }.bounds(x, y, width, height).build()
        return addRenderableWidget(btn)
    }

    override fun init() {
        super.init()

        val startBtn =
            button(
                0,
                height / 2 - 48,
                100,
                20,
                Component.translatable("text.observable.profile_tps")
            ) {
                val duration = (action as Action.NewProfile).duration
                Observable.CHANNEL.sendToServer(C2SPacket.InitProfile(duration, sample))
            }
        startBtn.active = action is Action.NewProfile
        startBtn.x = width / 2 - startBtn.width - 4

        val settingsBtn =
            button(
                width / 2 + 4,
                startBtn.y,
                startBtn.width,
                startBtn.height,
                Component.translatable("screen.observable.client_settings")
            ) {
                Minecraft.getInstance().setScreen(ClientSettingsGui())
            }

        val samplerBtn =
            addRenderableWidget(
                BetterCheckbox(
                    startBtn.x,
                    startBtn.y + startBtn.height + 4,
                    settingsBtn.x + settingsBtn.width - startBtn.x,
                    20,
                    Component.translatable("text.observable.sampler"),
                    sample
                ) {
                    sample = it
                }
            )

        val overlayBtn =
            addRenderableWidget(
                BetterCheckbox(
                    samplerBtn.x,
                    samplerBtn.y + samplerBtn.height + 4,
                    samplerBtn.width,
                    20,
                    Component.translatable("text.observable.overlay"),
                    ObservableClient.isOverlayEnabled
                ) {
                    if (it) {
                        synchronized(Overlay) { Overlay.load() }
                    }
                    ObservableClient.isOverlayEnabled = it
                }
            )

        // Exclusive Render Mode Toggles (Stacked Vertically for better spacing)
        val cubesBtn = addRenderableWidget(
            BetterCheckbox(
                overlayBtn.x,
                overlayBtn.y + overlayBtn.height + 4,
                overlayBtn.width,
                20,
                Component.translatable("text.observable.render_mode.cubes"),
                ClientSettings.renderMode == RenderMode.CUBES
            ) {
                if (it) {
                    ClientSettings.renderMode = RenderMode.CUBES
                    rebuildWidgets()
                }
            }
        )

        val wireframeBtn = addRenderableWidget(
            BetterCheckbox(
                cubesBtn.x,
                cubesBtn.y + cubesBtn.height + 4,
                cubesBtn.width,
                20,
                Component.translatable("text.observable.render_mode.wireframe"),
                ClientSettings.renderMode == RenderMode.WIREFRAME
            ) {
                if (it) {
                    ClientSettings.renderMode = RenderMode.WIREFRAME
                    rebuildWidgets()
                }
            }
        )

        val modeBottomY = wireframeBtn.y + wireframeBtn.height + 4
        val learnBtn =
            button(
                startBtn.x,
                modeBottomY,
                (settingsBtn.x + settingsBtn.width - samplerBtn.x) / 3 - 2,
                20,
                Component.translatable("text.observable.docs")
            ) {
                openLink("https://github.com/tasgon/observable/wiki")
            }
        
        val smallWidth = learnBtn.width
        val helpBtn =
            button(
                learnBtn.x + learnBtn.width + 4,
                learnBtn.y,
                smallWidth,
                20,
                Component.translatable("text.observable.discord")
            ) {
                openLink("https://discord.gg/sfPbb3b5tF")
            }
        val donateBtn =
            button(
                helpBtn.x + helpBtn.width + 4,
                helpBtn.y,
                smallWidth,
                20,
                Component.translatable("text.observable.donate")
            ) {
                openLink("https://github.com/tasgon/observable/wiki/Support-this-project")
            }

        this.startBtn = startBtn
        Observable.CHANNEL.sendToServer(C2SPacket.RequestAvailability)
    }

    override fun isPauseScreen() = false

    override fun extractRenderState(graphics: GuiGraphicsExtractor, i: Int, j: Int, f: Float) {
        super.extractRenderState(graphics, i, j, f)

        val msg = action.statusMsg
        val textX = width / 2 - this.font.width(msg) / 2
        val textY = startBtn!!.y - this.font.lineHeight - 6
        
        graphics.text(this.font, msg, textX, textY, 0xFFFFFFFF.toInt(), true)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, scrollX: Double, scrollY: Double): Boolean {
        (action as? Action.NewProfile)?.let {
            it.duration += (scrollY.roundToInt() * 5)
            it.duration = it.duration.coerceIn(5, 60)
            
            // Persist the duration
            ClientConfig.data.profileDuration = it.duration
            ClientConfig.save()
            
            return true
        }

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY)
    }
}
