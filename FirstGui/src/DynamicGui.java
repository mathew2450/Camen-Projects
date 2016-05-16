import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DynamicGui extends JFrame implements ActionListener
{
JPanel panel = new JPanel();
JPanel minipanel = new JPanel();
int clientNum = 0;
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
ArrayList<Client> clients= new ArrayList<Client>();
Dimension x = new Dimension(2500,2000);
BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
ArrayList<GroupLayout.ParallelGroup> pGroups = new ArrayList<GroupLayout.ParallelGroup>();
ArrayList<GroupLayout.SequentialGroup> sGroups = new ArrayList<GroupLayout.SequentialGroup>();

public DynamicGui()
{
 super("Floater Hour Calculator");
 //panel.setLayout(new FlowLayout());
 panel.setLayout(layout);
 
 add(panel,BorderLayout.CENTER); 
 JButton button=new JButton("Add Client");
 add(button,BorderLayout.NORTH);
 button.addActionListener(this);
 JButton button2=new JButton("Calculate");
 add(button2, BorderLayout.SOUTH);
 button2.addActionListener(this);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setSize(2500,2000);
 setVisible(true);
 
 minipanel.setLayout(new BoxLayout(minipanel, BoxLayout.X_AXIS));
 	minipanel.add(new JLabel("Client Name"));
 	clientNames.add(clientNum, new JFormattedTextField("First Name, Last Name"));
 	minipanel.add(clientNames.get(clientNum));	
 	boxes.add(clientNum, new JCheckBox("Present|"));
 	minipanel.add(boxes.get(clientNum));
 	minipanel.add(new JLabel("Time in/out:"));
 	times.add(clientNum, new JFormattedTextField("1/4hrs"));
 	minipanel.add(times.get(clientNum));
 	minipanel.add(new JLabel("One To One Workers:"));
 	names1.add(clientNum, new JFormattedTextField("First Name, Last Name"));
 	minipanel.add(names1.get(clientNum));
 	minipanel.add(new JLabel("Hours Worked:"));
 	hours1.add(clientNum, new JFormattedTextField("0"));
 	minipanel.add(hours1.get(clientNum));
 	names2.add(clientNum, new JFormattedTextField("First Name, Last Name"));
 	minipanel.add(names2.get(clientNum));
 	minipanel.add(new JLabel("Hours Worked:"));
 	hours2.add(clientNum, new JFormattedTextField("0"));
 	minipanel.add(hours2.get(clientNum));
 	names3.add(clientNum, new JFormattedTextField("First Name, Last Name"));
 	minipanel.add(names3.get(clientNum));
 	minipanel.add(new JLabel("Hours Worked:"));
 	hours3.add(clientNum, new JFormattedTextField("0"));
 	minipanel.add(hours3.get(clientNum));
 	minipanel.add(new JLabel("Hours Left For Billing:"));
 	results.add(clientNum, new JFormattedTextField("0"));
 	minipanel.add(results.get(clientNum));
 	minipanel.setSize(10,10);
 	minipanel.setVisible(true);
 //JScrollPane scrollFrame= new JScrollPane(panel);
 //scrollFrame.setViewportView(panel);
 //scrollFrame.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 //this.add(scrollFrame);
}

public void actionPerformed(ActionEvent evt)
{	clientNum++;
	panel.add(minipanel);
 panel.revalidate(); 
}

public void actionPerformed2(ActionEvent e){
	
}

public static void main(String[]args)
{
 DynamicGui dynamicGui=new DynamicGui();
}
}
