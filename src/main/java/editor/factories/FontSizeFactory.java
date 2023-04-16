package editor.factories;

import editor.metadata.FontSize;

import java.util.HashMap;

public class FontSizeFactory {
    private static final HashMap<Float, FontSize> fontSizeMap = new HashMap<>();

    public static FontSize getFontSize(float size) {
        if (!fontSizeMap.containsKey(size)) {
            fontSizeMap.put(size, new FontSize(size));
        }
        return fontSizeMap.get(size);
    }
}
