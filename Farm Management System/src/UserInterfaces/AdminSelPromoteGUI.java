package UserInterfaces;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Management.Farm;

public class AdminSelPromoteGUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPaneAdmin;
	private JTextField textDate;
	private JTextField textMoney;
	private static DefaultTableModel employeeModel ;
	private static Object [] employee ;
	private static JTable tableEmpoyee;
	private JTextField textEmpoyeeID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSelPromoteGUI frame = new AdminSelPromoteGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void createTable(boolean c) {
		if (c)
			employeeModel = new DefaultTableModel();
		else if (!c) {
			employeeModel = (DefaultTableModel) tableEmpoyee.getModel();
			employeeModel.setRowCount(0);
		}
		Object [] colEmployeeType = new Object [4];
		colEmployeeType[0] ="TYPE";
		colEmployeeType[1] ="ID";
		colEmployeeType[2] ="NAME";
		colEmployeeType[3] ="SALARY";
		employeeModel.setColumnIdentifiers(colEmployeeType);
		employee = new Object [4] ;		
		employee[0] = "Veterinary";
		employee[1] = Login.farm.getVeterinary().getId();
		employee[2] = Login.farm.getVeterinary().getName();
		employee[3] = Login.farm.getVeterinary().getSalary();
		employeeModel.addRow(employee);
		
		employee[0] = "Sales Person";
		employee[1] = Login.farm.getWarehouse().getBazaar().getSalesPerson().getId();
		employee[2] = Login.farm.getWarehouse().getBazaar().getSalesPerson().getName();
		employee[3] = Login.farm.getWarehouse().getBazaar().getSalesPerson().getSalary();
		employeeModel.addRow(employee);
		
		for (int i = 0; i < Login.farm.getWorkers().size(); i++) {
			if(Login.farm.getWorkers().get(i).isWorking()) {
				employee[0] = (i+1)+". Worker";
				employee[1] = Login.farm.getWorkers().get(i).getId();
				employee[2] = Login.farm.getWorkers().get(i).getName();
				employee[3] = Login.farm.getWorkers().get(i).getSalary();
				employeeModel.addRow(employee);						
			}			
		}		
	}
	public AdminSelPromoteGUI() {
		createTable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 681);
		contentPaneAdmin = new JPanel();
		contentPaneAdmin.setBackground(new Color(153, 153, 51));
		contentPaneAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAdmin);
		contentPaneAdmin.setLayout(null);
		
		JLabel lblWelcomeTxt = new JLabel("WELCOME MRS./MR. ADMIN");
		lblWelcomeTxt.setBounds(17, 25, 262, 13);
		lblWelcomeTxt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		contentPaneAdmin.add(lblWelcomeTxt);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(300, 14, 45, 22);
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPaneAdmin.add(lblDate);
		
		textDate = new JTextField();
		textDate.setBounds(344, 11, 106, 29);
		textDate.setEnabled(false);
		textDate.setText("  "+Farm.currentDate.toString());
		textDate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textDate.setColumns(10);
		contentPaneAdmin.add(textDate);
		
		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setBounds(282, 50, 88, 21);
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPaneAdmin.add(lblMoney);
		
		textMoney = new JTextField();
		textMoney.setBounds(344, 50, 106, 29);
		textMoney.setEnabled(false);
		textMoney.setText("  " + Login.farm.getMoney());
		textMoney.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textMoney.setColumns(10);
		contentPaneAdmin.add(textMoney);
		
		JButton btnNextDay = new JButton("NEXT DAY");
		btnNextDay.setBounds(460, 12, 106, 31);
		btnNextDay.setBackground(new Color(204, 204, 153));
		btnNextDay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.farm.nextDay();
				textDate.setText("  "+Farm.currentDate.toString());
				textMoney.setText("  "+Login.farm.getMoney());
				createTable(false);
			}
		});
		contentPaneAdmin.add(btnNextDay);
		
		JButton btnNextMonth = new JButton("NEXT MONTH");
		btnNextMonth.setBounds(460, 48, 106, 31);
		btnNextMonth.setBackground(new Color(204, 204, 153));
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
		btnSave.setBounds(576, 12, 96, 31);
		btnSave.setBackground(new Color(204, 204, 153));
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.farm.exitAndSave();
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		});
		contentPaneAdmin.add(btnSave);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(576, 47, 96, 31);
		btnBack.setBackground(new Color(204, 204, 153));
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 111, 515, 505);
		contentPaneAdmin.add(scrollPane);
		
		tableEmpoyee = new JTable(employeeModel);
		tableEmpoyee.setBackground(new Color(204, 204, 153));
		scrollPane.setViewportView(tableEmpoyee);
		
		JLabel lblEmpoyeeID = new JLabel("Empoyee ID:");
		lblEmpoyeeID.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmpoyeeID.setBounds(546, 235, 106, 29);
		contentPaneAdmin.add(lblEmpoyeeID);
		
		textEmpoyeeID = new JTextField();
		textEmpoyeeID.setBounds(546, 270, 126, 36);
		contentPaneAdmin.add(textEmpoyeeID);
		textEmpoyeeID.setColumns(10);
		
		JButton btnProme = new JButton("PROMOTE");
		btnProme.setBackground(new Color(204, 204, 153));
		btnProme.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnProme.setBounds(546, 332, 126, 29);
		btnProme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textEmpoyeeID.getText().length() == 0 ) {
					JOptionPane.showMessageDialog(null, "Please Fill in ID Field!");
				}else {
					Login.farm.getAdmin().promote(Integer.parseInt(textEmpoyeeID.getText()));
					createTable(false);
				}				
			}
		});
		contentPaneAdmin.add(btnProme);
	}
}
