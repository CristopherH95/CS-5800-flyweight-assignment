package editor.interfaces;

import editor.exceptions.TextParseException;

public interface TextSpanDeserializer {
    TextSpan deserialize(String span) throws TextParseException;
}
