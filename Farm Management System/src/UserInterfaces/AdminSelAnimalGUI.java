package UserInterfaces;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Management.Barn;
import Management.Chicken;
import Management.Coop;
import Management.Cow;
import Management.Farm;
import Management.Sheep;

@SuppressWarnings("serial")
public class AdminSelAnimalGUI extends JFrame {

	private JPanel contentPaneAdmin;
	private JTextField textDeleteId;
	private static JTable tableListAnimals;
	private static DefaultTableModel animalModel ;;
	private  Object [] animals ;
	private static int spinnerValue ;
	private static int quality = 1;
	private static int animaltype ;
	private JTextField textDate;
	private JTextField textMoney;
	private JButton btnAdd;
	
	

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSelAnimalGUI frame = new AdminSelAnimalGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void createTable(boolean c) {
		if (c)
			animalModel = new DefaultTableModel();
		else if(!c) {
			animalModel = (DefaultTableModel) tableListAnimals.getModel();
			animalModel.setRowCount(0);
		}
		Object [] colAnimalType = new Object [4];
		colAnimalType[0] ="ID";
		colAnimalType[1] ="ANIMAL TYPE";
		colAnimalType[2] ="GENDER";
		colAnimalType[3] ="QUALITY";
		animalModel.setColumnIdentifiers(colAnimalType);
		animals = new Object [4] ;
		
            for (Barn barn : Login.farm.getBarns()) {
                for (Cow cow : barn.getCows()) {
                    animals[0]= cow.getId();
                    animals[1]= "Cow";
                    Character gender = (char) cow.getGender();
                    if (gender == 'f') 
                        animals[2] = "Famale";
                    else if (gender == 'm') 
                        animals[2] = "Male";
                    animals[3]= cow.getQuality();
                    animalModel.addRow(animals);
                }
            }
        
            for (Barn barn : Login.farm.getBarns()) {
                for (Sheep sheep : barn.getSheeps()) {
                    animals[0]= sheep.getId();
                    animals[1]= "Sheep";
                    Character gender = (char) sheep.getGender();
                    if (gender == 'f') 
                        animals[2] = "Famale";
                    else if (gender == 'm') 
                        animals[2] = "Male";
                    animals[3]= sheep.getQuality();
                    animalModel.addRow(animals);
                }
            }
        
            for (Coop coop : Login.farm.getCoops()) {
                for (Chicken chicken : coop.getChickens()) {
                    animals[0]= chicken.getId();
                        animals[1]= "Chicken";
                        Character gender = (char) chicken.getGender();
                        if (gender == 'f') 
                            animals[2] = "Famale";
                        else if (gender == 'm') 
                            animals[2] = "Male";
                    animals[3]= chicken.getQuality();
                    animalModel.addRow(animals);
                }
            }
	}


	public AdminSelAnimalGUI() throws IOException{
		createTable(true);
				
		setResizable(false);
		setTitle("ADMIN SELECTION ANIMAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 681);
		contentPaneAdmin = new JPanel();
		contentPaneAdmin.setBackground(new Color(153, 153, 51));
		contentPaneAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAdmin);
		contentPaneAdmin.setLayout(null);
		
		JTabbedPane animalsPane = new JTabbedPane(JTabbedPane.TOP);
		animalsPane.setEnabled(false);
		animalsPane.setBounds(10, 81, 661, 550);
		contentPaneAdmin.add(animalsPane);
		
		JPanel animalPanel = new JPanel();
		animalsPane.addTab("Animals", null, animalPanel, null);
		animalsPane.setBackgroundAt(0, new Color(153, 153, 153));
		animalsPane.setEnabledAt(0, true);
		animalPanel.setLayout(null);
		
		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("admin.png")));
		lblLogo.setBounds(494, 10, 140, 123);
		animalPanel.add(lblLogo);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBackground(new Color(204, 204, 153));
		comboBox.setBounds(494, 183, 122, 30);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Cow", "Sheep", "Chicken"}));
		comboBox.setSelectedIndex(0);
		animalPanel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 0) animaltype = 0;
				else if (comboBox.getSelectedIndex() == 1) animaltype = 1;
				else if (comboBox.getSelectedIndex() == 2) animaltype = 2;				
			}
		});
		
		JLabel lblanimalTyple = new JLabel("Animal Type:");
		lblanimalTyple.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblanimalTyple.setBounds(494, 143, 114, 30);
		animalPanel.add(lblanimalTyple);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(204, 204, 153));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd.setBounds(494, 328, 122, 30);
		animalPanel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(animaltype==0) {
					Cow cow = new Cow(quality,spinnerValue);
					Login.farm.addCow(cow);
				}
				else if(animaltype==1) {
					Sheep sheep = new Sheep(quality,spinnerValue);
					Login.farm.addSheep(sheep);
				}
				else if(animaltype==2) {
					Chicken chicken = new Chicken(quality,spinnerValue*12);
					Login.farm.addChicken(chicken);
				}
				createTable(false);				
			}
		});
		
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblId.setBounds(494, 399, 45, 13);
		animalPanel.add(lblId);
		
		textDeleteId = new JTextField();
		textDeleteId.setBounds(494, 422, 123, 30);
		animalPanel.add(textDeleteId);
		textDeleteId.setColumns(10);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(new Color(204, 204, 153));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textDeleteId.getText().length() == 0 ) {
					JOptionPane.showMessageDialog(null, "Please Fill in ID Field!");
				}else {
					Login.farm.deleteAnimal(Integer.parseInt(textDeleteId.getText()));
					createTable(false);
				}				
			}
		});
		btnDelete.setBounds(494, 462, 122, 30);
		animalPanel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("1.Quality\r\n2.Quality\r\n3.Quality");
		scrollPane.setBounds(10, 10, 462, 497);
		animalPanel.add(scrollPane);
		
		tableListAnimals = new JTable(animalModel);
		tableListAnimals.setEnabled(false);
		tableListAnimals.setBackground(new Color(204, 204, 153));
		tableListAnimals.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(tableListAnimals);
		
		JLabel lblQualityType = new JLabel("Quality Type:");
		lblQualityType.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQualityType.setBounds(494, 223, 102, 20);
		animalPanel.add(lblQualityType);
		
		JComboBox<Object> comboBoxQuality = new JComboBox<Object>();
		comboBoxQuality.setBackground(new Color(204, 204, 153));
		comboBoxQuality.setModel(new DefaultComboBoxModel<Object>(new String[] {"1. Quality", "2. Quality", "3. Quality"}));
		comboBoxQuality.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		comboBoxQuality.setBounds(494, 253, 122, 30);
		comboBoxQuality.setSelectedIndex(0);
		animalPanel.add(comboBoxQuality);
		comboBoxQuality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxQuality.getSelectedIndex() == 0) quality = 1;
				else if (comboBoxQuality.getSelectedIndex() == 1) quality = 2;
				else if (comboBoxQuality.getSelectedIndex() == 2) quality = 3;		
			}
		});
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAge.setBounds(494, 298, 54, 20);
		animalPanel.add(lblAge);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(555, 301, 30, 20);
		animalPanel.add(spinner);
		
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				spinnerValue = (int) spinner.getValue();
			}
	    });
		
		JLabel lblWelcomeTxt = new JLabel("WELCOME MRS./MR. ADMIN");
		lblWelcomeTxt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblWelcomeTxt.setBounds(10, 25, 262, 13);
		contentPaneAdmin.add(lblWelcomeTxt);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(300, 14, 45, 22);
		contentPaneAdmin.add(lblDate);
		
		textDate = new JTextField();
		textDate.setEnabled(false);
		textDate.setText("  "+Farm.currentDate.toString());
		textDate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textDate.setColumns(10);
		textDate.setBounds(344, 11, 106, 29);
		contentPaneAdmin.add(textDate);
		
		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMoney.setBounds(282, 50, 88, 21);
		contentPaneAdmin.add(lblMoney);
		
		textMoney = new JTextField();
		textMoney.setEnabled(false);
		textMoney.setText("  " + Login.farm.getMoney());
		textMoney.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textMoney.setColumns(10);
		textMoney.setBounds(344, 50, 106, 29);
		contentPaneAdmin.add(textMoney);
		
		JButton btnNextDay = new JButton("NEXT DAY");
		btnNextDay.setBackground(new Color(204, 204, 153));
		btnNextDay.setBounds(460, 12, 106, 31);
		btnNextDay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.farm.nextDay();
				textDate.setText("  " + Farm.currentDate.toString());
				textMoney.setText("  " + Login.farm.getMoney());
				createTable(false);
			}
		});
		contentPaneAdmin.add(btnNextDay);
		JButton btnNextMonth = new JButton("NEXT MONTH");
		btnNextMonth.setBackground(new Color(204, 204, 153));
		btnNextMonth.setBounds(460, 48, 106, 31);
		btnNextMonth.setHorizontalAlignment(SwingConstants.LEFT);
		btnNextMonth.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.farm.nextMonth();
				textDate.setText("  "+Farm.currentDate.toString());
				textMoney.setText("  "+Login.farm.getMoney());
				createTable(false);
			}
		});
		contentPaneAdmin.add(btnNextMonth);
		JButton btnSave = new JButton("SAVE");
		btnSave.setBackground(new Color(204, 204, 153));
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSave.setBounds(576, 10, 96, 31);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.farm.exitAndSave();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		contentPaneAdmin.add(btnSave);
		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(204, 204, 153));
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnBack.setBounds(576, 47, 96, 31);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSelectionGUI adminSelectionGUI = null;
				try {
					adminSelectionGUI = new AdminSelectionGUI();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				adminSelectionGUI.setVisible(true);
				dispose();
			}
		});
		contentPaneAdmin.add(btnBack);	
	}
}