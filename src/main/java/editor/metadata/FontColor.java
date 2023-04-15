package editor.metadata;

import editor.abstractions.MetaData;
import editor.enums.Color;

public class FontColor extends MetaData {
    private final Color color;

    public FontColor(Color color) {
        this.color = color;
    }

    @Override
    public String getLabel() {
        return String.format("Color: %s", color.toString());
    }

    @Override
    public String serialize() {
        return color.toString();
    }
}
