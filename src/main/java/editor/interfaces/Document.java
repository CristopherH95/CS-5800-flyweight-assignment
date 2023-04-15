package editor.interfaces;

import java.util.ArrayList;

public interface Document extends Iterable<TextSpan> {
    void addTextSpan(TextSpan textSpan);
    TextSpan getTextSpan(int index);
    void setTextSpan(int index, TextSpan textSpan);
    void removeTextSpan(int index);
}
