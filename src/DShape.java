import java.awt.Color;
import java.awt.Graphics;

public abstract class DShape {
	boolean selected;
	public static int KNOB_DIMENSION = 3;
	public abstract Color getColor();
	/*
	 * draw method
	 */
	public void draw(Graphics g){
		
	}
	
	public boolean isSelected(){
		return this.selected;
	}
	
	public void setSelected(Boolean sel){
		this.selected = sel;
	}
	
	public void drawKnobs(Graphics g){
	}

	public abstract boolean containsPoint(int x, int y);
		
	
	
	
}