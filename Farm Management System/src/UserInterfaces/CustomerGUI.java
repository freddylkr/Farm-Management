package UserInterfaces;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Management.Product;

public class CustomerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneCustomer;
	private JButton btnSelect;
	private JTextField textSelect;
	private JComboBox<String> comboBoxSearch;
	private static int searchType = 0;
	private JButton btnSearch;
	private static ArrayList<Product> products;
	private static DefaultTableModel productModel;
	private static DefaultTableModel basketModel;
	private Object[] pro;
	private JTable tableProduct;
	private JLabel lblProductTable;
	private JTextField textFieldMoney;
	private JLabel lblMoney;
	private JButton btnComfirm;
	private Boolean flag;
	private int basketAmount;

	private Product selectedProduct;
	private JLabel lblId;
	private JTable tableBasket;
	private JLabel lblBasket;
	private ArrayList<Product> basket;
	private JScrollPane scrollPaneforTableBasket;
	private JScrollPane scrollPaneforTableProduct;
	private JLabel lblCustomer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void createTableProduct(boolean c) {
		if (c)
			productModel = new DefaultTableModel();
		else {
			productModel = (DefaultTableModel) tableProduct.getModel();
			productModel.setRowCount(0);
		}
		Object[] colProductType = { "PRODUCT", "PRODUCT TYPE", "PRODUCT NAME", "PRICE" };
		productModel.setColumnIdentifiers(colProductType);
		pro = new Object[4];

		if (products != null) {
			int i = 1;
			for (Product product : products) {
				pro[0] = i;
				pro[1] = product.getAnimalType();
				pro[2] = product.getProductName();
				pro[3] = product.getPrice();
				productModel.addRow(pro);
				i++;
			}
		}
	}

	public void createTableBasket(boolean c) {
		if (c) {
			basketModel = new DefaultTableModel();
			basketModel.setRowCount(0);
		}
		else {
			basketModel = (DefaultTableModel) tableBasket.getModel();
			basketModel.setRowCount(0);
		}
		Object[] colProductType = { "PRODUCT", "PRODUCT TYPE", "PRODUCT NAME", "PRICE" };
		basketModel.setColumnIdentifiers(colProductType);
		pro = new Object[4];

		if (basket != null) {
			int i = 1;
			for (Product product : basket) {
				pro[0] = i;
				pro[1] = product.getAnimalType();
				pro[2] = product.getProductName();
				pro[3] = product.getPrice();
				basketModel.addRow(pro);
				i++;
			}
		}
	}
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
	public Boolean check(Product product) {
		return Login.farm.getWarehouse().getBazaar().productCheck(product);
	}

	public CustomerGUI() throws IOException {
		createTableProduct(true);
		createTableBasket(true);
		setResizable(false);
		
		contentPaneCustomer = new JPanel();
		contentPaneCustomer.setBackground(new Color(153, 153, 51));
		contentPaneCustomer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCustomer);
		contentPaneCustomer.setLayout(null);

		flag = false;
		basketAmount = 0;
		scrollPaneforTableProduct = new JScrollPane();
		scrollPaneforTableProduct.setBounds(24, 57, 497, 236);
		contentPaneCustomer.add(scrollPaneforTableProduct);
		tableProduct = new JTable(productModel);
		scrollPaneforTableProduct.setViewportView(tableProduct);

		scrollPaneforTableBasket = new JScrollPane();
		scrollPaneforTableBasket.setBounds(24, 340, 497, 236);
		contentPaneCustomer.add(scrollPaneforTableBasket);
		tableBasket = new JTable(basketModel);
		scrollPaneforTableBasket.setViewportView(tableBasket);;

		products = new ArrayList<Product>();
		basket = new ArrayList<Product>();
		setTitle("CUSTOMER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 681);

		JLabel lblSearch = new JLabel("Search Type:");
		lblSearch.setBounds(531, 53, 102, 20);
		lblSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPaneCustomer.add(lblSearch);

		lblMoney = new JLabel("Basket Amount:");
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMoney.setBounds(531, 380, 120, 20);
		contentPaneCustomer.add(lblMoney);

		textFieldMoney = new JTextField();
		textFieldMoney.setEnabled(false);
		textFieldMoney.setBounds(531, 410, 120, 30);
		contentPaneCustomer.add(textFieldMoney);
		textFieldMoney.setColumns(10);

		comboBoxSearch = new JComboBox<String>();
		comboBoxSearch.setBounds(529, 83, 122, 30);
		comboBoxSearch.setToolTipText("");
		comboBoxSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[] { "Milk", "Egg", "Wool" }));
		comboBoxSearch.setSelectedIndex(0);

		contentPaneCustomer.add(comboBoxSearch);
		comboBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxSearch.getSelectedIndex() == 0) {
					searchType = 0;
				} else if (comboBoxSearch.getSelectedIndex() == 1)
					searchType = 1;
				else if (comboBoxSearch.getSelectedIndex() == 2)
					searchType = 2;
			}
		});

		btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(204, 204, 153));
		btnSearch.setBounds(531, 124, 120, 35);
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPaneCustomer.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (searchType == 0)
						products = Login.farm.getWarehouse().searchProduct("Milk");
					else if (searchType == 1)
						products = Login.farm.getWarehouse().searchProduct("Egg");
					else if (searchType == 2)
						products = Login.farm.getWarehouse().searchProduct("Wool");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				createTableProduct(false);
			}
		});
		textSelect = new JTextField();
		textSelect.setBounds(531, 213, 120, 30);
		contentPaneCustomer.add(textSelect);
		textSelect.setColumns(10);
		
		btnSelect = new JButton("Select");
		btnSelect.setBackground(new Color(204, 204, 153));
		btnSelect.setBounds(531, 253, 120, 35);
		btnSelect.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPaneCustomer.add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < products.size(); i++) {
					if (isNumeric(textSelect.getText()) && Integer.parseInt(textSelect.getText()) - 1 == i) {
						selectedProduct = products.get(i);
						flag = check(products.get(i));
						basket.add(selectedProduct);
						basketAmount += selectedProduct.getPrice();
						products.remove(selectedProduct);
						createTableProduct(false);
						createTableBasket(false);
						textFieldMoney.setText(String.valueOf(basketAmount));
					}
				}
			}
		});

		btnSelect.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPaneCustomer.add(btnSelect);

		lblBasket = new JLabel("Basket:");
		lblBasket.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblBasket.setBounds(24, 315, 147, 13);
		contentPaneCustomer.add(lblBasket);

		lblProductTable = new JLabel("Product Table");
		lblProductTable.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductTable.setBounds(24, 34, 147, 13);
		contentPaneCustomer.add(lblProductTable);

		btnComfirm = new JButton("BUY");
		btnComfirm.setBackground(new Color(204, 204, 153));
		btnComfirm.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnComfirm.setBounds(531, 462, 120, 35);
		contentPaneCustomer.add(btnComfirm);
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag) {
					Login.farm.getWarehouse().getBazaar().purchase(basket);
					basket.clear();
					createTableBasket(false);
					textFieldMoney.setText("");
					basketAmount = 0;
				}

			}
		});
		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblId.setBounds(531, 190, 64, 13);
		contentPaneCustomer.add(lblId);
		
		lblCustomer = new JLabel("WELCOME TO THE FARMLAND: MRS./MR." + Login.enteredCustomer.getName());
		lblCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomer.setBounds(24, 11, 655, 13);
		contentPaneCustomer.add(lblCustomer);

	}
}