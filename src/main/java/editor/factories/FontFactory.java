package editor.factories;

import editor.enums.FontType;
import editor.metadata.Font;

import java.util.HashMap;

public class FontFactory {
    private final HashMap<FontType, Font> fontMap;

    public FontFactory() {
        fontMap = new HashMap<>();
    }

    public Font getFont(FontType fontType) {
        if (!fontMap.containsKey(fontType)) {
            fontMap.put(fontType, new Font(fontType));
        }
        return fontMap.get(fontType);
    }
}
