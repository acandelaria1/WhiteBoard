
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class DText extends DShape {
    
    private DTextModel dTextModel;
    
    public DText() {
        this.dTextModel = new DTextModel();
        super.setdShapeModel(this.dTextModel);
        setModel();
    }

    //constructor for opening a file
    public DText(DTextModel model) {
        this.dTextModel = model;
    }

    /*
     Sets the X, Y, width, and height values for model
     */
    private void setModel() {
        dTextModel.setX(70);
        dTextModel.setY(250);
        dTextModel.setHeight(30);
        //width will be determined after font size is calculated based
        //on height in (computeFont)
    }

    /*
     Method to get font displayed correctly inside rectangular bounds
     */
    private Font computeFont(Graphics g) {
        int rectHeight, fontHeight;
        String text, fontName;
        fontName = dTextModel.getFontName();
        rectHeight = dTextModel.getHeight();
        //figure out what size the font needs to be to fit rectangle
        double size = 1.0;
        Font font = new Font(fontName, Font.PLAIN, (int) size);
        FontMetrics metrics = g.getFontMetrics(font);
        fontHeight = metrics.getHeight(); //initial height with size = 1
        while (fontHeight <= rectHeight) {
            size = (size * 1.10) + 1; //increase size
            //create font w/ new size & set new metrics
            font = new Font(fontName, Font.PLAIN, (int) size);
            metrics = g.getFontMetrics(font);
            fontHeight = metrics.getHeight();
        }
        //set updated values in model
        dTextModel.setFont(font);
        dTextModel.setSize((int) size);
        text = dTextModel.getText();
        dTextModel.setWidth(metrics.stringWidth(text));
        return font;
    }
    
    
    @Override
    public void draw(Graphics g) {
        Font theFont = computeFont(g); //so size will fit rectangular bounds
        String text = dTextModel.getText();
        int x, y, width, height;
        x = dTextModel.getX();
        y = dTextModel.getY();
        width = dTextModel.getWidth();
	height = dTextModel.getHeight();
        g.setFont(theFont);
        g.setColor(dTextModel.getColor());
        
        FontMetrics fm = g.getFontMetrics(theFont); //discard bottom padding so more vertically centered
        g.drawString(text, x, (y + height) - fm.getDescent()); //needs to be +h since text displays upwards
        
	//g.drawRect(x, y, width, height); //for testing
	super.draw(g);
    }
    
    @Override
    public Color getColor() {
        return dTextModel.getColor();
    }
    
    @Override
    public void setText(String newText){
        dTextModel.setText(newText);
    }
    
    public void setFontName(String s){
        dTextModel.setFontName(s);
    }
}
