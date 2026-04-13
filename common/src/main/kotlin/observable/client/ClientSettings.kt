package observable.client

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import observable.Observable
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
}

class ClientSettingsGui : Screen(Component.translatable("screen.observable.client_settings")) {
    val fields =
        listOf("maxBlockDist", "maxEntityDist", "maxEntityCount", "normalize").map {
            Component.translatable("text.observable.$it")
        }

    override fun init() {
        super.init()

        val leftX = width / 2 - 110
        val rightX = width / 2 + 50
        val widgetW = 60
        val startY = height / 2 - 60
        
        entry(startY, ClientSettings::maxBlockDist, rightX, widgetW)
        entry(startY + 25, ClientSettings::maxEntityDist, rightX, widgetW)
        entry(startY + 50, ClientSettings::maxEntityCount, rightX, widgetW)
        addRenderableWidget(
            BetterCheckbox(
                rightX,
                startY + 75,
                20,
                20,
                Component.literal(""),
                ClientSettings.normalized
            ) {
                ClientSettings.normalized = it
            }
        )

        // Back Button - Centered at the bottom
        button(
            width / 2 - 100,
            startY + 110,
            200,
            20,
            Component.translatable("gui.back")
        ) {
            Minecraft.getInstance().setScreen(Observable.PROFILE_SCREEN)
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
        val startY = height / 2 - 60
        
        // Title
        graphics.text(font, title, width / 2 - font.width(title) / 2, startY - 30, 0xFFFFFFFF.toInt(), true)

        var fieldIdx = 0
        for (child in this.children()) {
            if (fieldIdx < fields.size && child is AbstractWidget && (child is EditBox || (child is net.minecraft.client.gui.components.Checkbox && child.x == rightX && child.y == startY + 75))) {
                val label = fields[fieldIdx]
                graphics.text(
                    font,
                    label.visualOrderText,
                    rightX - 10 - font.width(label),
                    child.y + (child.height - 8) / 2,
                    0xFFFFFFFF.toInt(),
                    true
                )
                fieldIdx++
            }
        }
    }
}
