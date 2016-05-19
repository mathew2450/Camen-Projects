
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;


@SuppressWarnings("serial")
public class DynamicGui extends JPanel{
	int clientNum = 0, floaterNum = 0;
	JPanel mainPanel = new JPanel();
	 ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	 ArrayList<JFormattedTextField> times= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> clientNames= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> names1= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> names2= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> names3= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> hours1= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> hours2= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> hours3= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> results= new ArrayList<JFormattedTextField>();
	 ArrayList<String> floaters = new ArrayList<String>();  
	 ArrayList<Integer> floaterhrs = new ArrayList<Integer>();
	 Action calc;
	 Action newClient;
    public DynamicGui() {
    	
        super(new BorderLayout());
        
        calc = new Calc();
        newClient = new NewClient();

        
        JButton addClient = new JButton(newClient);
        addClient.setText("Add A New Client");
        JButton calculate = new JButton(calc);
        calculate.setText("Calculate");
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        //mainPanel.add(createYAlignmentExample());
        Dimension maxsize = new Dimension(2000, 10000);
        Dimension minsize = new Dimension(2000, 50);
        mainPanel.setMaximumSize(maxsize);
        mainPanel.setMinimumSize(minsize);
        mainPanel.setPreferredSize(maxsize);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
        add(addClient, BorderLayout.NORTH);       
        add(calculate, BorderLayout.SOUTH);
        
    }
 
    protected JPanel createClientInfoPanel() {
        JPanel pane = new JPanel();
        String title = "Client " + (clientNum+1);
 
        JComponent minipanel = new JPanel();
        Dimension size = new Dimension(1500, 50);
        minipanel.setAlignmentX(LEFT_ALIGNMENT);
        minipanel.setMaximumSize(size);
        minipanel.setPreferredSize(size);
        minipanel.setMinimumSize(size);
        TitledBorder border = new TitledBorder(
                                  new LineBorder(Color.black),
                                  "",
                                  TitledBorder.CENTER,
                                  TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        minipanel.setBorder(border);
        minipanel.add(new JLabel("Client Name"));
        //removeclientnum
        clientNames.add(clientNum, new JFormattedTextField("FirstName LastName"+clientNum));
        minipanel.add(clientNames.get(clientNum));	
        boxes.add(clientNum, new JCheckBox("Present |"));
        minipanel.add(boxes.get(clientNum));
        minipanel.add(new JLabel("Time in-out:"));
        times.add(clientNum, new JFormattedTextField("9:30-2:30"));
        minipanel.add(times.get(clientNum));
        minipanel.add(new JLabel("One To One Workers:"));
        names1.add(clientNum, new JFormattedTextField("FirstName LastName"));
        minipanel.add(names1.get(clientNum));
        minipanel.add(new JLabel("Hours Worked:"));
        hours1.add(clientNum, new JFormattedTextField("0"));
        minipanel.add(hours1.get(clientNum));
        names2.add(clientNum, new JFormattedTextField("FirstName LastName"));
        minipanel.add(names2.get(clientNum));
        minipanel.add(new JLabel("Hours Worked:"));
        hours2.add(clientNum, new JFormattedTextField("0"));
        minipanel.add(hours2.get(clientNum));
        names3.add(clientNum, new JFormattedTextField("FirstName LastName"));
        minipanel.add(names3.get(clientNum));
        minipanel.add(new JLabel("Hours Worked:"));
        hours3.add(clientNum, new JFormattedTextField("0"));
        minipanel.add(hours3.get(clientNum));
        minipanel.add(new JLabel("Hours Left For Billing:"));
        results.add(clientNum, new JFormattedTextField("0"));
        results.get(clientNum).setEditable(false);
        minipanel.add(results.get(clientNum));
   
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setAlignmentX(LEFT_ALIGNMENT);
        pane.add(minipanel, pane);
        pane.revalidate();
        return pane;
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DynamicGui.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
public class Calc extends AbstractAction{
    public void actionPerformed(ActionEvent e) {    
    	Scanner fileScanner;
		try {
			fileScanner = new Scanner(new File("floaters.txt"));
			
		
    	while (fileScanner.hasNext()){
    	   floaters.add(fileScanner.next()+" "+fileScanner.next());
    	   floaterhrs.add(fileScanner.nextInt()); 	
    	   floaterNum++;
    	}
    	} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int totalResults = 0; 
    	for(int i = 0; i<clientNum; i++){
    		
    		//System.out.println(boxes.get(i).isSelected());
    		if(boxes.get(i).isSelected()==false)
    			results.get(i).setText("0");
    		else
    		{
    			int hrs1 = Integer.parseInt(hours1.get(i).getText(), 10);
    			int hrs2 = Integer.parseInt(hours2.get(i).getText(), 10);
    			int hrs3 = Integer.parseInt(hours3.get(i).getText(), 10);
    			int hoursLeft = 8 - (hrs1 + hrs2 + hrs3);
    			totalResults += hoursLeft;
    			String hoursUsed = "" + (hoursLeft);
    			results.get(i).setText(hoursUsed);   			
    		}
    		
    	}
    	try {
    		int totalHrs = 0, hoursNeeded = 0;
    		for(int i = 0; i<floaterNum; i++)
    			totalHrs += floaterhrs.get(i);
    	if(totalResults < totalHrs )
    			hoursNeeded = totalHrs - totalResults;
    	//int temp = totalResults;
    	// The name of the file to open.
    	String fileName = "Floater_Hours.txt";
    	// Assume default encoding.
    	FileWriter fileWriter = new FileWriter(fileName);
    	FileWriter fileSaver = new FileWriter("Client_List.txt");

    	// Always wrap FileWriter in BufferedWriter.
    	BufferedWriter bufferedWriter =
    	new BufferedWriter(fileWriter);
    	BufferedWriter bufferedWriter2 =
    	new BufferedWriter(fileSaver);
    	String here;
    	bufferedWriter2.write("FirstName LastName Present Time One2OneFirst Last Hours One2OneFirst Last Hours One2OneFirst Last Hours FloaterHours");
    	for(int j = 0; j<clientNum; j++){
    		if(boxes.get(j).isSelected()==true)
    			here = "true";
    		else
    			here = "false";
    		bufferedWriter2.newLine();
    		bufferedWriter2.write(clientNames.get(j).getText() + " " + here +
    						" " + times.get(j).getText() + " " + names1.get(j).getText() 
    						+ " " + hours1.get(j).getText() + " " + names2.get(j).getText() 
    						+ " " + hours2.get(j).getText() + " " + names3.get(j).getText() 
    						+ " " + hours3.get(j).getText() + " " + results.get(j).getText() 
    						+ " ");
    		bufferedWriter2.newLine();
    		bufferedWriter2.newLine();
    	}
    	
    	bufferedWriter2.close();

    	// Note that write() does not automatically
    	// append a newline character.
    	bufferedWriter.write("Not Enough Hours for Floaters (" + hoursNeeded + ") More Hours are Needed.");
    	bufferedWriter.newLine();
    	for(int i = 0; i<floaters.size(); i++){
    		int temp = floaterhrs.get(i);
    		bufferedWriter.write(floaters.get(i));
    		bufferedWriter.newLine();
    		for(int j = 0; j<clientNum; j++){	
    			
    	if(Integer.parseInt(results.get(j).getText()) != 0){	
    		if(temp == 0){
    				j = clientNum;
    				}
    		else{
    			if(temp>=Integer.parseInt(results.get(j).getText()) && Integer.parseInt(results.get(j).getText()) > 0){
    				temp-=Integer.parseInt(results.get(j).getText());
    				bufferedWriter.write("     " + clientNames.get(j).getText() + " Hours: " + results.get(j).getText() +
    						" Between: " + times.get(j).getText());
    				bufferedWriter.newLine();
    				results.get(j).setText("0");
    				if(temp == 0){
        				j = clientNum;
        				temp = floaterhrs.get(i);}
    			}
    			
    			else{
    				bufferedWriter.write("     " + clientNames.get(j).getText() + " Hours: " + temp +
    						" Between: " + times.get(j).getText());
    				bufferedWriter.newLine();
    				temp = Integer.parseInt(results.get(j).getText()) - temp;
    				results.get(j).setText("" + temp);
    				temp=floaterhrs.get(i);
    				j = clientNum;
    			}
    		}
    	}
    		}
    	}
    	
    		
    		// Always close files.
    		bufferedWriter.close();}
    	catch(IOException ex) {
    		ex.printStackTrace();
    		            // Or we could just do this:
    		            // ex.printStackTrace();
    		        }
    	
    }
    }


    
public class NewClient extends AbstractAction{
    public void actionPerformed(ActionEvent e) {
    	mainPanel.add(createClientInfoPanel());
    	mainPanel.revalidate();
    	clientNum++;
    }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Floater Hour Computer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        DynamicGui newContentPane = new DynamicGui();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

