package editor.document;

import editor.interfaces.Document;
import editor.interfaces.DocumentLoader;
import editor.interfaces.SerializableMetaData;
import editor.interfaces.TextSpan;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class EditorDocumentLoader implements DocumentLoader {
    private final String unsetSymbol = "?";

    @Override
    public Document load(File file) throws DocumentParseException {
        EditorDocument document = new EditorDocument();
        ArrayList<TextSpan> textSpans = readTextSpans(file);

        for (TextSpan span : textSpans) {
            document.addTextSpan(span);
        }

        return document;
    }

    private ArrayList<TextSpan> readTextSpans(File file) throws DocumentParseException {
        Scanner fileReader = getFileReader(file);
        ArrayList<TextSpan> textSpans = new ArrayList<>();

        while(fileReader.hasNext()) {
            textSpans.add(
                parseTextSpan(fileReader.next())
            );
        }

        return textSpans;
    }

    private Scanner getFileReader(File file) throws DocumentParseException {
        try {
            return new Scanner(file);
        } catch(FileNotFoundException exception) {
            throw new DocumentParseException("Failed to load document", exception);
        }
    }

    private TextSpan parseTextSpan(String rawTextData) throws DocumentParseException {
        String[] textParts = getTextParts(rawTextData);
        int charactersIndex = 0;
        int metadataIndex = 1;
        String text = URLDecoder.decode(textParts[charactersIndex], StandardCharsets.UTF_8);
        ArrayList<SerializableMetaData> metaData = decodeMetaData(textParts[metadataIndex]);
        return generateTextSpan(text, metaData);
    }

    private String[] getTextParts(String rawTextData) throws DocumentParseException {
        String[] textParts = rawTextData.split(":");
        if (textParts.length < 2) {
            throw new DocumentParseException(String.format("Data too short: '%s'", rawTextData));
        }
        return textParts;
    }

    private ArrayList<SerializableMetaData> decodeMetaData(String rawData) {
        // TODO
    }

    private TextSpan generateTextSpan(String text, ArrayList<SerializableMetaData> metaData) {
        // TODO
    }
}
