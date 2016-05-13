import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class Canvas extends JPanel{

	final static int DIMENSION = 400;
	List<DShape> shapes = new ArrayList<DShape>();
	private int position;
	//DShape selectedShape;
	
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
					System.out.println("IS ANCHOR CHOSEN? "+ selectedShape.isAnchorChosen(x, y));

					int indexForSelectedShape = shapes.indexOf(selectedShape);
					position = indexForSelectedShape;
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
		
		this.addMouseMotionListener(new MouseMotionListener(){

			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int x, y;
				x = e.getX();
				y = e.getY();
				
				for(DShape shape : shapes){
					if(shape.isSelected()){
						//Handle when a anchor is moved
						int anchorNumber = 0;
						if((anchorNumber = shape.isAnchorChosen(x, y)) != 0){
							System.out.println("FROM MOUSE DRAGGED ANCHOR NUMBER "+ anchorNumber);
							//Dont do anything yet
							
							if(anchorNumber == 1)shape.dragAnchorOne(x,y);
							else if(anchorNumber == 2)shape.dragAnchorTwo(x,y);
							else if(anchorNumber == 3)shape.dragAnchorThree(x,y);
							else if(anchorNumber == 4)shape.dragAnchorFour(x,y);
						}else{
							shape.moveTo(e.getX(),e.getY());
						}
						repaint();
					}
				}
				
			}

			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
	
	// Set Color Option
	public void choiceofcolor() {
		if(shapes.get(position).selected)
		{
			 JColorChooser color = new JColorChooser();
			Color selected = color.showDialog(getParent(), "Choose", Color.white);
			shapes.get(position).getdShapeModel().setColor(selected);
			
			repaint();
		}
	}
	
	// Removes a selected shape
	public void removeShape(){
		shapes.remove(position);
		repaint();
	}

	
}
