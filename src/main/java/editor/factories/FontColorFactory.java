package editor.factories;

import editor.enums.Color;
import editor.metadata.FontColor;

import java.util.HashMap;

public class FontColorFactory {
    private static final HashMap<Color, FontColor> colorMap = new HashMap<>();

    public static FontColor getFontColor(Color color) {
        if (!colorMap.containsKey(color)) {
            colorMap.put(color, new FontColor(color));
        }
        return colorMap.get(color);
    }
}
