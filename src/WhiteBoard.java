import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class WhiteBoard extends JFrame implements ModelListener {

    final static String title = "WhiteBoard";
    final static String DEFAULT_PORT="39587";
    private Canvas canvas;
    private Mode whiteBoardMode;
    private java.util.List<ObjectOutputStream> outputs = new ArrayList<ObjectOutputStream>();
    
    class ClientHandler extends Thread{
    	private int port;
    	private String name;
    	ClientHandler(String name, int port){
    		this.port = port;
    		this.name = name;
    	}
    	
    	//Connects to server, loops getting messages
    	public void run(){
    		try{
    			Socket toServer = new Socket("127.0.0.1",port);
    			ObjectInputStream in = new ObjectInputStream(toServer.getInputStream());
    			while(true){
    				String xmlString = (String) in.readObject();
    				XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xmlString.getBytes()));
    				Message message = (Message) decoder.readObject();
    				System.out.println("client: read " + message);
    				//checking of commands
    				invokeToGUI(message);
    			}
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    	}
    }
    
    public void invokeToGUI(Message message){
    	final Message temp = message;
    	SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
			
			}
    		
    	});
    }
   
   public synchronized void addOutput(ObjectOutputStream out){
	   outputs.add(out);
   }
   
   class ServerAccepter extends Thread{
	   private int port;
	   ServerAccepter(int port){
		   this.port = port;
	   }
	   
	   public void run(){
		   try{
			   ServerSocket serverSocket = new ServerSocket(port);
			   while(true){
				   Socket toClient = null;
				   toClient = serverSocket.accept();
				   System.out.println("server: got client");
				   addOutput(new ObjectOutputStream(toClient.getOutputStream()));
			   }
		   }catch(IOException ex){
			   ex.printStackTrace();
		   }
	   }
   }
   
   
   
   public static class Message{
	   public String command;
	   public String message;
	   
	   public Message(){
		   command = null;
		   message = null;
	   }
	   
	   public String getCommand(){
		   return this.command;
	   }
	   
	   public void setCommand(String c){
		   this.command = c;
	   }
	   
	   public String getMessage(){
		   return this.message;
	   }
	   
	   public void setMessage(String message){
		   this.message = message;
	   }
	   
	   public String toString(){
		   return "message: " + command;
	   }
	   
   }
  

    class ControlPanel extends JPanel {
    	JButton serverStartButton, clientStartButton;
    	JLabel networkStatus;// = new JLabel("Status: Normal");
    	JPanel networkingPanel;
        ControlPanel() {

            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            //Add networkingPanel
            networkingPanel = new JPanel();
            networkStatus = new JLabel("Status: Normal");
            
            serverStartButton = new JButton("Start Server");
            clientStartButton = new JButton("Start Client");
            networkingPanel.add(serverStartButton);
            networkingPanel.add(clientStartButton);
            networkingPanel.add(networkStatus);
            this.add(networkingPanel);
            
            
            //add action listeners to network buttons
            serverStartButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JFrame portFrame = new JFrame("Server Port");
					JButton submitButton = new JButton("Submit");
					submitButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							//get text from portNumberField and store it in the server class
							//set whiteboard status
							whiteBoardMode = Mode.SERVER;
							networkStatus.setText("Status: " + Mode.SERVER.toString());
							portFrame.dispose();
						}
					});
					JPanel panel = new JPanel();
					JTextField portNumberField = new JTextField(DEFAULT_PORT);
					panel.add(new JLabel("Port Number:"));
					panel.add(portNumberField);
					panel.add(submitButton);
					
					portFrame.add(panel);
					portFrame.setVisible(true);
					portFrame.pack();
					portFrame.getRootPane().setDefaultButton(submitButton);
				}
            	
            });
            
            
            
            clientStartButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JFrame portFrame = new JFrame("Client Port");
					JButton submitButton = new JButton("Submit");
					submitButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							//get text from portNumberField and store it in the server class
							WhiteBoard clientWhiteBoard = new WhiteBoard(Mode.CLIENT);
							whiteBoardMode = Mode.SERVER;
							networkStatus.setText("Status: " + Mode.SERVER.toString());
							portFrame.dispose();
						}
					});
					JPanel panel = new JPanel();
					JTextField portNumberField = new JTextField("127.0.0.1:"+DEFAULT_PORT);
					panel.add(new JLabel("Port Number:"));
					panel.add(portNumberField);
					panel.add(submitButton);
					
					portFrame.add(panel);
					portFrame.setVisible(true);
					portFrame.pack();
					portFrame.getRootPane().setDefaultButton(submitButton);
					
				}
            	
            });
            

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
            rectButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    DRect rect = new DRect();
                    canvas.addShape(rect); //add view to canvas

                }

            });

            //Add button listener for add Oval Button
            ovalButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    DOval oval = new DOval();
                    canvas.addShape(oval); //add view to canvas

                }

            });

            //Add button listener for add Line Button
            lineButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    DLine line = new DLine();
                    canvas.addShape(line); //add view to canvas

                }

            });

            //Add button listener for add Text Button
            textButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    DText text = new DText();
                    canvas.addShape(text); //add view to canvas

                }

            });
            shapeButtons = new ArrayList<>(Arrays.asList(rectButton, ovalButton, lineButton, textButton));
            addShapePanel.add(addLabel);
            for (JButton b : shapeButtons) {
                addShapePanel.add(b);
            }
            this.add(addShapePanel);

            //Set Color Panel
            JPanel setColorPanel = new JPanel();
            JButton setColorButton = new JButton("Set Color");
            final JColorChooser colorChooser = new JColorChooser();
            setColorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent i) {
                    canvas.chooseColor();

                }

            });
            setColorPanel.add(setColorButton);
            this.add(setColorPanel);

            //TextInputPanel
            JPanel textInputPanel = new JPanel();
            JTextField inputTextField = new JTextField(title + "!");
            String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //get array of all available font names
            JComboBox fontChooser = new JComboBox(fontList);
            fontChooser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox box = (JComboBox)e.getSource();
                    String fontName = (String) box.getSelectedItem();
                    canvas.setShapeFont(fontName);
                }
            });
            
            //JButton fontButton = new JButton("Font Name");
            textInputPanel.add(inputTextField);
            //textInputPanel.add(fontButton);
            textInputPanel.add(fontChooser);
            this.add(textInputPanel);

            inputTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    //System.out.println(inputTextField.getText());
                    canvas.setText(inputTextField.getText());
                    repaint();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    //System.out.println(inputTextField.getText());
                    canvas.setText(inputTextField.getText());
                    repaint();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    //System.out.println(inputTextField.getText());
                    canvas.setText(inputTextField.getText());
                    repaint();
                }
                
            });

            //Shape Operations Panel
            JPanel shapeOperationsPanel = new JPanel();
            JButton moveFrontButton = new JButton("Move To Front");
            JButton moveBackButton = new JButton("Move To Back");
            JButton removeShapeButton = new JButton("Remove Shape");
            List<JButton> opButtons;
            opButtons = new ArrayList<>(Arrays.asList(moveFrontButton, moveBackButton, removeShapeButton));

            moveFrontButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    WhiteBoard.this.canvas.moveFront();
                }

            });

            moveBackButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    WhiteBoard.this.canvas.moveBack();

                }

            });

            removeShapeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DShape selectedShape = WhiteBoard.this.canvas.getSelectedShape();
                    WhiteBoard.this.canvas.removeShape(selectedShape);

                }
            });

            for (JButton b : opButtons) {
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
            saveButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String savedName = JOptionPane.showInputDialog("Enter in a file name: ", null);
                    if (savedName != null) {
                        File f = new File(savedName);
                        save(f);
                    }
                }
            });

            //add ActionListener for open button
            openButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (whiteBoardMode == Mode.NORMAL) {
                        String fileName = JOptionPane.showInputDialog("Enter in a file name: ", null);
                        if (fileName != null) {
                            File f = new File(fileName);
                            open(f);
                        }
                    }
                }

            });

            //add ActionListener for save image button
            saveImageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fileName = JOptionPane.showInputDialog("Enter in a file name: ", null);
                    if (fileName != null) {
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

    public WhiteBoard() {
        this.whiteBoardMode = Mode.NORMAL;
        displayGui();
    }
    
    public WhiteBoard(Mode mode) {
    	if(mode == Mode.CLIENT){
    		displayClientGui();
    	}
        //displayGui();
    }
    
    public void displayClientGui(){
    	JFrame theFrame = new JFrame("WhiteBoard Client");
    	theFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	theFrame.setLayout(new BorderLayout());
    	Canvas canvas = new Canvas();
    	theFrame.add(canvas, BorderLayout.CENTER);
    	theFrame.setVisible(true);
    	theFrame.pack();
    }

    public void displayGui() {
        JFrame theFrame = new JFrame(title);
        theFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
    public void save(File file) {
        try {
            XMLEncoder xmlOut = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(file)));
            List<DShapeModel> modelList = canvas.getModels();
            DShapeModel[] modelArray = modelList.toArray(new DShapeModel[0]);
            xmlOut.writeObject(modelArray);
            xmlOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage(File file) {
        //Take away knobs by iterating through canvas.shapes and setting selected to false
        for (DShape shape : canvas.getShapes()) {
            shape.setSelected(false);
        }
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvas.paint(image.getGraphics());

        try {
            javax.imageio.ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open(File file) {
        DShapeModel[] modelArray = null;
        try {
            XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(
                    new FileInputStream(file)));
            //read in array of DShapes
            modelArray = (DShapeModel[]) xmlIn.readObject();
            xmlIn.close();
            //wipe out old view
            canvas.clear();
            //create shapes from model array and add to view
            for (DShapeModel d : modelArray) {
                canvas.doAdd(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        WhiteBoard obj = new WhiteBoard();
    }
}
