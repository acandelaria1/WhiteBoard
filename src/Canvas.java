import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Canvas extends JPanel{

	final static int DIMENSION = 400;
	List<DShape> shapes = new ArrayList<DShape>();
	
	
	public Canvas(){
		displayCanvas();
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//System.out.println(e.getX() + " " +e.getY());
				int x,y;
				x = e.getX();
				y = e.getY();
				DShape selectedShape = findShapeForLocation(x,y);
				if(selectedShape != null){
					selectedShape.setSelected(true);
					int indexForSelectedShape = shapes.indexOf(selectedShape);
					for(int i =0; i<shapes.size(); i++){
						if(i != indexForSelectedShape){
							shapes.get(i).setSelected(false);
						}
					}
				}else if(selectedShape == null){
					for(DShape shape : shapes){
						shape.setSelected(false);
					}
				}
				repaint();
			}
		});
		
	}
	
	private DShape findShapeForLocation(int x, int y){
		for(DShape shape : shapes){
			if(shape.containsPoint(x,y)){
				return shape;
			}
		}
		return null;
		
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
		shapes.add(shape);
		repaint();
	}
	
	public void deleteShape(DShape s){
		
	}

	
}