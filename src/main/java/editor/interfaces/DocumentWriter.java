package editor.interfaces;

import editor.exceptions.DocumentWriteException;

import java.io.File;

public interface DocumentWriter {
    void write(Document document, File file) throws DocumentWriteException;
}
