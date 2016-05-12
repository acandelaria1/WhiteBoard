import java.awt.Color;
import java.util.List;

public class DShapeModel {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private List<ModelListener> modelListeners;
	
	// DShape Model Constructor
	public DShapeModel(){
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.color = Color.GRAY;
	}
	
		
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
        public Color getColor(){
                return color;
        }
	public void setColor(Color color) {
		this.color = color;
	}
	public List<ModelListener> getModelListeners() {
		return modelListeners;
	}
	public void setModelListeners(List<ModelListener> modelListeners) {
		this.modelListeners = modelListeners;
	}
	
	
	
}