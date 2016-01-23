import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.*;

public class Login_CEO {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_CEO window = new Login_CEO();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField textField_UN;
	private JPasswordField passwordField;
	
	/**
	 * Create the application.
	 */
	public Login_CEO() {
		initialize();
		connection=DBconnect.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u039A\u0391\u03A1\u03A4\u0395\u039B\u0391 CEO");
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		frame.setBounds(350, 150, 650, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(119, 56, 128, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(119, 132, 128, 45);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_UN = new JTextField();
		textField_UN.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textField_UN.setForeground(Color.WHITE);
		textField_UN.setBackground(Color.BLACK);
		textField_UN.setBounds(298, 65, 213, 38);
		frame.getContentPane().add(textField_UN);
		textField_UN.setColumns(10);
		
		JButton btnLogin = new JButton("Login CEO");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(textField_UN.getText().equals("") || passwordField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Complete all Data");
					}else{
					String query="select * from employeeInfo where username=? and userpass=? and title = 'CEO'";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, textField_UN.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs=pst.executeQuery();
					
					
					int count=0;
					while (rs.next()){
						count=count+1;
					}
					if (count==1){
						
						frame.dispose();
						CEO ceo=new CEO();
						ceo.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and Password not correct Try again");
					}
					
					rs.close();
					pst.close();
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					
				}
				
			}
		});
		btnLogin.setBounds(298, 230, 213, 45);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		passwordField.setBackground(Color.BLACK);
		passwordField.setBounds(301, 136, 210, 38);
		frame.getContentPane().add(passwordField);
	}
}
