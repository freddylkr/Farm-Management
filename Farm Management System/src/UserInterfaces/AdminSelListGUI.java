package UserInterfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Management.Barn;
import Management.Farm;
import Management.Product;
import Management.Sheep;
import Management.Cow;

public class AdminSelListGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneAdmin;
	private static DefaultTableModel animalModel;
	private Object[] animals;
	private static DefaultTableModel barnCoopModel;
	private Object[] barnCoop;
	private static DefaultTableModel employeeModel;
	private Object[] employee;
	private static DefaultTableModel productModel;
	private Object[] products;
	private static JTable tableSelectionAnimal;
	private static JTable tableBarnCoop;
	private static JTable tableEmployee;
	private static JTable tableProduct;
	private JTextField textMoney;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSelListGUI frame = new AdminSelListGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void createTable(boolean c, int selection) {
		if (c)
			animalModel = new DefaultTableModel();
		else if (!c) {
			animalModel = (DefaultTableModel) tableSelectionAnimal.getModel();
			animalModel.setRowCount(0);
		}
		Object[] colAnimalType = new Object[6];
		colAnimalType[0] = "ID";
		colAnimalType[1] = "ANIMAL TYPE";
		colAnimalType[2] = "GENDER";
		colAnimalType[3] = "QUALITY";
		colAnimalType[4] = "PREGNANCY";
		colAnimalType[5] = "AGE";
		animalModel.setColumnIdentifiers(colAnimalType);
		animals = new Object[6];
		if (selection == 0) {
			for (Barn barn : Login.farm.getBarns()) {
				for (Cow cow : barn.getCows()) {
					animals[0] = cow.getId();
					animals[1] = "Cow";
					animals[2] = cow.getGender();
					if ((char) animals[2] == 'f')
						animals[2] = "Famale";
					else if ((char) animals[2] == 'm')
						animals[2] = "Male";
					animals[3] = cow.getQuality();
					int pre = cow.getPregnancyCounter();
					if (pre != 0)
						animals[4] = String.valueOf(pre / 30) + " Months";
					else
						animals[4] = "Not pregnant";
					animals[5] = cow.getAge();
					animalModel.addRow(animals);

				}
			}
		} else if (selection == 1) {
			for (Barn barn : Login.farm.getBarns()) {
				for (Sheep sheep : barn.getSheeps()) {
					animals[0] = sheep.getId();
					animals[1] = "Sheep";
					animals[2] = sheep.getGender();
					if ((char) animals[2] == 'f')
						animals[2] = "Famale";
					else if ((char) animals[2] == 'm')
						animals[2] = "Male";
					animals[3] = sheep.getQuality();
					int pre = sheep.getPregnancyCounter();
					if (pre != 0)
						animals[4] = String.valueOf(pre / 30) + " Months";
					else
						animals[4] = "Not pregnant";
					animals[5] = sheep.getAge();
					animalModel.addRow(animals);
				}
				
			}
			for (int i = 0; i < Login.farm.getBarns().size(); i++) {
				for (int j = 0; j < Login.farm.getBarns().get(i).getSheeps().size(); j++) {
					
				}
			}
		} else if (selection == 2) {
			for (int i = 0; i < Login.farm.getCoops().size(); i++) {
				for (int j = 0; j < Login.farm.getCoops().get(i).getChickens().size(); j++) {
					animals[0] = Login.farm.getCoops().get(i).getChickens().get(j).getId();
						animals[1] = "Chicken";
					animals[2] = Login.farm.getCoops().get(i).getChickens().get(j).getGender();
					if ((char) animals[2] == 'f')
						animals[2] = "Famale";
					else if ((char) animals[2] == 'm')
						animals[2] = "Male";
					animals[3] = Login.farm.getCoops().get(i).getChickens().get(j).getQuality();
					int pre = Login.farm.getCoops().get(i).getChickens().get(j).getIncubationCounter();
					if (pre != 0)
						animals[4] = String.valueOf(pre) + " Days";
					else
						animals[4] = "Not pregnant";
					animals[5] = Login.farm.getCoops().get(i).getChickens().get(j).getMonthAge();
					animalModel.addRow(animals);
				}
			}
		}
	}

	public void createTable2(boolean c, int selection) {
		if (c)
			barnCoopModel = new DefaultTableModel();
		else if (!c) {
			barnCoopModel = (DefaultTableModel) tableBarnCoop.getModel();
			barnCoopModel.setRowCount(0);
		}
		Object[] colType = new Object[1];
		colType[0] = "ELEMENTS";
		barnCoopModel.setColumnIdentifiers(colType);
		barnCoop = new Object[1];
		if (selection == 0) {
			for (int i = 0; i < Login.farm.getBarns().size(); i++) {
				barnCoop[0] = (i + 1) + ". Barn";
				barnCoopModel.addRow(barnCoop);
				barnCoop[0] = "(1. Worker) ID: " + Login.farm.getBarns().get(i).getWorkers()[0].getId() + " Name: "
						+ Login.farm.getBarns().get(i).getWorkers()[0].getName() + " Salary: "
						+ Login.farm.getBarns().get(i).getWorkers()[0].getSalary();
				barnCoopModel.addRow(barnCoop);
				barnCoop[0] = "(2. Worker) ID: " + Login.farm.getBarns().get(i).getWorkers()[1].getId() + " Name: "
						+ Login.farm.getBarns().get(i).getWorkers()[1].getName() + " Salary: "
						+ Login.farm.getBarns().get(i).getWorkers()[1].getSalary();
				barnCoopModel.addRow(barnCoop);
				barnCoop[0] = "Count of Cow: " + Login.farm.getBarns().get(i).getCows().size();
				barnCoopModel.addRow(barnCoop);
				barnCoop[0] = "Count of Sheep: " + Login.farm.getBarns().get(i).getSheeps().size();
				barnCoopModel.addRow(barnCoop);
			}
		} else if (selection == 1) {
			for (int i = 0; i < Login.farm.getCoops().size(); i++) {
				barnCoop[0] = (i + 1) + ". Coop";
				barnCoopModel.addRow(barnCoop);
				barnCoop[0] = "(Worker) ID: " + Login.farm.getCoops().get(i).getWorker().getId() + " Name: "
						+ Login.farm.getCoops().get(i).getWorker().getId() + " Salary: "
						+ Login.farm.getCoops().get(i).getWorker().getId();
				barnCoopModel.addRow(barnCoop);
				barnCoop[0] = "Count of Chicken: " + Login.farm.getCoops().get(i).getChickens().size();
				barnCoopModel.addRow(barnCoop);
			}
		}
	}

	public void createTableEmployee(boolean c) {
		if (c)
			employeeModel = new DefaultTableModel();
		else if (!c) {
			employeeModel = (DefaultTableModel) tableEmployee.getModel();
			employeeModel.setRowCount(0);
		}
		employeeModel = new DefaultTableModel();
		Object[] colEmployeeType = new Object[4];
		colEmployeeType[0] = "TYPE";
		colEmployeeType[1] = "ID";
		colEmployeeType[2] = "NAME";
		colEmployeeType[3] = "SALARY";
		employeeModel.setColumnIdentifiers(colEmployeeType);
		employee = new Object[4];

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
			if (Login.farm.getWorkers().get(i).isWorking()) {
				employee[0] = (i + 1) + ". Worker";
				employee[1] = Login.farm.getWorkers().get(i).getId();
				employee[2] = Login.farm.getWorkers().get(i).getName();
				employee[3] = Login.farm.getWorkers().get(i).getSalary();
				employeeModel.addRow(employee);
			}
		}
	}
	
	public void createTableProduct(boolean c) {
		if (c)
			productModel = new DefaultTableModel();
		else if (!c) {
			productModel = (DefaultTableModel) tableProduct.getModel();
			productModel.setRowCount(0);
		}
		Object[] colEmployeeType = new Object[3];
		colEmployeeType[0] = "ANIMAL TYPE";
		colEmployeeType[1] = "PRODUCT NAME";
		colEmployeeType[2] = "PRICE";
		productModel.setColumnIdentifiers(colEmployeeType);
		products = new Object[3];
		for (Product product : Login.farm.getWarehouse().getProducts()) {
			products[0] = product.getAnimalType();
			products[1] = product.getProductName();
			products[2] = product.getPrice();
			productModel.addRow(products);
		}

	}

	public AdminSelListGUI() {

		setTitle("ADMIN SELECTION LIST");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 681);
		contentPaneAdmin = new JPanel();
		contentPaneAdmin.setBackground(new Color(153, 153, 51));
		contentPaneAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAdmin);
		contentPaneAdmin.setLayout(null);

		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(204, 204, 153));
		btnBack.setBounds(564, 47, 96, 31);
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

		JLabel lblWelcomeTxt = new JLabel("WELCOME MRS./MR. ADMIN");
		lblWelcomeTxt.setBounds(10, 25, 262, 13);
		lblWelcomeTxt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		contentPaneAdmin.add(lblWelcomeTxt);

		JTextField textDate = new JTextField();
		textDate.setEnabled(false);
		textDate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textDate.setBounds(332, 11, 106, 29);
		textDate.setText("  " + Farm.currentDate.toString());
		contentPaneAdmin.add(textDate);
		textDate.setColumns(10);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 70, 666, 560);
		contentPaneAdmin.add(tabbedPane);

		JPanel panelAnimal = new JPanel();
		tabbedPane.addTab("Animal", null, panelAnimal, null);
		panelAnimal.setLayout(null);

		JLabel lblSelAnimal = new JLabel("Please Enter Selection:");
		lblSelAnimal.setBounds(10, 10, 206, 32);
		lblSelAnimal.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panelAnimal.add(lblSelAnimal);

		JComboBox<Object> comboBoxAnimal = new JComboBox<Object>();
		comboBoxAnimal.setBackground(new Color(204, 204, 153));
		createTable(true, 0);
		comboBoxAnimal.setBounds(187, 18, 134, 24);
		comboBoxAnimal.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		comboBoxAnimal.setModel(new DefaultComboBoxModel<Object>(new String[] { "Cow", "Sheep", "Chicken" }));
		panelAnimal.add(comboBoxAnimal);
		comboBoxAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable(false, comboBoxAnimal.getSelectedIndex());
			}
		});

		JScrollPane scrollPaneAnimal = new JScrollPane();
		scrollPaneAnimal.setBounds(29, 70, 609, 438);
		panelAnimal.add(scrollPaneAnimal);

		tableSelectionAnimal = new JTable();
		tableSelectionAnimal.setBackground(new Color(204, 204, 153));
		scrollPaneAnimal.setViewportView(tableSelectionAnimal);

		JPanel panelBarnCoop = new JPanel();
		tabbedPane.addTab("Barn-Coop", null, panelBarnCoop, null);
		panelBarnCoop.setLayout(null);

		JLabel lblSelBarnCoop = new JLabel("Please Enter Selection:");
		lblSelBarnCoop.setBounds(10, 10, 206, 32);
		lblSelBarnCoop.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panelBarnCoop.add(lblSelBarnCoop);

		JComboBox<String> comboBoxBarnCoop = new JComboBox<String>();
		createTable2(true, 0);
		comboBoxBarnCoop.setBounds(187, 18, 134, 24);
		comboBoxBarnCoop.setModel(new DefaultComboBoxModel<String>(new String[] { "Barn", "Coop" }));
		comboBoxBarnCoop.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panelBarnCoop.add(comboBoxBarnCoop);
		comboBoxBarnCoop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable2(false, comboBoxBarnCoop.getSelectedIndex());
			}
		});

		JScrollPane scrollPaneBarnCoop = new JScrollPane();
		scrollPaneBarnCoop.setBounds(20, 52, 618, 456);
		panelBarnCoop.add(scrollPaneBarnCoop);

		tableBarnCoop = new JTable();
		tableBarnCoop.setBackground(new Color(204, 204, 153));
		scrollPaneBarnCoop.setViewportView(tableBarnCoop);

		JPanel panelEmployee = new JPanel();
		tabbedPane.addTab("Employee", null, panelEmployee, null);
		panelEmployee.setLayout(null);
		createTableEmployee(true);

		JScrollPane scrollPaneEmployee = new JScrollPane();
		scrollPaneEmployee.setBounds(10, 10, 626, 499);
		panelEmployee.add(scrollPaneEmployee);

		tableEmployee = new JTable(employeeModel);
		tableEmployee.setBackground(new Color(204, 204, 153));
		scrollPaneEmployee.setViewportView(tableEmployee);

		JPanel panelProduct = new JPanel();
		tabbedPane.addTab("Product", null, panelProduct, null);
		panelProduct.setLayout(null);
		createTableProduct(true);

		JScrollPane scrollPaneProduct = new JScrollPane();
		scrollPaneProduct.setBounds(10, 10, 626, 502);
		panelProduct.add(scrollPaneProduct);

		tableProduct = new JTable(productModel);
		tableProduct.setBackground(new Color(204, 204, 153));
		scrollPaneProduct.setViewportView(tableProduct);

		JButton btnNextDay = new JButton("NEXT DAY");
		btnNextDay.setBackground(new Color(204, 204, 153));
		btnNextDay.setBounds(448, 10, 106, 31);
		btnNextDay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.farm.nextDay();
				textDate.setText("  " + Farm.currentDate.toString());
				textMoney.setText("  " + Login.farm.getMoney());
				createTable(false, comboBoxAnimal.getSelectedIndex());
				createTable2(false, comboBoxBarnCoop.getSelectedIndex());
				createTableProduct(false);
				createTableEmployee(false);

			}
		});
		contentPaneAdmin.add(btnNextDay);

		JButton btnNextMonth = new JButton("NEXT MONTH");
		btnNextMonth.setBackground(new Color(204, 204, 153));
		btnNextMonth.setBounds(448, 48, 106, 31);
		btnNextMonth.setHorizontalAlignment(SwingConstants.LEFT);
		btnNextMonth.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.farm.nextMonth();
				textDate.setText("  " + Farm.currentDate.toString());
				textMoney.setText("  " + Login.farm.getMoney());
				createTable(false, comboBoxAnimal.getSelectedIndex());
				createTable2(false, comboBoxBarnCoop.getSelectedIndex());
				createTableEmployee(false);
				createTableProduct(false);
			}
		});
		contentPaneAdmin.add(btnNextMonth);

		JButton btnSave = new JButton("SAVE");
		btnSave.setBackground(new Color(204, 204, 153));
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSave.setBounds(564, 6, 96, 31);
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

		textMoney = new JTextField();
		textMoney.setEnabled(false);
		textMoney.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textMoney.setText("  " + Login.farm.getMoney());
		textMoney.setBounds(332, 50, 106, 29);
		contentPaneAdmin.add(textMoney);
		textMoney.setColumns(10);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(288, 14, 45, 22);
		contentPaneAdmin.add(lblDate);

		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMoney.setBounds(270, 50, 88, 21);
		contentPaneAdmin.add(lblMoney);

	}
}
