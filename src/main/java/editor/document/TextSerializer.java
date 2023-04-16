package editor.document;

import editor.enums.MetaDataSymbol;
import editor.exceptions.UnrecognizedMetaDataException;
import editor.interfaces.SerializableMetaData;
import editor.interfaces.TextSpan;
import editor.interfaces.TextSpanSerializer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TextSerializer implements TextSpanSerializer {
    @Override
    public String serialize(TextSpan span) {
        return serializeRawText(span) + ":" + serializeMetaData(span);
    }

    private String serializeRawText(TextSpan span) {
        return URLEncoder.encode(span.getCharacters(), StandardCharsets.UTF_8);
    }

    private String serializeMetaData(TextSpan span) {
        SerializableMetaData[] metaData = span.getMetaData();
        ArrayList<String> serializedData = new ArrayList<>();

        for (SerializableMetaData data : metaData) {
            try {
                MetaDataSymbol symbol = getMetaDataSymbol(data);
                String value = data.serializeValue();
                serializedData.add(symbol.toString() + ";" + value);
            } catch (UnrecognizedMetaDataException e) {
                e.printStackTrace(System.err);
            }
        }

        return String.join(",", serializedData);
    }

    private MetaDataSymbol getMetaDataSymbol(SerializableMetaData metaData) throws UnrecognizedMetaDataException {
        return MetaDataSymbol.getSymbolForMetaData(metaData);
    }
}
