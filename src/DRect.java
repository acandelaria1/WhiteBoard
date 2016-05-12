import java.awt.Color;
import java.awt.Graphics;

public class DRect extends DShape {

	private DRectModel dRectModel;
	
	public DRect(){
                //shape should take care of model
		this.dRectModel = new DRectModel();
        setModel();
	}
	
	public DRectModel getShapeModel(){
		return dRectModel;
	}
	
        /*
        Sets the X, Y, width, and height values for model
        */
        private void setModel(){
            dRectModel.setX(10);
            dRectModel.setY(10);
            dRectModel.setWidth(30);
            dRectModel.setHeight(30);
            dRectModel.setColor(Color.RED);
            
        }
        
	@Override
	public void draw(Graphics g){
		int x,y,width,height;
		x = dRectModel.getX();
		y = dRectModel.getY();
		width = dRectModel.getWidth();
		height = dRectModel.getHeight();
                g.setColor(dRectModel.getColor());
		g.drawRect(x, y, width, width);
		g.fillRect(x, y, width, height);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.dRectModel.getColor();
	}
}