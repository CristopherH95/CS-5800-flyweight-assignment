package editor.document;

import editor.enums.Color;
import editor.enums.FontType;
import editor.enums.MetaDataSymbol;
import editor.exceptions.MetaDataParseException;
import editor.exceptions.TextParseException;
import editor.factories.FontColorFactory;
import editor.factories.FontFactory;
import editor.factories.FontSizeFactory;
import editor.interfaces.SerializableMetaData;
import editor.interfaces.TextSpan;
import editor.interfaces.TextSpanDeserializer;
import editor.records.RawParsedMetaDataParts;
import editor.records.RawParsedTextParts;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TextDeserializer implements TextSpanDeserializer {
    @Override
    public TextSpan deserialize(String span) throws TextParseException {
        Text text = new Text();
        RawParsedTextParts textParts = getTextParts(span);
        String characters = deserializeRawText(textParts.content());
        ArrayList<SerializableMetaData> metaData = deserializeMetaData(textParts.metaData());
        text.addCharacters(characters);
        text.addMetaData(metaData.toArray(new SerializableMetaData[0]));

        return text;
    }

    private RawParsedTextParts getTextParts(String text) throws TextParseException {
        String[] parts = text.split(":");
        if (parts.length < 2) {
            throw new TextParseException(String.format("Data too short: '%s'", text));
        }
        String content = parts[0];
        String metaData = parts[1];

        return new RawParsedTextParts(
            content,
            metaData
        );
    }

    private String deserializeRawText(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }

    private ArrayList<SerializableMetaData> deserializeMetaData(String rawData) {
        String[] tokens = getMetaDataTokens(rawData);
        ArrayList<SerializableMetaData> metaData = new ArrayList<>();

        for (String metaDataToken : tokens) {
            try {
                metaData.add(parseMetaDataToken(metaDataToken));
            } catch(MetaDataParseException e) {
                e.printStackTrace(System.err);
            }
        }

        return metaData;
    }

    private String[] getMetaDataTokens(String rawData) {
        return rawData.split(",");
    }

    private SerializableMetaData parseMetaDataToken(String metaDataText) throws MetaDataParseException {
        RawParsedMetaDataParts parts = getMetaDataParts(metaDataText);
        return constructMetaData(parts);
    }

    private RawParsedMetaDataParts getMetaDataParts(String metaDataText) throws MetaDataParseException {
        String[] metaDataParts = metaDataText.split(";");
        if (metaDataParts.length < 2) {
            throw new MetaDataParseException(String.format("Meta data too short: '%s'", metaDataText));
        }
        String rawSymbol = metaDataParts[0];
        String value = metaDataParts[1];

        return new RawParsedMetaDataParts(
                rawSymbol,
                value
        );
    }

    private SerializableMetaData constructMetaData(RawParsedMetaDataParts parts) throws MetaDataParseException {
        MetaDataSymbol symbol = parseMetaDataSymbol(parts.symbol());

        switch (symbol) {
            case FONT -> {
                FontType fontType = parseFontType(parts.value());
                return FontFactory.getFont(fontType);
            }
            case FONT_COLOR -> {
                Color color = parseColor(parts.value());
                return FontColorFactory.getFontColor(color);
            }
            case FONT_SIZE -> {
                float size = parseFontSize(parts.value());
                return FontSizeFactory.getFontSize(size);
            }
            default ->
                throw new MetaDataParseException(
                    String.format("Could not resolve symbol: '%s'", symbol)
                );
        }
    }

    private MetaDataSymbol parseMetaDataSymbol(String rawSymbol) throws MetaDataParseException {
        try {
            return MetaDataSymbol.valueOf(rawSymbol);
        } catch(IllegalArgumentException e) {
            String errorMessage = String.format(
                    "Failed to translate symbol: '%s'",
                    rawSymbol
            );
            throw new MetaDataParseException(errorMessage, e);
        }
    }

    private FontType parseFontType(String rawFontType) throws MetaDataParseException {
        try {
            return FontType.valueOf(rawFontType);
        } catch(IllegalArgumentException e) {
            String errorMessage = String.format(
                    "Failed to translate font type: '%s'",
                    rawFontType
            );
            throw new MetaDataParseException(errorMessage, e);
        }
    }

    private Color parseColor(String rawColor) throws MetaDataParseException {
        try {
            return Color.valueOf(rawColor);
        } catch(IllegalArgumentException e) {
            String errorMessage = String.format(
                    "Failed to translate color: '%s'",
                    rawColor
            );
            throw new MetaDataParseException(errorMessage, e);
        }
    }

    private float parseFontSize(String rawFontSize) throws MetaDataParseException {
        try {
            return Float.parseFloat(rawFontSize);
        } catch(NumberFormatException e) {
            String errorMessage = String.format(
                    "Failed to translate font size: '%s'",
                    rawFontSize
            );
            throw new MetaDataParseException(errorMessage, e);
        }
    }
}
