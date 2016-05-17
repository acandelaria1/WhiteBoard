
import java.awt.Font;
import java.awt.Font.*;
import java.awt.FontMetrics;

public class DTextModel extends DShapeModel {

    private String text;
    private String fontName;
    private Font font;
    private int size;

    public DTextModel() {
        text = "Hello";
        size = 0;
        fontName = "Dialog";
        font = new Font(fontName, Font.PLAIN, size); //Font.getFont("Dialog");
    }

    public String getText() {
        return text;
    }
    
    public String getFontName(){
        return fontName;
    }

    public Font getFont() {
        return font;
    }

    public void setText(String theText) {
        this.text = theText;
    }

    public void setFont(Font theFont) {
        this.font = theFont;
    }
    
    public void setFontName(String s) {
        this.fontName = s;
    }
    
    public void setSize(int theSize){
        this.size = theSize;
    }
}
