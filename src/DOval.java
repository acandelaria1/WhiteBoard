import java.awt.Color;
import java.awt.Graphics;

public class DOval extends DShape {

	private DOvalModel dOvalModel;
	
        public DOval(){
            this.dOvalModel = new DOvalModel();
            super.setdShapeModel(dOvalModel);
            setModel();
        }
        
      //constructor for opening a file
        public DOval(DOvalModel aModel){
             //set the model
        	this.dOvalModel = aModel;
        }

        
        private void setModel(){
            dOvalModel.setX(50);
            dOvalModel.setY(50);
            dOvalModel.setWidth(30);
            dOvalModel.setHeight(30);
        }
        
        @Override
	public void draw(Graphics g){
                int x,y,width,height;
		x = dOvalModel.getX();
		y = dOvalModel.getY();
		width = dOvalModel.getWidth();
		height = dOvalModel.getHeight();
                g.setColor(dOvalModel.getColor());
		g.drawOval(x, y, width, height);
		g.fillOval(x, y, width, height);
		super.draw(g);
   }

		@Override
		public Color getColor() {
			// TODO Auto-generated method stub
			return dOvalModel.getColor();
		}

		
}