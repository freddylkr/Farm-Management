package UserInterfaces;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Management.Admin;
import Management.Customer;
import Management.Farm;


public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNameAdmin;
	private JTextField txtNameCustomer;
	private JPasswordField passwordAdmin;
	private JPasswordField passwordCustomer;
	public static Farm farm;
	public static Customer enteredCustomer;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() throws IOException {
		farm = new Farm();
		setResizable(false);
		setTitle("Farm Managamet System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 681);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel textbox = new JPanel();
		textbox.setForeground(Color.WHITE);
		textbox.setBounds(91, 342, 517, 89);
		contentPane.add(textbox);
		textbox.setLayout(null);

		JTextPane welcomeText = new JTextPane();
		welcomeText.setBounds(10, 10, 497, 65);
		textbox.add(welcomeText);
		welcomeText.setBackground(new Color(153, 153, 51));
		welcomeText.setEditable(false);
		welcomeText.setForeground(Color.BLACK);
		welcomeText.setToolTipText("");
		welcomeText.setFont(new Font("Times New Roman", Font.BOLD, 23));
		welcomeText.setText("                           WELCOME TO \r\n             FARM MANAGEMENT SYSTEM");

		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("logo.png")));
		lblLogo.setBounds(142, 10, 402, 322);
		contentPane.add(lblLogo);

		JTabbedPane tabbedLogin = new JTabbedPane(JTabbedPane.TOP);
		tabbedLogin.setBounds(10, 441, 668, 190);
		contentPane.add(tabbedLogin);

		JLabel lblErrorMessage = new JLabel("");
		lblErrorMessage.setBounds(326, 175, 45, 13);
		contentPane.add(lblErrorMessage);

		JPanel panelCustomer = new JPanel();
		panelCustomer.setBackground(new Color(204, 204, 153));
		panelCustomer.setBorder(new LineBorder(new Color(153, 153, 102), 4));
		tabbedLogin.addTab("CUSTOMER", null, panelCustomer, null);
		tabbedLogin.setBackgroundAt(0, new Color(153, 153, 102));
		panelCustomer.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("USER NAME:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(38, 20, 131, 34);
		panelCustomer.add(lblNewLabel_1);

		JLabel lblPassword_1 = new JLabel("PASSWORD:");
		lblPassword_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword_1.setBounds(38, 59, 131, 34);
		panelCustomer.add(lblPassword_1);

		txtNameCustomer = new JTextField();
		txtNameCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNameCustomer.setColumns(10);
		txtNameCustomer.setBounds(179, 20, 450, 34);
		panelCustomer.add(txtNameCustomer);

		JButton btnSgnUpCustomer = new JButton("SIGN UP");
		btnSgnUpCustomer.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (txtNameCustomer.getText().length() == 0 || passwordCustomer.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please Fill in All Fields!");
				} else {
					Customer customer = new Customer(txtNameCustomer.getText(), passwordCustomer.getText());
					farm.addCustomer(customer);
					try {
						farm.saveCustomers();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "You have saccuesfuly signed up");
				}
			}
		});
		btnSgnUpCustomer.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnSgnUpCustomer.setBackground(new Color(153, 153, 51));
		btnSgnUpCustomer.setBounds(38, 103, 297, 44);
		panelCustomer.add(btnSgnUpCustomer);

		JButton btnLoginCustomer = new JButton("LOGIN");
		btnLoginCustomer.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (txtNameCustomer.getText().length() == 0 || passwordCustomer.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please Fill in All Fields!");
				} else {
					boolean control = false;
					for (Customer customer : farm.getCustomers()) {
						
						if (txtNameCustomer.getText().equals(customer.getName()) && passwordCustomer.getText().equals(customer.getPassword())) {
							enteredCustomer = customer; 
							CustomerGUI customerGUI = null;
							try {
								customerGUI = new CustomerGUI();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							customerGUI.setVisible(true);
							control = true;
							dispose();
						}
					}
					if (!control)
						JOptionPane.showMessageDialog(lblErrorMessage, "Incorrect Login, Please Try Again!");
				}
			}
		});
		btnLoginCustomer.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnLoginCustomer.setBackground(new Color(153, 153, 51));
		btnLoginCustomer.setBounds(344, 103, 285, 44);
		panelCustomer.add(btnLoginCustomer);

		passwordCustomer = new JPasswordField();
		passwordCustomer.setBounds(179, 64, 450, 29);
		panelCustomer.add(passwordCustomer);

		JPanel panelAdmin = new JPanel();
		panelAdmin.setBackground(new Color(204, 204, 153));
		panelAdmin.setBorder(new LineBorder(new Color(153, 153, 102), 4));
		tabbedLogin.addTab("ADMIN", null, panelAdmin, null);
		tabbedLogin.setForegroundAt(1, new Color(0, 0, 0));
		tabbedLogin.setBackgroundAt(1, new Color(153, 153, 102));
		panelAdmin.setLayout(null);

		JLabel lblNewLabel = new JLabel("USER NAME:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(38, 20, 131, 34);
		panelAdmin.add(lblNewLabel);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword.setBounds(38, 59, 131, 34);
		panelAdmin.add(lblPassword);

		txtNameAdmin = new JTextField();
		txtNameAdmin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNameAdmin.setBounds(179, 20, 450, 34);
		panelAdmin.add(txtNameAdmin);
		txtNameAdmin.setColumns(10);

		JButton btnLoginAdmin = new JButton("LOGIN");
		btnLoginAdmin.setBackground(new Color(153, 153, 51));
		btnLoginAdmin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (txtNameAdmin.getText().length() == 0 || passwordAdmin.getText().length() == 0) {
					JOptionPane.showMessageDialog(lblErrorMessage, "Please Fill in All Fields!");
				} else {
					Admin admin = new Admin();
					farm.setAdmin(admin);
					if (txtNameAdmin.getText().equals(admin.getName()) && passwordAdmin.getText().equals(admin.getPassword())) {
						AdminSelectionGUI adminGUI = null;
						try {
							adminGUI = new AdminSelectionGUI();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						adminGUI.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(lblErrorMessage, "Incorrect Login, Please Try Again!");
					}
				}
			}
		});
		btnLoginAdmin.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnLoginAdmin.setBounds(38, 103, 591, 44);
		panelAdmin.add(btnLoginAdmin);
		
		passwordAdmin = new JPasswordField();
		passwordAdmin.setBounds(179, 64, 450, 29);
		panelAdmin.add(passwordAdmin);
	}
}