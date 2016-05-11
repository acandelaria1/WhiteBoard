import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
					// TODO Auto-generated method stub
					DRect rect = new DRect();
					DRectModel model = new DRectModel();
					model.setX(10);
					model.setY(10);
					model.setWidth(30);
					model.setHeight(30);
					model.setColor(Color.RED);
					
					canvas.addShape(rect);
					
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
			JButton saveButton, openButton;
			saveButton = new JButton("Save");
			openButton = new JButton("Open");
			filePanel.add(openButton);
			filePanel.add(saveButton);
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
