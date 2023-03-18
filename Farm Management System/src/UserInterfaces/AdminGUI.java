package UserInterfaces;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AdminGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneAdmin;
	private JTextField txtAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminGUI() {
		setTitle("ADMIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 554);
		contentPaneAdmin = new JPanel();
		contentPaneAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAdmin);
		contentPaneAdmin.setLayout(null);
		
		txtAdmin = new JTextField();
		txtAdmin.setEditable(false);
		txtAdmin.setBounds(208, 249, 96, 19);
		txtAdmin.setText("ADMIN");
		contentPaneAdmin.add(txtAdmin);
		txtAdmin.setColumns(10);
	}

}
