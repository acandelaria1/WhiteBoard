import java.awt.BorderLayout;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WhiteBoard extends JFrame implements ModelListener {
	final static String title = "WhiteBoard";
	
	class ControlPanel extends JPanel{
		ControlPanel(){
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			JPanel addShapePanel = new JPanel();
			JLabel addLabel = new JLabel("Add");
			JButton rectButton, ovalButton, lineButton, textButton;
			List<JButton> shapeButtons; 
	
			rectButton = new JButton("Rect");
			ovalButton = new JButton("Oval");
			lineButton = new JButton("Line");
			textButton = new JButton("Text");
			
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
		}
	}
	
	
	public WhiteBoard(){
		displayGui();
	}
	
	
	public void displayGui(){
		JFrame theFrame = new JFrame(title);
		theFrame.setLayout(new BorderLayout());
		theFrame.add(new Canvas(), BorderLayout.CENTER);
		theFrame.add(new ControlPanel(), BorderLayout.WEST);
		theFrame.setVisible(true);
		theFrame.pack();
		
	}
	
	
	
	
	@Override
	public void modelChanged() {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args){
		WhiteBoard obj = new WhiteBoard();
	}
}
