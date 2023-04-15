package editor.interfaces;

import editor.document.DocumentParseException;

import java.io.File;

public interface DocumentLoader {
    Document load(File file) throws DocumentParseException;
}
