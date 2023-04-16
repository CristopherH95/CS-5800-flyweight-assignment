package editor.interfaces;

import editor.exceptions.DocumentParseException;

import java.io.File;

public interface DocumentLoader {
    Document load(File file) throws DocumentParseException;
}
