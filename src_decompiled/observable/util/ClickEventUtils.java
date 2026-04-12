/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.ClickEvent
 *  net.minecraft.network.chat.ClickEvent$OpenFile
 *  net.minecraft.network.chat.ClickEvent$OpenUrl
 *  net.minecraft.network.chat.ClickEvent$RunCommand
 *  org.jspecify.annotations.NonNull
 */
package observable.util;

import java.net.URI;
import java.util.Objects;
import net.minecraft.network.chat.ClickEvent;
import org.jspecify.annotations.NonNull;

public class ClickEventUtils {
    public static ClickEvent createOpenUrl(@NonNull String url) {
        return new ClickEvent.OpenUrl(Objects.requireNonNull(URI.create(Objects.requireNonNull(url))));
    }

    public static ClickEvent createRunCommand(@NonNull String command) {
        return new ClickEvent.RunCommand(Objects.requireNonNull(command));
    }

    public static ClickEvent openFile(@NonNull String path) {
        return new ClickEvent.OpenFile(Objects.requireNonNull(path));
    }
}

