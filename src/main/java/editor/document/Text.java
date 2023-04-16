package editor.document;

import editor.interfaces.SerializableMetaData;
import editor.interfaces.TextSpan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Text implements TextSpan {
    private String characters;
    HashSet<SerializableMetaData> metaDataSet;

    public Text() {
        characters = "";
        metaDataSet = new HashSet<>();
    }

    @Override
    public void addCharacters(String characters) {
        this.characters += characters;
    }

    @Override
    public void deleteCharacters(int count) {
        characters = characters.substring(0, characters.length() - count);
    }

    @Override
    public String getCharacters() {
        return characters;
    }

    @Override
    public void addMetaData(SerializableMetaData... metaData) {
        metaDataSet.addAll(Arrays.asList(metaData));
    }

    @Override
    public void removeMetaData(SerializableMetaData... metaData) {
        metaDataSet.removeAll(Arrays.asList(metaData));
    }

    @Override
    public SerializableMetaData[] getMetaData() {
        return metaDataSet.toArray(new SerializableMetaData[0]);
    }

    @Override
    public String toString() {
        return String.format("%s%s", characters, getMetaDataDisplay());
    }

    private String getMetaDataDisplay() {
        ArrayList<String> metaDataItems = new ArrayList<>();

        for (SerializableMetaData metaData : metaDataSet) {
            metaDataItems.add(metaData.getLabel());
        }

        return String.format("[%s]", String.join(", ", metaDataItems));
    }
}
