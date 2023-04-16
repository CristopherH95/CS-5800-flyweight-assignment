package editor.enums;

import editor.exceptions.UnrecognizedMetaDataException;
import editor.interfaces.SerializableMetaData;
import editor.metadata.Font;
import editor.metadata.FontColor;
import editor.metadata.FontSize;

public enum MetaDataSymbol {
    FONT,
    FONT_COLOR,
    FONT_SIZE;

    public static MetaDataSymbol getSymbolForMetaData(SerializableMetaData metaData) throws UnrecognizedMetaDataException {
        if (metaData instanceof Font) {
            return FONT;
        }
        if (metaData instanceof FontColor) {
            return FONT_COLOR;
        }
        if (metaData instanceof FontSize) {
            return FONT_SIZE;
        }

        throw new UnrecognizedMetaDataException("Unrecognized metadata type");
    }
}
