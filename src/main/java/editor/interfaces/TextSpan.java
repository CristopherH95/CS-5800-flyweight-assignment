package editor.interfaces;

import java.util.ArrayList;

public interface TextSpan {
    void addCharacters(String characters);
    void deleteCharacters(int count);
    String getCharacters();
    void addMetaData(SerializableMetaData... metaData);
    void removeMetaData(SerializableMetaData... metaData);
    SerializableMetaData[] getMetaData();
}
