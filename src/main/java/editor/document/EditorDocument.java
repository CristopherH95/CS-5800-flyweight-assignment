package editor.document;

import editor.interfaces.Document;
import editor.interfaces.TextSpan;

import java.util.ArrayList;
import java.util.Iterator;

public class EditorDocument implements Document {
    private final ArrayList<TextSpan> textSpans;

    public EditorDocument() {
        textSpans = new ArrayList<>();
    }

    @Override
    public void addTextSpan(TextSpan textSpan) {
        textSpans.add(textSpan);
    }

    @Override
    public TextSpan getTextSpan(int index) {
        return textSpans.get(index);
    }

    @Override
    public void setTextSpan(int index, TextSpan textSpan) {
        textSpans.set(index, textSpan);
    }

    @Override
    public void removeTextSpan(int index) {
        textSpans.remove(index);
    }

    @Override
    public Iterator<TextSpan> iterator() {
        return textSpans.iterator();
    }
}
