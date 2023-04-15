package editor.document;

public class DocumentParseException extends Exception {
    DocumentParseException(String message) {
        super(message);
    }

    DocumentParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
