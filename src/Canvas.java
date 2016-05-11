import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;

public class Canvas extends JPanel{

	final static int DIMENSION = 400;
	List<DShape> shapes;
	
	
	public Canvas(){
		displayCanvas();
	}
	
	public void displayCanvas(){
		setPreferredSize(new Dimension(DIMENSION, DIMENSION));
		setBackground(Color.WHITE);

	}
	
	/*
	 * paintComponent() method:
	 */
	public void paintComponent(){
		
	}
	
	/*
	 * moveFront() method:
	 */
	public void moveFront(DShape s){
		
	}
	
	public void moveBack(DShape s){
		
	}
	
	public void addShape(DShape s){
		
	}
	
	public void deleteShape(DShape s){
		
	}
	
}
