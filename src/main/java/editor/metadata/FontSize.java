package editor.metadata;

import editor.abstractions.MetaData;

public class FontSize extends MetaData {
    private final float point;

    public FontSize(float point) {
        this.point = point;
    }

    @Override
    public String getLabel() {
        return String.format("Size: %s pt", point);
    }

    @Override
    public String serialize() {
        return String.valueOf(point);
    }
}
