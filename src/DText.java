import java.awt.Color;
import java.awt.Graphics;

public class DText extends DShape{
	
	private DTextModel dTextModel;
	
	public DText(){
		this.dTextModel = new DTextModel();
		this.setdShapeModel(this.dTextModel);
	}
	
	//constructor for opening a file
    public DText(DTextModel model){
        this.dTextModel = model;
    }

	
	@Override
	public void draw(Graphics g){
		// TODO: Dtext spefic draw method
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return dTextModel.getColor();
	}

	@Override
	public boolean containsPoint(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}