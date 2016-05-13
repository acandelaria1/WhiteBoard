import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class WhiteBoard extends JFrame implements ModelListener {
	final static String title = "WhiteBoard";
	private Canvas canvas;
	private Mode whiteBoardMode;
	
	
	class ControlPanel extends JPanel{
		ControlPanel(){
			
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			//Add networkingPanel
			JPanel networkingPanel = new JPanel();
			JButton serverStartButton, clientStartButton;
			JLabel networkStatus = new JLabel("Status: Normal");
			serverStartButton = new JButton("Start Server");
			clientStartButton = new JButton("Start Client");
			networkingPanel.add(serverStartButton);
			networkingPanel.add(clientStartButton);
			networkingPanel.add(networkStatus);
			this.add(networkingPanel);
			
			//Add addShapePanel
			JPanel addShapePanel = new JPanel();
			JLabel addLabel = new JLabel("Add");
			JButton rectButton, ovalButton, lineButton, textButton;
			List<JButton> shapeButtons; 
			
			rectButton = new JButton("Rect");
			ovalButton = new JButton("Oval");
			lineButton = new JButton("Line");
			textButton = new JButton("Text");
			
			//Add button listener for add Rect Button
			rectButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DRect rect = new DRect();
					canvas.addShape(rect); //add view to canvas
					
				}
				
				
			});
                        
                        //Add button listener for add Oval Button
			ovalButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DOval oval = new DOval();
					canvas.addShape(oval); //add view to canvas
					
				}
				
				
			});
                        
                        //Add button listener for add Line Button
			lineButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DLine line = new DLine();
					canvas.addShape(line); //add view to canvas
					
				}
				
				
			});
                        
                        //Add button listener for add Text Button
			textButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DText text = new DText();
					canvas.addShape(text); //add view to canvas
					
				}
				
				
			});         
			shapeButtons = new ArrayList<>(Arrays.asList(rectButton, ovalButton, lineButton, textButton));
			addShapePanel.add(addLabel);
			for(JButton b: shapeButtons){
				addShapePanel.add(b);
			}
			this.add(addShapePanel);
		
			
			//Set Color Panel
			JPanel setColorPanel = new JPanel();
			JButton setColorButton = new JButton("Set Color");
			final JColorChooser colorChooser = new JColorChooser();
			setColorButton.addActionListener(new ActionListener ()
			{
				public void actionPerformed(ActionEvent i)
				{
					canvas.chooseColor();
								
				}
		
			});
			setColorPanel.add(setColorButton);
			this.add(setColorPanel);
			
			//TextInputPanel
			JPanel textInputPanel = new JPanel();
			JTextField inputTextField = new JTextField(title+"!");
			JButton fontButton = new JButton("Font Name");
			textInputPanel.add(inputTextField);
			textInputPanel.add(fontButton);
			this.add(textInputPanel);
			
			//Shape Operations Panel
			JPanel shapeOperationsPanel = new JPanel();
			JButton moveFrontButton = new JButton("Move To Front");
			JButton moveBackButton = new JButton("Move To Back");
			JButton removeShapeButton = new JButton("Remove Shape");
			List<JButton> opButtons;
			opButtons = new ArrayList<>(Arrays.asList(moveFrontButton, moveBackButton, removeShapeButton));
			
			for(JButton b: opButtons){
				shapeOperationsPanel.add(b);
			}
			
			this.add(shapeOperationsPanel);
			
			
			//Add JTable Panel
			// Creates a default table model so that we can add columns and rows
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("X");
			tableModel.addColumn("Y");
			tableModel.addColumn("Width");
			tableModel.addColumn("Height");
			// Create a JTable with the defaulttablemodel
			JTable table = new JTable(tableModel);
			// Have to add it to a JScrollPane or else it won't appear
			JScrollPane scrollPane = new JScrollPane(table);
			this.add(scrollPane);
			
			//Add Save and Open Buttons Panel
			JPanel filePanel = new JPanel();
			JButton saveButton, openButton, saveImageButton;
			saveButton = new JButton("Save");
			openButton = new JButton("Open");
			saveImageButton = new JButton("Save Image");
			
			
			//add ActionListener for save button
            //prompt user for filename
            saveButton.addActionListener(new ActionListener(){

	@Override
	public void actionPerformed(ActionEvent e) {
		String savedName = JOptionPane.showInputDialog("Enter in a file name: ", null);
                            if(savedName != null)
                            {
                                File f = new File(savedName);
                                save(f);
                            }
				}
            });
            
            //add ActionListener for open button
            openButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(whiteBoardMode == Mode.NORMAL){
                        String fileName = JOptionPane.showInputDialog("Enter in a file name: ", null);
                        if(fileName != null)
                            {
                                File f = new File(fileName);
                                open(f);
                            }
                        }
                    }
            
            });
            
            //add ActionListener for save image button
            saveImageButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fileName = JOptionPane.showInputDialog("Enter in a file name: ", null);
                        if(fileName != null)
                            {
                                File f = new File(fileName);
                                saveImage(f);
                            }
                        }
            
            });
			filePanel.add(openButton);
			filePanel.add(saveButton);
			filePanel.add(saveImageButton);
			this.add(filePanel);
			

		}
	}
	
	
	public WhiteBoard(){
		this.whiteBoardMode = Mode.NORMAL;
		displayGui();
	}
	
	
	public void displayGui(){
		JFrame theFrame = new JFrame(title);
		theFrame.setLayout(new BorderLayout());
		Canvas canvas = new Canvas();
		this.canvas = canvas;
		theFrame.add(canvas, BorderLayout.CENTER);
		theFrame.add(new ControlPanel(), BorderLayout.WEST);
		theFrame.setVisible(true);
		theFrame.pack();
		
	}
	
	
	
	
	@Override
	public void modelChanged() {
		// TODO Auto-generated method stub
		repaint();
	}
	
	 /*
    Method saves DShapeModels
    */
    public void save(File file){
        try {
            XMLEncoder xmlOut = new XMLEncoder(
                    new BufferedOutputStream(
                    new FileOutputStream(file)));
            List<DShapeModel> modelList = canvas.getModels();
            DShapeModel[] modelArray = modelList.toArray(new DShapeModel[0]);
            xmlOut.writeObject(modelArray);       
            xmlOut.close();
        }
        
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void saveImage(File file){
    	//Take away knobs by iterating through canvas.shapes and setting selected to false
    	for(DShape shape : canvas.getShapes()){
    		shape.setSelected(false);
    	}
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvas.paint(image.getGraphics());
        
        try{
            javax.imageio.ImageIO.write(image, "PNG", file);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void open(File file){
        DShapeModel[] modelArray = null;
        try{
            XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(
            new FileInputStream(file)));
            //read in array of DShapes
            modelArray = (DShapeModel[]) xmlIn.readObject();
            xmlIn.close();
            //wipe out old view
            canvas.clear();
            //create shapes from model array and add to view
            for(DShapeModel d : modelArray){
                canvas.doAdd(d);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception ignored){
		}
		WhiteBoard obj = new WhiteBoard();
	}
}