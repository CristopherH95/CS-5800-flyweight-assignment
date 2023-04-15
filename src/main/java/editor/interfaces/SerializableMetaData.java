package editor.interfaces;

public interface SerializableMetaData extends Labeled, Comparable<SerializableMetaData> {
    String serialize();
}
