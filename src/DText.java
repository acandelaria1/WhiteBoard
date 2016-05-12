import java.awt.Color;
import java.awt.Graphics;

public class DText extends DShape{
	
	private DTextModel dTextModel;
	
	public DText(){
		this.dTextModel = new DTextModel();
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
}