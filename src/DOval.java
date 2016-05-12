import java.awt.Color;
import java.awt.Graphics;

public class DOval extends DShape {

	private DOvalModel dOvalModel;
	
        public DOval(){
            this.dOvalModel = new DOvalModel();
            setModel();
        }
        
        private void setModel(){
            dOvalModel.setX(10);
            dOvalModel.setY(10);
            dOvalModel.setWidth(30);
            dOvalModel.setHeight(30);
            dOvalModel.setColor(Color.BLUE);
        }
        
        @Override
	public void draw(Graphics g){
                int x,y,width,height;
		x = dOvalModel.getX();
		y = dOvalModel.getY();
		width = dOvalModel.getWidth();
		height = dOvalModel.getHeight();
                g.setColor(dOvalModel.getColor());
		g.drawOval(x, y, width, width);
		g.fillOval(x, y, width, height);
        }

		@Override
		public Color getColor() {
			// TODO Auto-generated method stub
			return dOvalModel.getColor();
		}
}