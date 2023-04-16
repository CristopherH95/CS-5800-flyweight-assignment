package editor.document;

import editor.exceptions.DocumentWriteException;
import editor.interfaces.Document;
import editor.interfaces.DocumentWriter;
import editor.interfaces.TextSpan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorDocumentWriter implements DocumentWriter {
    private final TextSerializer serializer = new TextSerializer();

    @Override
    public void write(Document document, File file) throws DocumentWriteException {
        FileWriter fileWriter = getFileWriter(file);

        for (TextSpan textSpan : document) {
            writeTextSpan(textSpan, fileWriter);
        }
    }

    private FileWriter getFileWriter(File file) throws DocumentWriteException {
        try {
            return new FileWriter(file);
        } catch(IOException e) {
            throw new DocumentWriteException("Failed to open file for write", e);
        }
    }

    private void writeTextSpan(TextSpan textSpan, FileWriter fileWriter) throws DocumentWriteException {
        try {
            fileWriter.write(serializer.serialize(textSpan));
            fileWriter.write(System.lineSeparator());
        } catch(IOException e) {
            throw new DocumentWriteException("Failed to write to file", e);
        }
    }
}
