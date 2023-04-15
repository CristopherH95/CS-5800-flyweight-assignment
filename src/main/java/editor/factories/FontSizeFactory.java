package editor.factories;

import editor.metadata.FontSize;

import java.util.HashMap;

public class FontSizeFactory {
    private final HashMap<Float, FontSize> fontSizeMap;

    public FontSizeFactory() {
        fontSizeMap = new HashMap<>();
    }

    public FontSize getFontSize(float size) {
        if (!fontSizeMap.containsKey(size)) {
            fontSizeMap.put(size, new FontSize(size));
        }
        return fontSizeMap.get(size);
    }
}
