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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminSelectionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel SelectionPane;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSelectionGUI frame = new AdminSelectionGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AdminSelectionGUI() throws IOException {
		setBackground(new Color(102, 0, 0));
		setTitle("AdminSelection");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 681);
		SelectionPane = new JPanel();
		SelectionPane.setBackground(new Color(204, 204, 153));
		SelectionPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(SelectionPane);
		SelectionPane.setLayout(null);
		
		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("adminSel.png")));
		lblLogo.setBounds(-25, 10, 714, 692);
		SelectionPane.add(lblLogo);
		
		JLabel lblTextClick = new JLabel("Please Click on the Button for the Section You Want to Do.");
		lblTextClick.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTextClick.setBounds(99, 29, 483, 56);
		SelectionPane.add(lblTextClick);
		
		JButton btnAnimals = new JButton("ANIMALS");
		btnAnimals.setBackground(new Color(153, 153, 102));
		btnAnimals.setForeground(new Color(0, 0, 0));
		btnAnimals.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAnimals.setBounds(188, 95, 312, 37);
		SelectionPane.add(btnAnimals);
		btnAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSelAnimalGUI adminSelAnimalGUI = null;
                try {
                    adminSelAnimalGUI = new AdminSelAnimalGUI();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                adminSelAnimalGUI.setVisible(true);
                dispose();
			}
		});
		
		
		JButton btnList = new JButton("LIST");
		btnList.setBackground(new Color(153, 153, 102));
		btnList.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnList.setBounds(188, 177, 312, 37);
		SelectionPane.add(btnList);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSelListGUI adminSelListGUI = null;
				adminSelListGUI = new AdminSelListGUI();
				adminSelListGUI.setVisible(true);
				dispose();
			}
		});
		
		JButton btnProduct = new JButton("PROMOTE");
		btnProduct.setBackground(new Color(153, 153, 102));
		btnProduct.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnProduct.setBounds(188, 258, 312, 37);
		SelectionPane.add(btnProduct);
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSelPromoteGUI adminSelPromeGUI = null;
				adminSelPromeGUI = new AdminSelPromoteGUI();
				adminSelPromeGUI.setVisible(true);
				dispose();
			}
		});
	}
}
