import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; 
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;

@SuppressWarnings("serial")
public class RandomListUI extends JFrame{

	public final String TITLE = "Random";
	public final String LIST_FILE = "list";
	
	private ArrayList<String> people;
	private int numOfPeople;	
	
	/*LISTENERS*/
	private ExitListener windowListener;
	private AddListener addListener;
	private RemoveListener removeListener;
	private RemoveAllListener removeAllListener;
	private RollListener rollListener;
	private PickListener pickListener;
	private ChampListener champListener;
	
	/*Components*/
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnRemoveAll;
	private JButton btnRoll;
	private JButton btnPick;
	private JButton btnChamplist;
	private JTextField txtName;
	private JList<String> lstNames;
	private JLabel lblPick;
	
	private GridBagLayout layout;

	public RandomListUI(){

		people = new ArrayList<String>();
		numOfPeople = 0;

		windowListener = new ExitListener();
		addListener = new AddListener();
		removeListener = new RemoveListener();
		removeAllListener = new RemoveAllListener();
		rollListener = new RollListener();
		pickListener = new PickListener();
		champListener = new ChampListener();
		
		layout = new GridBagLayout();
		
		//Set appearance
		setTitle(TITLE);
		setLayout(layout);
		setResizable(false);
		
		addWindowListener(windowListener);
		
		lblPick = new JLabel("");
		lblPick.setFont(new Font("Arial", Font.BOLD, 25));
		lblPick.setPreferredSize(new Dimension(250,50));
		lblPick.setMinimumSize(new Dimension(250,50));
		lblPick.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnAdd = new JButton("Add name");
		btnAdd.addActionListener(addListener);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(removeListener);
		
		btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.addActionListener(removeAllListener);
		
		btnRoll = new JButton("Let's roll");
		btnRoll.addActionListener(rollListener);
	 
		btnPick = new JButton("Pick one");
		btnPick.addActionListener(pickListener);
	 
		btnChamplist = new JButton("List of Champs");
		btnChamplist.addActionListener(champListener);
		
		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(125,28));
		txtName.setFont(new Font("Arial",Font.PLAIN,18));
		txtName.addActionListener(addListener);		

		lstNames = new JList<String>(new DefaultListModel<String>());
		lstNames.setFont(new Font("Arial",Font.PLAIN,18));
		lstNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(lstNames);
		scrollPane.setPreferredSize(new Dimension(300,250));
		scrollPane.setMinimumSize(new Dimension(300,250));
		
		addWidget(txtName, 2, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0));
		addWidget(btnAdd, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,2,0,0));
		addWidget(btnRemove, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,2,0,0));
		addWidget(btnRemoveAll, 3, 2, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,2,0,0));
		addWidget(btnRoll, 2, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,2,0,0));
		addWidget(btnPick, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,2,0,0));
		addWidget(btnChamplist, 2, 4, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,2,0,0));
		addWidget(scrollPane, 0, 0, 2, 7, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0));
		
		setLocationRelativeTo(null);
		
	}
	
	public void addWidget(Component widget, int x, int y, int w, int h, int anchor, int fill, Insets inset){

		/* 	Add the widget onto the frame depending on the grid bag layout */
	
		GridBagConstraints constraints = new GridBagConstraints(x, y, w, h, 0, 0, anchor, fill, inset , 0, 0);

		layout.setConstraints(widget, constraints);

		add(widget);
	}
	
	private class ExitListener extends WindowAdapter{

		public void windowClosing(WindowEvent e){
			
			RandomListUI frame = (RandomListUI)e.getWindow();
			
			try{		
			
				// Pause the program
				Thread.sleep(750);
				
			}catch(InterruptedException i){
				i.printStackTrace();
			};
			
			/* Terminates the program */
			frame.setVisible(false);
			frame.dispose();
			System.exit(0);
		
		}
	}
	
	private class AddListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			// Get the model of the list
			DefaultListModel<String> dlm = (DefaultListModel<String>)lstNames.getModel();
			
			// Get the contact from the text field
			String input = txtName.getText();
			input = input.trim();
		
			/* If the input is not empty, add the contact to the list*/
			if(!input.equals(null) && !input.equals("")){
				// Add the contact to the end of the list
				people.add(input);
				dlm.addElement(++numOfPeople + ". " + input);
			}
			
			/* Pause the program for a moment*/
			try{
				Thread.sleep(50);
			}
			catch(InterruptedException i){
				i.printStackTrace();
			}
			
			// Reset the text field to empty
			txtName.setText(null);
		
		}
	}
	private class RemoveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			// Get the model of the list
			DefaultListModel<String> dlm = (DefaultListModel<String>)lstNames.getModel();		
			
			// Get the selected index for which the user have chosen
			int index = lstNames.getSelectedIndex();
			
			/* If a selection has been made, then remove the selection from the list*/
			if(index != -1){			
				// Remove the contact from the list
				dlm.removeElementAt(index);
				people.remove(index);
				numOfPeople--;
				
				//Re-order if element removed is not the last element in the scroll list
				if(index <= people.size()-1){
					dlm.removeRange(index,(people.size()-1));
					
					for(int i = index; i < people.size(); i++){
						dlm.addElement((i+1) + ". " + people.get(i));
					}
				}
			}
		}
	}
	
	private class RollListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
	 		
			if(numOfPeople > 0){
				// Get the model of the list
				DefaultListModel<String> dlm = (DefaultListModel<String>)lstNames.getModel();
				
				dlm.removeAllElements();
				
				int[] occured = new int[numOfPeople];
				for(int i = 0; i < occured.length; i++){
					occured[i] = -1;
				}
				ArrayList<String> reOrderedNames = new ArrayList<String>();
				int randomNum;
				int counter = 0;
				while (counter < numOfPeople){
					randomNum = new Random().nextInt(numOfPeople);
						
					if(!reoccurred(occured, randomNum)){
				
						occured[counter] = randomNum;
						
						String randomName = people.get(randomNum);
						dlm.addElement(++counter + ". " + randomName);
						reOrderedNames.add(randomName);
						
					}
				}
				
				people.clear();
				people.addAll(reOrderedNames);
			}
		}
		
		public boolean reoccurred(int[] array, int num){
			int counter = 0;
			while(counter < array.length){
				
				if(array[counter] == num){
					return true;
				}
				counter++;
			}
			return false;
		
		}
	}
	
	private class RemoveAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			// Get the model of the list
			DefaultListModel<?> dlm = (DefaultListModel<?>)lstNames.getModel();
			
			dlm.removeAllElements();
			people.clear();
			numOfPeople = 0;			
		}
	}
	
	private class PickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(numOfPeople > 0){
				JComponent widgetUsed = (JComponent)e.getSource();
				RandomListUI f = (RandomListUI) widgetUsed.getRootPane().getParent();
				int pick = new Random().nextInt(numOfPeople);
				lblPick.setText(people.get(pick));
				JOptionPane.showMessageDialog(f, lblPick, "BOOM!", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private class ChampListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DefaultListModel<String> dlm = (DefaultListModel<String>)lstNames.getModel();
			Scanner s = null;
			
			dlm.removeAllElements();
			people.clear();
			numOfPeople = 0;
			
			try {
				s = new Scanner (new File(LIST_FILE));		
				
			} catch (FileNotFoundException e1) {e1.printStackTrace();}
			
			while(s.hasNext()){
				String champ = s.nextLine();
				people.add(champ);
				numOfPeople++;
			}
			
			for(String champ : people){
				dlm.addElement(champ);
			}
		}
	}
}