import java.awt.Color;
import java.awt.Graphics;

public class DRect extends DShape {

	private DRectModel dRectModel;
	
	DRect(){
		this.dRectModel = new DRectModel();
	}
	
	public DRectModel getShapeModel(){
		return dRectModel;
	}
	
	@Override
	public void draw(Graphics g){
		int x,y,width,height;
		x = dRectModel.getX();
		y = dRectModel.getY();
		width = dRectModel.getWidth();
		height = dRectModel.getHeight();
		g.drawRect(x, y, width, width);
		g.fillRect(x, y, width, height);
		g.setColor(dRectModel.getColor());
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.dRectModel.getColor();
	}
}
