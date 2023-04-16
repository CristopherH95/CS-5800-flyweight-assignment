package editor.metadata;

import editor.abstractions.MetaData;
import editor.enums.FontType;

public class Font extends MetaData {
    private final FontType fontType;

    public Font(FontType fontType) {
        this.fontType = fontType;
    }

    @Override
    public String getLabel() {
        return String.format("Font: %s", fontType.toString());
    }

    @Override
    public String serializeValue() {
        return fontType.toString();
    }
}
