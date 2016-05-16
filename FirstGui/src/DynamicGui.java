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
GroupLayout layout = new GroupLayout(panel);
ArrayList<GroupLayout.ParallelGroup> pGroups = new ArrayList<GroupLayout.ParallelGroup>();
ArrayList<GroupLayout.SequentialGroup> sGroups = new ArrayList<GroupLayout.SequentialGroup>();

public DynamicGui()
{
 super("Floater Hour Calculator");
 //panel.setLayout(new FlowLayout());
 panel.setLayout(layout);

 // Turn on automatically adding gaps between components
 layout.setAutoCreateGaps(true);

 // Turn on automatically creating gaps between components that touch
 // the edge of the container and the container.
 layout.setAutoCreateContainerGaps(true);
 
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
 //JScrollPane scrollFrame= new JScrollPane(panel);
 //scrollFrame.setViewportView(panel);
 //scrollFrame.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 //this.add(scrollFrame);
}

public void actionPerformed(ActionEvent evt)
{	clientNum++;
GroupLayout.ParallelGroup hGroup = layout.createParallelGroup();
 pGroups.add(hGroup);
GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
 sGroups.add(vGroup);
  //hGroups.add(clientNum-1, hGroup);
 panel.add(new JLabel("Client Name"));
 clientNames.add(clientNum-1, new JFormattedTextField("First Name, Last Name"));
 panel.add(clientNames.get(clientNum-1));
 //panel.add(new JFormattedTextField("First Name, Last Name"));	
 boxes.add(clientNum-1, new JCheckBox("Present|"));
 panel.add(boxes.get(clientNum-1));
 panel.add(new JLabel("Time in/out:"));
 times.add(clientNum-1, new JFormattedTextField("1/4hrs"));
 panel.add(times.get(clientNum-1));
 //panel.add(new JFormattedTextField("9:30/2:30"));
 panel.add(new JLabel("One To One Workers:"));
 names1.add(clientNum-1, new JFormattedTextField("First Name, Last Name"));
 panel.add(names1.get(clientNum-1));
 //panel.add(new JFormattedTextField("First Name, Last Name"));
 panel.add(new JLabel("Hours Worked:"));
 //panel.add(new JFormattedTextField("0"));
 hours1.add(clientNum-1, new JFormattedTextField("0"));
 panel.add(hours1.get(clientNum-1));
 names2.add(clientNum-1, new JFormattedTextField("First Name, Last Name"));
 panel.add(names2.get(clientNum-1));
 //panel.add(new JFormattedTextField("First Name, Last Name"));
 panel.add(new JLabel("Hours Worked:"));
 //panel.add(new JFormattedTextField("0"));
 hours2.add(clientNum-1, new JFormattedTextField("0"));
 panel.add(hours2.get(clientNum-1));
 names3.add(clientNum-1, new JFormattedTextField("First Name, Last Name"));
 panel.add(names3.get(clientNum-1));
 //panel.add(new JFormattedTextField("First Name, Last Name"));
 panel.add(new JLabel("Hours Worked:"));
 //panel.add(new JFormattedTextField("0"));
 hours3.add(clientNum-1, new JFormattedTextField("0"));
 panel.add(hours3.get(clientNum-1));
 panel.add(new JLabel("Hours Left For Billing:"));
 //panel.add(new JFormattedTextField("0"));
 results.add(clientNum-1, new JFormattedTextField("0"));
 panel.add(results.get(clientNum-1));
 /*hGroup.addGroup(layout.createSequentialGroup().addComponent(clientNames.get(clientNum-1)).addComponent(boxes.get(clientNum-1)).addComponent(times.get(clientNum-1))
		 .addComponent(names1.get(clientNum-1)).addComponent(hours1.get(clientNum-1)).addComponent(names2.get(clientNum-1)).addComponent(hours2.get(clientNum-1))
		 .addComponent(names3.get(clientNum-1)).addComponent(hours3.get(clientNum-1)).addComponent(results.get(clientNum-1)));
 layout.setHorizontalGroup(hGroup);
 vGroup.addGroup(layout.createParallelGroup().addComponent(clientNames.get(clientNum-1)).addComponent(boxes.get(clientNum-1)).addComponent(times.get(clientNum-1))
		 .addComponent(names1.get(clientNum-1)).addComponent(hours1.get(clientNum-1)).addComponent(names2.get(clientNum-1)).addComponent(hours2.get(clientNum-1))
		 .addComponent(names3.get(clientNum-1)).addComponent(hours3.get(clientNum-1)).addComponent(results.get(clientNum-1)));
 layout.setVerticalGroup(vGroup);*/
  hGroup.addGroup(layout.createSequentialGroup().addComponent(clientNames.get(clientNum-1)));
		 layout.setHorizontalGroup(hGroup);
 vGroup.addGroup(layout.createParallelGroup().addComponent(clientNames.get(clientNum-1)));
		 layout.setVerticalGroup(vGroup);
 panel.revalidate(); 
}

public void actionPerformed2(ActionEvent e){
	
}

public static void main(String[]args)
{
 DynamicGui dynamicGui=new DynamicGui();
}
}
