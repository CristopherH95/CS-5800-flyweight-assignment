package editor.interfaces;

public interface TextSpan {
    void addCharacters(String characters);
    void deleteCharacters(int count);
    void addMetaData(SerializableMetaData metaData);
    void removeMetaData(SerializableMetaData metaData);
    String serialize();
}
