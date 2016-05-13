import java.awt.Color;
import java.awt.Graphics;

public abstract class DShape {
	static int ANCHORSIZE = 10;
	boolean selected;
	private DShapeModel dShapeModel;
	public static int KNOB_DIMENSION = 3;
	public abstract Color getColor();
	/*
	 * draw method
	 */
	public void draw(Graphics g){
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
	
	public void dragAnchorOne(int x, int y){
		DShapeModel shapeModel = this.dShapeModel;
		int prevX,prevY, differenceX, differenceY;
		prevX = shapeModel.getX();
		prevY = shapeModel.getY();
		differenceX = (prevX-x);
		differenceY = (prevY-y);
		moveOriginTo(x,y);
		dShapeModel.setWidth(dShapeModel.getWidth()+differenceX);
		dShapeModel.setHeight(dShapeModel.getHeight()+differenceY);
	}
	
	public void dragAnchorTwo(int x, int y){
		DShapeModel shapeModel = this.dShapeModel;
		System.out.println("DRAG ANCHOR TWO CALLED!!!!!!!!!!!!!!");
		int originx,prevX, prevY, differenceX, differenceY;
		originx=shapeModel.getX();
		prevX = shapeModel.getX()+shapeModel.getWidth();
		prevY = shapeModel.getY();
		differenceX = (x - prevX );
		differenceY = (prevY - y);
		moveOriginTo(originx, prevY - differenceY);
		dShapeModel.setWidth(dShapeModel.getWidth()+differenceX);
		dShapeModel.setHeight(dShapeModel.getHeight()+differenceY);	
	}
	
	public void dragAnchorThree(int x, int y){
		DShapeModel shapeModel = this.dShapeModel;
		System.out.println("DRAG ANCHOR THREE CALLED!");
		int originy, originx,prevX, prevY, differenceX, differenceY;
		originy = shapeModel.getY();
		originx = shapeModel.getX();
		prevX = shapeModel.getX();
		prevY = originy+shapeModel.getHeight();
		differenceX = (prevX - x);
		differenceY = (y - prevY);
		moveOriginTo(originx-differenceX,originy);
		dShapeModel.setWidth(shapeModel.getWidth()+differenceX);
		dShapeModel.setHeight(shapeModel.getHeight()+differenceY);
		
	}
	
	public void dragAnchorFour(int x, int y){
		DShapeModel shapeModel = this.dShapeModel;
		System.out.println("DRAG ANCHOR FOUR CALLED!");
		int originx,originy, prevX, prevY, differenceX, differenceY;
		originx = shapeModel.getX();
		originy = shapeModel.getY();
		prevX = originx + shapeModel.getWidth();
		prevY = originy + shapeModel.getHeight();
		differenceX = x - prevX;
		differenceY = y - prevY;
		dShapeModel.setWidth(shapeModel.getWidth() + differenceX);
		dShapeModel.setHeight(shapeModel.getHeight() + differenceY);
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
	
	public void moveOriginTo(int x, int y){
		dShapeModel.setX(x);
		dShapeModel.setY(y);
	}
	
	public void moveTo(int x, int y){
		dShapeModel.setX(x-dShapeModel.getWidth()/2);
		dShapeModel.setY(y-dShapeModel.getHeight()/2);
	}
	
	public int isAnchorChosen(int x, int y){
		
		if(isAnchorOneChosen(x,y)){
			System.out.println("ANCHOR 1 CHOSEN!!!");
			return 1;
		}else if(isAnchorTwoChosen(x,y)){
			System.out.println("ANCHOR 1 CHOSEN!!!");
			return 2;
		}else if(isAnchorThreeChosen(x,y)){
			System.out.println("ANCHOR 1 CHOSEN!!!");
			return 3;

		}else if(isAnchorFourChosen(x,y)){
			System.out.println("ANCHOR 1 CHOSEN!!!");
			return 4;
		}
		
		return 0;
	}
	
	//Anchor one is defined as the anchor point of the shapeModel.
	//In shape model this is defined as x and y
	private boolean isAnchorOneChosen(int x, int y){
		int shapeX = dShapeModel.getX();
		int shapeY = dShapeModel.getY();
		if(x >= shapeX-ANCHORSIZE && x<= shapeX + ANCHORSIZE &&  y >= shapeY-ANCHORSIZE && y <= shapeY+ANCHORSIZE) return true;
		
		return false;
	}
	
	//Anchor two is defined as the anchor point x+width , y
	private boolean isAnchorTwoChosen(int x, int y){
		
		int shapeX = dShapeModel.getX() + dShapeModel.getWidth();
		int shapeY = dShapeModel.getY();
		if(x >= shapeX-ANCHORSIZE && x<= shapeX + ANCHORSIZE &&  y >= shapeY-ANCHORSIZE && y <= shapeY+ANCHORSIZE) return true;
		
		return false;
	}
	
	//Anchor three is defined as the anchor point x, y+height
	private boolean isAnchorThreeChosen(int x, int y){
		int shapeX = dShapeModel.getX();
		int shapeY = dShapeModel.getY() + dShapeModel.getHeight();
		if(x >= shapeX-ANCHORSIZE && x<= shapeX + ANCHORSIZE &&  y >= shapeY-ANCHORSIZE && y <= shapeY+ANCHORSIZE) return true;	
		return false;
	}
	
	//Anchor four is defined as the anchor point x+width, y+height
	private boolean isAnchorFourChosen(int x , int y){
		int shapeX = dShapeModel.getX() + dShapeModel.getWidth();
		int shapeY = dShapeModel.getY() + dShapeModel.getHeight();
		if(x >= shapeX-ANCHORSIZE && x<= shapeX + ANCHORSIZE &&  y >= shapeY-ANCHORSIZE && y <= shapeY+ANCHORSIZE) return true;
		return false;
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