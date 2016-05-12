import java.awt.Color;
import java.awt.Graphics;

public class DLine extends DShape{
	
	private DLineModel dLineModel;
	
        public DLine(){
                //shape should take care of model
		this.dLineModel = new DLineModel();
                setModel();
	}
        
        public void setModel(){
            dLineModel.setX(100);
            dLineModel.setY(100);
            dLineModel.setX2(200);
            dLineModel.setY2(100);
            dLineModel.setColor(Color.BLACK);
        }
        
	@Override
	public void draw(Graphics g){
                int x1,y1,x2,y2;
		x1 = dLineModel.getX();
		y1 = dLineModel.getY();
		x2 = dLineModel.getWidth();
		y2 = dLineModel.getHeight();
                g.setColor(dLineModel.getColor());
		g.drawLine(x1, y2, x2, y2);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.dLineModel.getColor();
	}

}