import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Choose_login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Choose_login window = new Choose_login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Choose_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u0395\u03A0\u0399\u039B\u039F\u0393\u0397 \u03A7\u03A1\u0397\u03A3\u03A4\u0397");
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(500, 200, 316, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLoginCeo = new JButton("Login CEOs");
		btnLoginCeo.setForeground(Color.WHITE);
		btnLoginCeo.setBackground(Color.BLACK);
		btnLoginCeo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		btnLoginCeo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Login_CEO.NewScreen();
			
			}
		});
		btnLoginCeo.setBounds(10, 23, 275, 50);
		frame.getContentPane().add(btnLoginCeo);
		
		JButton btnLoginEmp = new JButton("Login Employees");
		
		btnLoginEmp.setForeground(Color.WHITE);
		btnLoginEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login_emp.main();
			}
		});
		btnLoginEmp.setBackground(Color.BLACK);
		btnLoginEmp.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		btnLoginEmp.setBounds(10, 100, 275, 50);
		frame.getContentPane().add(btnLoginEmp);
		
	}
}
