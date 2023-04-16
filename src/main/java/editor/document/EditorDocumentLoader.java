package editor.document;

import editor.exceptions.DocumentParseException;
import editor.exceptions.TextParseException;
import editor.interfaces.Document;
import editor.interfaces.DocumentLoader;
import editor.interfaces.TextSpan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EditorDocumentLoader implements DocumentLoader {
    private final TextDeserializer deserializer = new TextDeserializer();

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
        try {
            return deserializer.deserialize(rawTextData);
        } catch(TextParseException e) {
            throw new DocumentParseException(String.format("Failed to parse text data: '%s'", rawTextData), e);
        }
    }
}
