import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Canvas extends JPanel{

	final static int DIMENSION = 400;
	List<DShape> shapes = new ArrayList<DShape>();
	
	
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
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(DShape shape : shapes){
			g.setColor(shape.getColor());
			shape.draw(g);
		}
		
	}
	
	/*
	 * moveFront() method:
	 */
	public void moveFront(DShape s){
		
	}
	
	public void moveBack(DShape s){
		
	}
	
	public void addShape(DShape shape){
		//If Shape added is DRect
		shapes.add(shape);
		repaint();
	}
	
	public void deleteShape(DShape s){
		
	}
	
}
