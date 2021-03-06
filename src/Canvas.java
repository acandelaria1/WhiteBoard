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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Canvas extends JPanel {

    final static int DIMENSION = 400;
    private List<DShape> shapes = new ArrayList<DShape>();
    private DShape selectedShape;
    //instance variables for table model:
    DefaultTableModel tableModel;
    Integer[] tableInfo;
    List<DShape> tablePosition = new ArrayList<DShape>();

    public Canvas() {
        displayCanvas();

        //set table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("X");
        tableModel.addColumn("Y");
        tableModel.addColumn("Width");
        tableModel.addColumn("Height");

        //initialize array to hold information for table in WhiteBoard frame
        tableInfo = new Integer[4];

        //mouse listeners
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x, y;
                x = e.getX();
                y = e.getY();
                Canvas.this.selectedShape = null;
                DShape selectedShape = findShapeForLocation(x, y);
                if (selectedShape != null) {
                    selectedShape.setSelected(true);
                    Canvas.this.selectedShape = selectedShape;
                    int indexForSelectedShape = shapes.indexOf(selectedShape);
                    for (int i = 0; i < shapes.size(); i++) {
                        if (i != indexForSelectedShape) {
                            shapes.get(i).setSelected(false);
                        }
                    }
                } else if (selectedShape == null) {
                    for (DShape shape : shapes) {
                        shape.setSelected(false);
                    }
                }
                repaint();
                DLine.initiated = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int x, y;
                x = e.getX();
                y = e.getY();

                for (DShape shape : shapes) {
                    if (shape.isSelected()) {
                        //Handle when a anchor is moved
                        int anchorNumber = 0;
                        if ((anchorNumber = shape.isAnchorChosen(x, y)) != 0) {
                            if (anchorNumber == 1) {
                                shape.dragAnchorOne(x, y);
                                table(shape);
                            } else if (anchorNumber == 2) {
                                shape.dragAnchorTwo(x, y);
                                table(shape);
                            } else if (anchorNumber == 3) {
                                shape.dragAnchorThree(x, y);
                                table(shape);
                            } else if (anchorNumber == 4) {
                                shape.dragAnchorFour(x, y);
                                table(shape);
                            }
                        } else {
                            shape.moveTo(e.getX(), e.getY());
                            
                            table(shape);
                        }
                        repaint();
                    }
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });

    }

    public List<DShape> getShapes() {
        return this.shapes;
    }

    public void doRemoveShape(DShapeModel shapeModel){
    	for(DShape shape: shapes){
    		System.out.println(shape.getdShapeModel() + " " + shapeModel);
    		if(shape.getdShapeModel().getId().equals(shapeModel.getId())){
    			shapes.remove(shape);
    			System.out.println("GOT IN REMOVE SHAPE");
    		}
    	}
    	repaint();
    }
    
    public void removeShape(DShape shape) {
        shapes.remove(shape);
        int correctIndex = tablePosition.indexOf(shape);
        try {
            tableModel.removeRow(correctIndex); // remove from table model
        } catch (Exception e) {
        }
        //remove shape from table position array!!
        tablePosition.remove(shape);
        repaint();
    }

    public DShape getSelectedShape() {
        return this.selectedShape;
    }

    public void setSelectedShape(DShape shape) {
        this.selectedShape = shape;
    }

    private DShape findShapeForLocation(int x, int y) {
        DShape shape;
        for (int i = shapes.size() - 1; i >= 0; i--) {
            shape = shapes.get(i);
            if (shape.containsPoint(x, y)) {
                return shape;
            }
        }
        return null;

    }

    public void displayCanvas() {
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
        setBackground(Color.WHITE);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    /*
     * paintComponent() method:
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DShape shape : shapes) {
            shape.draw(g);
        }

    }

    /*
     * moveFront() method: only works if a shape is selected
     */
    public void moveFront() {
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            shapes.add(selectedShape);
        }
        repaint();
    }

    public void moveBack() {
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            shapes.add(0, selectedShape);
        }
        repaint();
    }

    public void addShape(DShape shape) {
        shapes.add(shape);
        repaint();
    }

    /*
     * method clear all shapes from canvas for opening files
     */
    public void clear() {
        shapes.clear();
        repaint();
    }

    /*
     Returns models for all shapes in shapes list (for saving file)
     @return a DShapeModel list
     */
    public List<DShapeModel> getModels() {
        List<DShapeModel> models = new ArrayList<DShapeModel>();
        for (DShape s : shapes) {
            models.add(s.getdShapeModel());
        }
        return models;
    }

    /*
     Adds shape to canvas (for opening files)
     */
    public void doAdd(DShapeModel model) {
        //use the model to create a shape
        if (model instanceof DRectModel) {
            DRect shape = new DRect((DRectModel) model);
            shapes.add(shape);
        } else if (model instanceof DOvalModel) {
            DOval shape = new DOval((DOvalModel) model);
            shapes.add(shape);
        } else if (model instanceof DLineModel) {
            DLine shape = new DLine((DLineModel) model);
            shapes.add(shape);
        } else if (model instanceof DTextModel) {
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

    public void setText(String newText) {
        if (selectedShape instanceof DText) {
            selectedShape.setText(newText);
        }
    }

    public void setShapeFont(String s) {
        if (selectedShape instanceof DText) {
            selectedShape.setFontName(s);
        }
    }

    public void table(DShape d) {
        tableInfo[0] = d.getdShapeModel().getX();
        tableInfo[1] = d.getdShapeModel().getY();
        tableInfo[2] = d.getdShapeModel().getWidth();
        tableInfo[3] = d.getdShapeModel().getHeight();
        int correctIndex = tablePosition.indexOf(d);

        try {
            //note: correctIndex corresponds with row
            tableModel.setValueAt(Math.abs(tableInfo[0]), correctIndex, 0);
            tableModel.setValueAt(Math.abs(tableInfo[1]), correctIndex, 1);
            tableModel.setValueAt(Math.abs(tableInfo[2]), correctIndex, 2);
            tableModel.setValueAt(Math.abs(tableInfo[3]), correctIndex, 3);
        } catch (Exception e) {
            //do nothing
        }

    }

    /*
     In whiteboard, shape is added to table when created
     (Method is used in button Action Listeners)
     */
    public void addShapeToTable(DShape newShape) {
        //store shape's table information in array
        tableInfo[0] = newShape.getdShapeModel().getX();
        tableInfo[1] = newShape.getdShapeModel().getY();
        tableInfo[2] = newShape.getdShapeModel().getHeight();
        tableInfo[3] = newShape.getdShapeModel().getWidth();

        tableModel.addRow(tableInfo);
        tablePosition.add(newShape);
    }

    public TableModel getModel() {
        return tableModel;
    }

}
