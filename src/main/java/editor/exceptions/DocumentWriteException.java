package editor.exceptions;

public class DocumentWriteException extends Exception {
    public DocumentWriteException(String message) {
        super(message);
    }

    public DocumentWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
