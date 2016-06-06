
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
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.UnsupportedLookAndFeelException;



@SuppressWarnings("serial")
public class DynamicGui extends JPanel{
	int clientNum = 0, floaterNum = 0;
	JPanel mainPanel = new JPanel();
	 ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	 ArrayList<JCheckBox> hrLimit = new ArrayList<JCheckBox>();
	 ArrayList<JFormattedTextField> times= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> clientNames= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> names1= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> names2= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> names3= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> hours1= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> hours2= new ArrayList<JFormattedTextField>();
	 ArrayList<JFormattedTextField> hours3= new ArrayList<JFormattedTextField>();
	 @SuppressWarnings("rawtypes")
	ArrayList<JComboBox> levels1= new ArrayList<JComboBox>();
	 @SuppressWarnings("rawtypes")
	ArrayList<JComboBox> levels2= new ArrayList<JComboBox>();
	 @SuppressWarnings("rawtypes")
	ArrayList<JComboBox> levels3= new ArrayList<JComboBox>();
	 ArrayList<JFormattedTextField> results= new ArrayList<JFormattedTextField>();
	 ArrayList<String> floaters = new ArrayList<String>();  
	 ArrayList<Integer> floaterlvl = new ArrayList<Integer>();
	 ArrayList<Integer> floaterhrs = new ArrayList<Integer>();
	 ArrayList<String> floaterTimes = new ArrayList<String>();
	 Action calc;
	 Action newClient;
	 String[] levels = new String[]{"0","1","2","3"};
	 JComboBox<String> levelSelect;
	 /*
	  * initiates the GUI
	  */
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
    /*
     * creates each of the pieces for the client information to be input. Saves each piece to its relative arraylist to be
     * read from later 
     */
    protected JPanel createClientInfoPanel() {
        JPanel pane = new JPanel();
        String title = "Client " + (clientNum+1);
 
        JComponent minipanel = new JPanel();
        Dimension size = new Dimension(1500, 70);
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
        minipanel.add(new JLabel("Level:"));
        levels1.add(clientNum, levelSelect = new JComboBox<String>(levels));
        minipanel.add(levels1.get(clientNum));
        names2.add(clientNum, new JFormattedTextField("FirstName LastName"));
        minipanel.add(names2.get(clientNum));
        minipanel.add(new JLabel("Hours Worked:"));
        hours2.add(clientNum, new JFormattedTextField("12:30-12:30"));
        minipanel.add(hours2.get(clientNum));
        minipanel.add(new JLabel("Level:"));
        levels2.add(clientNum, levelSelect = new JComboBox<String>(levels));
        minipanel.add(levels2.get(clientNum));
        names3.add(clientNum, new JFormattedTextField("FirstName LastName"));
        minipanel.add(names3.get(clientNum));
        minipanel.add(new JLabel("Hours Worked:"));
        hours3.add(clientNum, new JFormattedTextField("12:30-12:30"));
        minipanel.add(hours3.get(clientNum));
        minipanel.add(new JLabel("Level:"));
        levels3.add(clientNum, levelSelect = new JComboBox<String>(levels));
        minipanel.add(levels3.get(clientNum));
        results.add(clientNum, new JFormattedTextField("0"));
        results.get(clientNum).setEditable(false);
        hrLimit.add(clientNum, new JCheckBox("Limit to 7 hrs"));
        minipanel.add(hrLimit.get(clientNum));
        //minipanel.add(results.get(clientNum));
   
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.setAlignmentX(LEFT_ALIGNMENT);
        pane.add(minipanel, pane);
        pane.revalidate();
        return pane;
    }
 /*
  * when the button is clicked, the information is read from the GUI and the input files and the schedule is created
  * by matching quarter hours. Then the results are output to a text file.  
  */
public class Calc extends AbstractAction{
    public void actionPerformed(ActionEvent e) {    
    	/*
    	 * 
    	 * read in the floater hours and save them from floaters.txt
    	 * 
    	 */
    	Scanner fileScanner;
    	//InputStream input = DynamicGui.class.getResourceAsStream("/floaters.txt");
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	InputStream input = classLoader.getResourceAsStream("floaters.txt");
		try {
			
			fileScanner = new Scanner(new InputStreamReader(input, "UTF-8"));
			
		
    	while (fileScanner.hasNext()){
    	   floaters.add(fileScanner.next()+" "+fileScanner.next());
    	   floaterTimes.add(fileScanner.next());
    	   floaterlvl.add(fileScanner.nextInt());
    	   floaterhrs.add(calcHours(floaterTimes.get(floaterNum))); 	
    	   floaterNum++;
    	}
    	} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
    	 * 
    	 * find the number of hours left for each client 
    	 * 
    	 */
		int totalResults = 0, hoursLeft = 0; 
    	for(int i = 0; i<clientNum; i++){
    		
    		//System.out.println(boxes.get(i).isSelected());
    		if(boxes.get(i).isSelected()==false)
    			results.get(i).setText("0");
    		else
    		{
    			int hrs1 = calcHours(hours1.get(i).getText());
    			int hrs2 = calcHours(hours2.get(i).getText());
    			int hrs3 = calcHours(hours3.get(i).getText());
    			if(hrLimit.get(i).isSelected()){
    				hoursLeft = 28 - (hrs1 + hrs2 + hrs3);
    				//System.out.println("Hours limited for Client:" + i);
    			}
    			else
    				hoursLeft = 32 - (hrs1 + hrs2 + hrs3);
    			totalResults += hoursLeft;
    			String hoursUsed = "" + (hoursLeft);
    			//System.out.println(hoursLeft);
    			results.get(i).setText(hoursUsed);   			
    		}
    		
    	}
    	/*
    	 * 
    	 * find hours for each client and output the schedule to a text file
    	 * 
    	 */
    	try {
    		int totalHrs = 0, hoursNeeded = 0;
    		for(int i = 0; i<floaterNum; i++)
    			totalHrs += floaterhrs.get(i);
    	if(totalResults < totalHrs )
    			hoursNeeded = totalHrs - totalResults;
    	//System.out.println(hoursNeeded);
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
    		excess = (hoursNeeded/(floaterNum+1));
    	excess = (hoursNeeded/(floaterNum+1))+1;
    	if(hoursNeeded > 0)
    		bufferedWriter.write(hoursNeeded + " extra floater quarterhours are needed for today. One solution would be to subtract " +
    					excess + " hours from each floater.");
    	bufferedWriter.newLine();
    	/*
    	 * Go through and make each floater a time array for their hours, create time arrays for each client
    	 * Match each floater time to a client time until there are no more hours available
    	*/
    	WorkTime floaterStart, floaterEnd, clientStart, clientEnd;
    	String[] floaterSplit, clientSplit;
    	ArrayList<WorkTime> floaterArray = new ArrayList<WorkTime>();//floatertimearray
    	ArrayList<WorkTime> clientArray = new ArrayList<WorkTime>();//clientArray
    	for(int i = 0; i<floaters.size()-1; i++){
    		System.out.println("floater :" + i);
    		//int temp = floaterhrs.get(i);
    		bufferedWriter.write(floaters.get(i) + " " + floaterhrs.get(i) + " " + floaterTimes.get(i) + " Level:" + floaterlvl.get(i));
    		bufferedWriter.newLine();
    		floaterSplit = floaterTimes.get(i).split("-");
    		floaterStart = new WorkTime(floaterSplit[0]);
    		floaterEnd = new WorkTime(floaterSplit[1]);
    		if(floaterEnd.hrs <= 9)
    			floaterEnd.setHrs(floaterEnd.hrs+12);
    		if(floaterStart.hrs < 6)
    			floaterStart.setHrs(floaterStart.hrs+12);
    		floaterArray.removeAll(floaterArray);
    		floaterArray.addAll(createTimeArray(floaterStart, floaterEnd));
    		
    		
    		for(int j = 0; j<clientNum; j++){
    			for(int k = 0; k < floaterArray.size()-1; k++)
    				System.out.print(floaterArray.get(k).timeToString(false));
    			//System.out.println();
    			//System.out.println(j + "has this many quarter hours left: " + results.get(j).getText());
    			int hrsAvail = 0, timeWithClient = 0;	//maxAvail = 0, 
    			clientSplit = times.get(i).getText().split("-");
        		clientStart = new WorkTime(clientSplit[0]);
        		clientEnd = new WorkTime(clientSplit[1]);
        		if(clientEnd.hrs <= 9)
        			clientEnd.setHrs(clientEnd.hrs+12);
        		if(clientStart.hrs < 6)
        			clientStart.setHrs(clientStart.hrs+12);
        		clientArray.addAll(createTimeArray(clientStart, clientEnd));
        		hrsAvail = Integer.parseInt(results.get(j).getText());
        		if(hrsAvail > 0 && (floaterArray.size()-1) > 0){
        			floaterTimeLoop:
        			for(int f = 0; f < floaterArray.size()-1; f++){
        				System.out.println(f);
        				clientTimeLoop:
        				for(int c = 0; c < clientArray.size()-1; c++){
        					//System.out.println(f + " " + c);
        					System.out.println(floaterArray.get(f).time + " compared to " + clientArray.get(c).time);
        					if(floaterArray.get(f).isEqualTo(clientArray.get(c))){
        						System.out.println("its equal! " + floaterArray.get(f).timeToString(false) + " to " + clientArray.get(c).timeToString(false));
        						timeWithClient++;
        						if(timeWithClient == 1){
        							bufferedWriter.write("	" + clientNames.get(j).getText() + " between " + floaterArray.get(f).timeToString(false));
        						}//end if start time with client
        						floaterEnd = floaterArray.get(f);
        						floaterArray.remove(f);
        						f--;
        						if(f<0)
        							f=0;
        						if(floaterArray.size() == 0){
        							System.out.println("no more time for floater");
        							break clientTimeLoop;
        						}
        						hrsAvail--;
        						results.get(j).setText("" + hrsAvail);				
        						//c = clientArray.size()-1;
        						if(hrsAvail == 0){
        							System.out.println("no more hours for client " + j);
        							break floaterTimeLoop; 
        						}//end if avail hrs is zero	
        					}//end if equal times
        					
        				}//for each client time
        				
        			}//for each floater time
        		if(timeWithClient > 0){
    							bufferedWriter.write(" and " + floaterEnd.timeToString(false) + " for " + timeWithClient + " quarter Hours!");
    							bufferedWriter.newLine();
    							System.out.println("time between floater and client found");
    							//break floaterTimeLoop;
        			}//end if floater array has been fully traversed or hrsAvail equals 0		
        		}//end if results is not 0
    		}//end client for loop
    		
    	}//end floater for loop
    	
    	
    	int availHrs = 0;
    	for(int i = 0; i < results.size(); i++){
    		availHrs += Integer.parseInt(results.get(i).getText());
    		//System.out.println(results.get(i).getText());
    	}
    	bufferedWriter.newLine();
    	if(availHrs > 0){
    		bufferedWriter.write("There are " + availHrs + " quarter-hours left to be scheduled");
    		bufferedWriter.newLine();
    		for(int i = 0; i < results.size(); i++){
        		if(Integer.parseInt(results.get(i).getText()) > 0){
        			bufferedWriter.write("	There are " + results.get(i).getText() + " quarter-hours left for " + clientNames.get(i).getText());
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
    /*
     * creates a time array given the start and end times
     */
    public ArrayList<WorkTime> createTimeArray(WorkTime st, WorkTime et){

    	WorkTime tempst = new WorkTime(st.time);
    	
    	//System.out.printf(tempst.time +  " ");
		ArrayList<WorkTime> timeWorked = new ArrayList<WorkTime>();
		timeWorked.add(st);
		//System.out.printf(timeWorked.get(0).time +  " ");
		do{
			
			WorkTime temp = new WorkTime(tempst.addquarterHour().time);
			timeWorked.add(temp);
			//System.out.printf(temp.time + " ");
		}
		while(tempst.isLessThan(et));
		
		
		//System.out.println("floater array created: ");
		return(timeWorked);
	}
    

    /*
     * calculates the number of quarter hours for the given time frame
     */
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
    
    /*
     * subtracts the start time from the end time to determine number of quarter hours
     */
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


    /*
     * Adds a new panel for client information
     */
public class NewClient extends AbstractAction{
    public void actionPerformed(ActionEvent e) {
    	mainPanel.add(createClientInfoPanel());
    	mainPanel.revalidate();
    	clientNum++;
    }
    }
 
    /*
     * Create the GUI and show it. 
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Floater Hour Computer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        DynamicGui newContentPane = new DynamicGui();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
 
        frame.pack();
        frame.setVisible(true);
    }
    /*
     * set up to be runnable and start the code
     */
    public static void main(String[] args) {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

