package observable.client

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen
import net.minecraft.network.chat.Component
import observable.client.ObservableClient
import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.ChatFormatting
import net.minecraft.client.KeyMapping
import java.lang.NumberFormatException
import kotlin.reflect.KMutableProperty0

object ClientSettings {
    var minRate: Int = 0
        set(v) {
            field = v
            Overlay.loadSync()
        }

    var maxBlockDist: Int = 128
        @Synchronized set

    var maxEntityDist: Int = 2048
        @Synchronized set

    var normalized = true
        set(v) {
            field = v
            Overlay.loadSync()
        }

    var maxBlockCount: Int = 2000
    var maxEntityCount: Int = 2000
    
    var renderMode: RenderMode
        get() = ClientConfig.data.renderMode
        set(v) {
            ClientConfig.data.renderMode = v
            ClientConfig.save()
        }

    var silenceJoinMessage: Boolean
        get() = ClientConfig.data.silenceJoinMessage
        set(v) {
            ClientConfig.data.silenceJoinMessage = v
            ClientConfig.save()
        }
}

class ClientSettingsGui : Screen(Component.translatable("screen.observable.client_settings")) {
    val fields =
        listOf("maxBlockDist", "maxEntityDist", "maxEntityCount", "normalize", "silence_join").map {
            Component.translatable("text.observable.$it")
        }
    
    private var listeningKey: KeyMapping? = null

    override fun init() {
        super.init()

        val leftX = width / 2 - 110
        val rightX = width / 2 + 50
        val widgetW = 60
        val startY = height / 2 - 100
        
        entry(startY, ClientSettings::maxBlockDist, rightX, widgetW)
        entry(startY + 20, ClientSettings::maxEntityDist, rightX, widgetW)
        entry(startY + 40, ClientSettings::maxEntityCount, rightX, widgetW)
        addRenderableWidget(
            BetterCheckbox(
                rightX,
                startY + 60,
                20,
                20,
                Component.literal(""),
                ClientSettings.normalized
            ) {
                ClientSettings.normalized = it
            }
        )
        addRenderableWidget(
            BetterCheckbox(
                rightX,
                startY + 80,
                20,
                20,
                Component.literal(""),
                ClientSettings.silenceJoinMessage
            ) {
                ClientSettings.silenceJoinMessage = it
            }
        )

        // Keybinds Section
        val keyY = startY + 125
        keyButton(keyY, ObservableClient.KEY_OPEN_SETTINGS)
        keyButton(keyY + 24, ObservableClient.KEY_TOGGLE_OVERLAY)
        keyButton(keyY + 48, ObservableClient.KEY_CYCLE_RENDER_MODE)

        // Footer Buttons - Vertical with 24px gap
        button(
            width / 2 - 100,
            keyY + 72,
            200,
            20,
            Component.translatable("controls.keybinds")
        ) {
            Minecraft.getInstance().setScreen(KeyBindsScreen(this, Minecraft.getInstance().options))
        }

        button(
            width / 2 - 100,
            keyY + 96,
            200,
            20,
            Component.translatable("gui.back")
        ) {
            Minecraft.getInstance().setScreen(ObservableClient.PROFILE_SCREEN)
        }
    }

    private fun button(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        component: Component,
        onPress: () -> Unit
    ) {
        val btn = net.minecraft.client.gui.components.Button.builder(component) { onPress() }.bounds(x, y, width, height).build()
        addRenderableWidget(btn)
    }

    private fun keyButton(y: Int, mapping: KeyMapping) {
        // Main Key Button
        val keyBtn = net.minecraft.client.gui.components.Button.builder(getKeyMessage(mapping)) {
            listeningKey = mapping
            rebuildWidgets()
        }.bounds(width / 2 - 20, y, 75, 20).build()
        addRenderableWidget(keyBtn)

        // Reset Button
        val resetBtn = net.minecraft.client.gui.components.Button.builder(Component.translatable("controls.reset")) {
            mapping.setKey(mapping.defaultKey)
            KeyMapping.resetMapping()
            Minecraft.getInstance().options.save()
            rebuildWidgets()
        }.bounds(width / 2 + 59, y, 50, 20).build()
        
        resetBtn.active = !mapping.isDefault
        addRenderableWidget(resetBtn)
    }

    private fun getKeyMessage(mapping: KeyMapping): Component {
        if (listeningKey == mapping) return Component.literal("> ??? <").withStyle(ChatFormatting.YELLOW)
        val msg = mapping.translatedKeyMessage
        if (mapping.isUnbound || msg.string.endsWith(".0") || msg.string.contains("unknown", ignoreCase = true)) {
            return com.mojang.blaze3d.platform.InputConstants.UNKNOWN.displayName
        }
        return msg
    }

    override fun keyPressed(event: net.minecraft.client.input.KeyEvent): Boolean {
        listeningKey?.let { mapping ->
            val key = InputConstants.getKey(event)
            mapping.setKey(key)
            KeyMapping.resetMapping()
            Minecraft.getInstance().options.save()
            listeningKey = null
            rebuildWidgets()
            return true
        }
        return super.keyPressed(event)
    }

    override fun mouseClicked(event: net.minecraft.client.input.MouseButtonEvent, doubleClick: Boolean): Boolean {
        listeningKey?.let { mapping ->
            val key = InputConstants.Type.MOUSE.getOrCreate(event.button())
            mapping.setKey(key)
            KeyMapping.resetMapping()
            Minecraft.getInstance().options.save()
            listeningKey = null
            rebuildWidgets()
            return true
        }
        return super.mouseClicked(event, doubleClick)
    }

    private fun entry(y: Int, prop: KMutableProperty0<Int>, x: Int, w: Int) {
        val box =
            EditBox(
                Minecraft.getInstance().font,
                x,
                y,
                w,
                20,
                Component.literal("")
            )
        box.value = prop.get().toString()

        box.setResponder {
            try {
                prop.set(Integer.parseInt(it))
            } catch (e: NumberFormatException) {
            }
        }
        addRenderableWidget(box)
    }

    override fun extractRenderState(graphics: GuiGraphicsExtractor, i: Int, j: Int, f: Float) {
        super.extractRenderState(graphics, i, j, f)

        val font = Minecraft.getInstance().font
        val leftX = width / 2 - 110
        val rightX = width / 2 + 50
        val startY = height / 2 - 100
        
        // Title
        graphics.text(font, title, width / 2 - font.width(title) / 2, startY - 22, -1, true)
        graphics.fill(width / 2 - 110, startY - 12, width / 2 + 110, startY - 11, 0x40FFFFFF)

       

        // Settings Labels
        val labelX = rightX - 10
        
        fun drawLabel(key: String, y: Int) {
            val component = Component.translatable(key)
            graphics.text(font, component, labelX - font.width(component), y + 6, -1, true)
        }

        drawLabel("text.observable.maxBlockDist", startY)
        drawLabel("text.observable.maxEntityDist", startY + 20)
        drawLabel("text.observable.maxEntityCount", startY + 40)
        drawLabel("text.observable.normalize", startY + 60)
        drawLabel("text.observable.silence_join", startY + 80)

        // Keybinds Rendering
        val keyY = startY + 125
        val header = Component.translatable("controls.keybinds.title")
        graphics.text(font, header, width / 2 - font.width(header) / 2, keyY - 18, -1, true)
        graphics.fill(width / 2 - 110, keyY - 8, width / 2 + 110, keyY - 7, 0x40FFFFFF)
        
        val keyLabelX = width / 2 - 25
        
        val settingsLabel = Component.translatable("key.observable.settings")
        graphics.text(font, settingsLabel, keyLabelX - font.width(settingsLabel), keyY + 6, -1, true)

        val overlayLabel = Component.translatable("key.observable.overlay")
        graphics.text(font, overlayLabel, keyLabelX - font.width(overlayLabel), keyY + 30, -1, true)

        val cycleLabel = Component.translatable("key.observable.cycle_render_mode")
        graphics.text(font, cycleLabel, keyLabelX - font.width(cycleLabel), keyY + 54, -1, true)
    }
}
