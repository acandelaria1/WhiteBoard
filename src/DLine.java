import java.awt.Color;
import java.awt.Graphics;


public class DLine extends DShape{
	
	private DLineModel dLineModel;
	private Boolean selected;
        public DLine(){
                //shape should take care of model
		this.dLineModel = new DLineModel();
		selected = false;
                setModel();
	}
        
        public void setModel(){
            dLineModel.setX(70);
            dLineModel.setY(80);
            dLineModel.setX2(100);
            dLineModel.setY2(100);
        }
        
	@Override
	public void draw(Graphics g){
                int x1,y1,x2,y2;
		x1 = dLineModel.getX();
		y1 = dLineModel.getY();
		x2 = dLineModel.getX2();
		y2 = dLineModel.getY2();
                g.setColor(dLineModel.getColor());
		g.drawLine(x1, y1, x2, y2);
		System.out.println("GOT TO DRAW ! SELECTED = " + this.selected);
		if(selected) drawKnobs(g);
	}
	
	@Override
	public void drawKnobs(Graphics g){
		//find each end of the lines points..
		System.out.println("GOT TO DRAWKNOBS! selected = " + this.selected);
		int x1, x2,y1,y2;
		x1 = dLineModel.getX();
		x2 = dLineModel.getX2();
		y1 = dLineModel.getY();
		y2 = dLineModel.getY2();
		//use these points to draw squares with their centers at the endpoints
		g.setColor(Color.BLACK);
		//first knob
		g.drawRect(x1-2, y1-2, KNOB_DIMENSION, KNOB_DIMENSION);
		g.fillRect(x1-2, y1-2, KNOB_DIMENSION, KNOB_DIMENSION);
		
		//second knob
		g.drawRect(x2-2, y2-2, KNOB_DIMENSION, KNOB_DIMENSION);
		g.fillRect(x2-2, y2-2, KNOB_DIMENSION, KNOB_DIMENSION);
		
		//
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.dLineModel.getColor();
	}

	@Override
	public boolean containsPoint(int x, int y) {
		// TODO Auto-generated method stub
		//First find slope of line
		int y1,y2;
		y1 = dLineModel.getY();
		y2 = dLineModel.getY2();
		double slope = this.dLineModel.getSlope();
		//y = mx + b
		//b = y - mx
		//Find y intercept
		double b = (double)dLineModel.getY() - slope*(double)dLineModel.getX();
		//now plug int point to see if it intersects the line
		if(y > (int)(slope*(double)x + b-20) && y < (int)(slope*(double)x+b+20)){
			if(y <= Math.max(y1, y2) && y >= Math.min(y1, y2)){
				System.out.println("HAS BEEN SELECTED " + (int)(slope*(double)x+b));
				this.selected=true;
				return true;
			}
		}
		this.selected = false;
		return false; 
	}

	

	

}