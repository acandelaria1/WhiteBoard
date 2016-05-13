import java.awt.Color;
import java.awt.Graphics;

public abstract class DShape {
	boolean selected;
	private DShapeModel dShapeModel;
	public static int KNOB_DIMENSION = 3;
	public abstract Color getColor();
	/*
	 * draw method
	 */
	public void draw(Graphics g){
		System.out.println("DSHAPE DRAW CALLED");
		if(selected) drawKnobs(g);
	}
	
	public boolean isSelected(){
		return this.selected;
	}
	
	public DShapeModel getdShapeModel() {
		return dShapeModel;
	}
	public void setdShapeModel(DShapeModel dShapeModel) {
		this.dShapeModel = dShapeModel;
	}
	public void setSelected(Boolean sel){
		this.selected = sel;
	}
	
	public void drawKnobs(Graphics g){
		int x1,y1,x2,y2,x3,y3,x4,y4;
		
		x1 = this.dShapeModel.getX();
		y1 = this.dShapeModel.getY();
		
		x2 = this.dShapeModel.getX();
		y2 = this.dShapeModel.getY() +dShapeModel.getHeight();
		
		x3 = this.dShapeModel.getX() + dShapeModel.getWidth();
		y3 = this.dShapeModel.getY();
		
		x4 = this.dShapeModel.getX() + dShapeModel.getWidth();
		y4 = dShapeModel.getY() + dShapeModel.getHeight();
		
		g.setColor(Color.BLACK);
		//first knob
		g.drawRect(x1-2, y1-2, KNOB_DIMENSION, KNOB_DIMENSION);
		g.fillRect(x1-2, y1-2, KNOB_DIMENSION, KNOB_DIMENSION);
		
		//second knob
		g.drawRect(x2-2, y2-2, KNOB_DIMENSION, KNOB_DIMENSION);
		g.fillRect(x2-2, y2-2, KNOB_DIMENSION, KNOB_DIMENSION);
		
		//Third Knob
		g.drawRect(x3-2, y3-2, KNOB_DIMENSION, KNOB_DIMENSION);
		g.fillRect(x3-2, y3-2, KNOB_DIMENSION, KNOB_DIMENSION);
		
		//Fourth Knob
		g.drawRect(x4-2, y4-2, KNOB_DIMENSION, KNOB_DIMENSION);
		g.fillRect(x4-2, y2-4, KNOB_DIMENSION, KNOB_DIMENSION);

		
	}

	public boolean containsPoint(int x, int y){
		DShapeModel model = this.dShapeModel;
		if(model != null){
			int height = model.getHeight();
			int width = model.getWidth();
			if(x>= model.getX() && x <= model.getX() + width && y>= model.getY() && y <= model.getY()+height){
				this.setSelected(true);
				System.out.println("SELECTED!!!!");
				return true;
			}
			this.setSelected(false);
		}
		return false;
	}
		
	
	
	
}