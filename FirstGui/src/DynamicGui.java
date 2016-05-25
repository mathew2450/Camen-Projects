
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	 ArrayList<String> floaterTimes = new ArrayList<String>();
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
        hours1.add(clientNum, new JFormattedTextField("9:30-2:30"));
        minipanel.add(hours1.get(clientNum));
        names2.add(clientNum, new JFormattedTextField("FirstName LastName"));
        minipanel.add(names2.get(clientNum));
        minipanel.add(new JLabel("Hours Worked:"));
        hours2.add(clientNum, new JFormattedTextField("12:30-12:30"));
        minipanel.add(hours2.get(clientNum));
        names3.add(clientNum, new JFormattedTextField("FirstName LastName"));
        minipanel.add(names3.get(clientNum));
        minipanel.add(new JLabel("Hours Worked:"));
        hours3.add(clientNum, new JFormattedTextField("12:30-12:30"));
        minipanel.add(hours3.get(clientNum));
        //minipanel.add(new JLabel("Hours Left For Billing:"));
        results.add(clientNum, new JFormattedTextField("0"));
        results.get(clientNum).setEditable(false);
        //minipanel.add(results.get(clientNum));
   
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setAlignmentX(LEFT_ALIGNMENT);
        pane.add(minipanel, pane);
        pane.revalidate();
        return pane;
    }
    
public class Calc extends AbstractAction{
    public void actionPerformed(ActionEvent e) {    
    	Scanner fileScanner;
    	//InputStream input = DynamicGui.class.getResourceAsStream("/floaters.txt");
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	InputStream input = classLoader.getResourceAsStream("floaters.txt");
		try {
			
			fileScanner = new Scanner(new InputStreamReader(input, "UTF-8"));
			
		
    	while (fileScanner.hasNext()){
    	   floaters.add(fileScanner.next()+" "+fileScanner.next());
    	   floaterTimes.add(fileScanner.next());
    	   floaterhrs.add(calcHours(floaterTimes.get(floaterNum))); 	
    	   floaterNum++;
    	}
    	} catch (UnsupportedEncodingException e1) {
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
    			int hrs1 = calcHours(hours1.get(i).getText());
    			int hrs2 = calcHours(hours2.get(i).getText());
    			int hrs3 = calcHours(hours3.get(i).getText());
    			int hoursLeft = 32 - (hrs1 + hrs2 + hrs3);
    			totalResults += hoursLeft;
    			String hoursUsed = "" + (hoursLeft);
    			//System.out.println(hoursLeft);
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
    	bufferedWriter2.write("FirstName LastName Present Time One2OneFirst Last Hours One2OneFirst Last Hours One2OneFirst Last Hours " +
    			"FloaterHours");
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
    	int excess = 0;
    	// Note that write() does not automatically
    	// append a newline character.
    	if(hoursNeeded % floaterNum == 0)
    		excess = (hoursNeeded/floaterNum);
    	excess = (hoursNeeded/floaterNum)+1;
    	if(hoursNeeded > 0)
    		bufferedWriter.write(hoursNeeded + " extra floater quaterhours are needed for today. One solution would be to subtract " +
    					excess + " hours from each floater.");
    	bufferedWriter.newLine();
    	    	
    	WorkTime floaterStart, floaterEnd, clientStart, clientEnd;
    	String[] floaterSplit, clientSplit;
    	ArrayList<WorkTime> floaterArray = new ArrayList<WorkTime>();//clientArray
    	for(int i = 0; i<floaters.size(); i++){
    		
    		int temp = floaterhrs.get(i);
    		bufferedWriter.write(floaters.get(i) + " " + floaterhrs.get(i) + " " + floaterTimes.get(i));
    		bufferedWriter.newLine();
    		floaterSplit = floaterTimes.get(i).split("-");
    		floaterStart = new WorkTime(floaterSplit[0]);
    		floaterEnd = new WorkTime(floaterSplit[1]);
    		if(floaterEnd.hrs <= 9)
    			floaterEnd.setHrs(floaterEnd.hrs+12);
    		if(floaterStart.hrs < 6)
    			floaterStart.setHrs(floaterStart.hrs+12);
    		floaterArray.addAll(createTimeArray(floaterStart, floaterEnd));
    		
    		
    		for(int j = 0; j<clientNum; j++){
    			int hrsAvail = 0, maxAvail = 0;	
    			
    			for(int client = 0; client<clientNum; client++){
    				if(Integer.parseInt(results.get(client).getText()) > maxAvail )
    					maxAvail = Integer.parseInt(results.get(client).getText());
    			}
    			
    			
    			clientSplit = times.get(j).getText().split("-");
    			clientStart = new WorkTime(clientSplit[0]);
    			clientEnd = new WorkTime(clientSplit[1]);
    			if(clientEnd.hrs < 9)
    				clientEnd.setHrs(clientEnd.hrs+12);
    			if(clientStart.hrs < 6)
    				clientStart.setHrs(clientStart.hrs+12);
    			//clientArray = createTimeArray(clientStart, clientEnd);
    	if(Integer.parseInt(results.get(j).getText()) != 0){	
    		if(temp == 0){
    				j = clientNum;
    				}
    		else{
    			
    			if(clientStart.isLessThan(floaterStart) && clientEnd.isLessThan(floaterStart))
    				System.out.println(clientNames.get(j).getText() + " starts and leaves before floater");
    			
    			else if(clientStart.isMoreThan(floaterEnd) && clientEnd.isMoreThan(floaterEnd))
    				System.out.println(clientNames.get(j).getText() + " arrives after floater");
    			
    			else if(clientStart.isLessThan(floaterStart) && clientEnd.isMoreThan(floaterStart) && clientEnd.isLessThan(floaterEnd)){
    				hrsAvail = subTimes(floaterStart, clientEnd);
    				if(hrsAvail > Integer.parseInt(results.get(j).getText()))
    						hrsAvail = Integer.parseInt(results.get(j).getText());
    				bufferedWriter.write("	" + clientNames.get(j).getText() + " for " + hrsAvail + " quater-hours today! between "  + 
    						floaterStart.timeToString(false));
    				//bufferedWriter.newLine();
    				int tempResults = Integer.parseInt(results.get(j).getText());
    //**				
    				System.out.println();
    				for(int l = 0; l < floaterArray.size(); l++)
    					System.out.print(floaterArray.get(l).time + "^ ");
    				for(int k = 0; k < (hrsAvail); k++){	
    					floaterArray.remove(0);
    					tempResults--;
    				}
    				floaterStart = floaterArray.get(0);
    				results.get(j).setText("" + tempResults);
    				bufferedWriter.write(" - " + floaterStart.timeToString(false));
    				bufferedWriter.newLine();
    				System.out.println();
    				for(int l = 0; l < floaterArray.size(); l++)
    					System.out.print(floaterArray.get(l).time + "^ ");
    //**
    			}
    			
    			else if(clientStart.isLessThan(floaterEnd) && clientEnd.isMoreThan(floaterEnd) && clientStart.isMoreThan(floaterStart)){
    				hrsAvail = subTimes(clientStart, floaterEnd);
    				if(hrsAvail > Integer.parseInt(results.get(j).getText()))
    					hrsAvail = Integer.parseInt(results.get(j).getText());
    				
    				
    				int length = floaterArray.size() - 1;
    				int tempResults = Integer.parseInt(results.get(j).getText());
    				for(int k = length; k > (length - hrsAvail); k--){
    					floaterArray.remove(floaterArray.size()-1);
    					tempResults--;
    				}
    //**				
    				System.out.println();
    				for(int l = 0; l < floaterArray.size(); l++)
    					System.out.print(floaterArray.get(l).time + "* ");
    				bufferedWriter.write("	" + clientNames.get(j).getText() + " for " + hrsAvail + " quater-hours today between "  + 
    						floaterArray.get(floaterArray.size()-1).timeToString(false) + " - " + floaterEnd.timeToString(false));
    				bufferedWriter.newLine();
    				floaterEnd = floaterArray.get(floaterArray.size()-1);
    				results.get(j).setText("" + tempResults);
    				System.out.println();
    				for(int l = 0; l < floaterArray.size(); l++)
    					System.out.print(floaterArray.get(l).time + "* ");
    //**				
    			}
    			
    			else if(clientStart.isMoreThan(floaterStart) && clientEnd.isLessThan(floaterEnd)){
    				hrsAvail = subTimes(clientStart, clientEnd);
    				if(hrsAvail > Integer.parseInt(results.get(j).getText()))
    					hrsAvail = Integer.parseInt(results.get(j).getText());
    				bufferedWriter.write("	" + clientNames.get(j).getText() + " for " + hrsAvail + " quater-hours today between " +  
    						clientStart.timeToString(false) + " - " + clientEnd.timeToString(false));
    				bufferedWriter.newLine();
    				
    				int start = subTimes(floaterStart, clientStart);
    				int end = start + hrsAvail;
    				System.out.println();
    				System.out.println(start + "-" + end);
    				System.out.println();
    				int tempResults = Integer.parseInt(results.get(j).getText());
    //**				
    				
    				for(int l = 0; l < floaterArray.size(); l++)
    					System.out.print(floaterArray.get(l).time + "! ");
    				for(int k = start; k < (end); k++){
    					floaterArray.remove(start);
    					tempResults--;
    				}
    				results.get(j).setText("" + tempResults);
    				if(start < (floaterArray.size()-start))
    					floaterStart = floaterArray.get(start);
    				else{
    					//if (floaterArray.isEmpty() == false){
    						//if(start-2 > 0)
    							//floaterEnd = floaterArray.get(start-2);
    						//else if (start -1 > 0)
    							//floaterEnd = floaterArray.get(start-1);
    						//else 
    							floaterEnd = floaterArray.get(start-1);
    					//}
    //**							
    				}
    				System.out.println();
    				for(int l = start; l < floaterArray.size()-(start-1); l++)
    					System.out.print(floaterArray.get(l).time + "! ");
    			}
    			
    			else if(clientStart.isLessThan(floaterStart) && clientEnd.isMoreThan(floaterEnd)){
    				hrsAvail = Integer.parseInt(results.get(j).getText());
    				if(subTimes(floaterStart, floaterEnd) < hrsAvail)
    					hrsAvail = subTimes(floaterStart, floaterEnd);
    				bufferedWriter.write("	" + clientNames.get(j).getText() + " for " + hrsAvail + " quater-hours today between " + 
    						clientStart.timeToString(false));
    				//bufferedWriter.newLine();
    //**				
    				System.out.println();
    				for(int l = 0; l < floaterArray.size(); l++)
    					System.out.print(floaterArray.get(l).time + "# ");
    				if(hrsAvail >= subTimes(floaterStart, floaterEnd)){
    					floaterArray.removeAll(floaterArray);
    					results.get(j).setText("" + subTimes(floaterStart, floaterEnd));
    					j = clientNum;
    				}
    				else{
    					int tempResults = Integer.parseInt(results.get(j).getText());
    				for(int k = 0; k < (hrsAvail); k++){	
    					floaterArray.remove(0);
    					tempResults--;
    				}
    				floaterStart = floaterArray.get(1);
    				results.get(j).setText("" + tempResults);
    				}
    				System.out.println();
    				for(int l = 0; l < floaterArray.size(); l++)
    					System.out.print(floaterArray.get(l).time + "# ");
    				bufferedWriter.write(" - " + floaterStart.timeToString(false));
    				bufferedWriter.newLine();
    //**				
    			}
    		}//end of else
    	}// end of large if
    }//end of client loop
    		floaterArray.removeAll(floaterArray);
   }// end of floater loop
    	int availHrs = 0;
    	for(int i = 0; i < results.size(); i++){
    		availHrs += Integer.parseInt(results.get(i).getText());
    		//System.out.println(results.get(i).getText());
    	}
    	bufferedWriter.newLine();
    	if(availHrs > 0){
    		bufferedWriter.write("There are " + availHrs + " quater-hours left to be scheduled");
    		bufferedWriter.newLine();
    		for(int i = 0; i < results.size(); i++){
        		if(Integer.parseInt(results.get(i).getText()) > 0){
        			bufferedWriter.write("	There are " + results.get(i).getText() + " quater-hours left for " + clientNames.get(i).getText());
        			bufferedWriter.newLine();
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
    
    public ArrayList<WorkTime> createTimeArray(WorkTime st, WorkTime et){

    	WorkTime tempst = new WorkTime(st.time);
    	
    	//System.out.printf(tempst.time +  " ");
		ArrayList<WorkTime> timeWorked = new ArrayList<WorkTime>();
		timeWorked.add(st);
		//System.out.printf(timeWorked.get(0).time +  " ");
		do{
			
			WorkTime temp = new WorkTime(tempst.addQuaterHour().time);
			timeWorked.add(temp);
			//System.out.printf(temp.time + " ");
		}
		while(tempst.isLessThan(et));
		
		
		//System.out.println("floater array created: ");
		return(timeWorked);
	}
    

    
    public int calcHours(String hrs){    	
    	
    	String[] hoursWrked = hrs.split("-");
    	WorkTime startTime = new WorkTime(hoursWrked[0]);
    	WorkTime endTime = new WorkTime(hoursWrked[1]);
    	if(endTime.hrs <= 9)
    		endTime.hrs += 12;
    	if(startTime.hrs < 6)
    		startTime.hrs += 12;
    	int total = subTimes(startTime, endTime);//
    	return(total);
    }
    
    public int subTimes(WorkTime startTime, WorkTime endTime){

    	int total = endTime.hrs - startTime.hrs;
    	int mins = 0; 
    	if(endTime.mins >= startTime.mins)
    		mins = endTime.mins - startTime.mins;
    	else{
    		mins = (60 - startTime.mins) + endTime.mins;
    		total--;
    	}
    	total = (total*4)+(mins/15);
    	//System.out.println(endTime.hrs + " - " + startTime.hrs);
    	return(total);
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
    //needs to be an added implementation
    public static ArrayList<JFormattedTextField> sortResults(ArrayList<JFormattedTextField> resultsToSort){
    	return(resultsToSort);
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

