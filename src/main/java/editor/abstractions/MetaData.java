package editor.abstractions;

import editor.interfaces.SerializableMetaData;

public abstract class MetaData implements SerializableMetaData {
    @Override
    public int compareTo(SerializableMetaData o) {
        return serializeValue().compareTo(o.serializeValue());
    }
}
