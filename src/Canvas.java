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
	private List<DShape> shapes = new ArrayList<DShape>();
	private DShape selectedShape;
	
	public Canvas(){
		displayCanvas();
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int x,y;
				x = e.getX();
				y = e.getY();
				Canvas.this.selectedShape = null;
				DShape selectedShape = findShapeForLocation(x,y);
				if(selectedShape != null){
					selectedShape.setSelected(true);
					Canvas.this.selectedShape = selectedShape;
					System.out.println("IS ANCHOR CHOSEN? "+ selectedShape.isAnchorChosen(x, y));

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
		
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
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

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	public List<DShape> getShapes(){
		return this.shapes;
	}
	
	public void removeShape(DShape shape){
		shapes.remove(shape);
		repaint();
	}
	
	public DShape getSelectedShape(){
		return this.selectedShape;
	}
	
	public void setSelectedShape(DShape shape){
		this.selectedShape = shape;
	}
	
	private DShape findShapeForLocation(int x, int y){
			DShape shape;
			for(int i = shapes.size()-1; i>=0; i--){
				shape = shapes.get(i);
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
	 * moveFront() method: only works if a shape is selected
	 */
	public void moveFront(){
		if(selectedShape != null){
			shapes.remove(selectedShape);
			shapes.add(selectedShape);
		}
		repaint();
	}
	
	public void moveBack(){
		if(selectedShape != null){
			shapes.remove(selectedShape);
			shapes.add(0,selectedShape);
		}
		repaint();
	}
	
	public void addShape(DShape shape){
		shapes.add(shape);
		repaint();
	}
	
	public void deleteShape(DShape s){
		
	}
	
	/*
	 * method clear all shapes from canvas for opening files
	 */
	public void clear(){
		shapes.clear();
        repaint();
	}
	
	/*
    Returns models for all shapes in shapes list (for saving file)
    @return a DShapeModel list
    */
    public List<DShapeModel> getModels(){
        List<DShapeModel> models = new ArrayList<DShapeModel>();
        for(DShape s : shapes){
            models.add(s.getdShapeModel());
        }
        return models;
    }
    
    /*
    Adds shape to canvas (for opening files)
    */
    public void doAdd(DShapeModel model){
        //use the model to create a shape
        if(model instanceof DRectModel){
            DRect shape = new DRect((DRectModel) model);
            shapes.add(shape);
        }else if(model instanceof DOvalModel){
            DOval shape = new DOval((DOvalModel) model);
            shapes.add(shape);
        }else if(model instanceof DLineModel){
            DLine shape = new DLine((DLineModel) model);
            shapes.add(shape);
        }else if(model instanceof DTextModel){
            DText shape = new DText((DTextModel) model);
            shapes.add(shape);
        }
        repaint();
    }
    
    public void chooseColor() {
			final JColorChooser color = new JColorChooser();
			Color selected = color.showDialog(getParent(), "Choose", Color.white);
			this.selectedShape.setColor(selected);
			repaint();
	}
	


	
}