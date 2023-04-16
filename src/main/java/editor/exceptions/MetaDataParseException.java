package editor.exceptions;

public class MetaDataParseException extends Exception {
    public MetaDataParseException(String message) {
        super(message);
    }

    public MetaDataParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
