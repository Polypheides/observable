package observable.util;

import net.minecraft.network.chat.ClickEvent;
import java.net.URI;

public class ClickEventUtils {
    public static ClickEvent createOpenUrl(String url) {
        return new ClickEvent.OpenUrl(URI.create(url));
    }

    public static ClickEvent createRunCommand(String command) {
        return new ClickEvent.RunCommand(command);
    }

    public static ClickEvent openFile(String path) {
        return new ClickEvent.OpenFile(path);
    }
}
