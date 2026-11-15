package ldts.hungergames.gui;

public interface GUI extends java.io.Closeable {
    int getWidth();
    int getHeight();
    Action getNextAction() throws java.io.IOException;
    void clear();
    void drawChar(int x,int y,char character,GuiColor color );
    void drawText(int x,int y,String text, GuiColor color);
    void refresh() throws java.io.IOException;
}
