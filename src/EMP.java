import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;

import net.proteanit.sql.DbUtils;

public class EMP extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox_Name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EMP frame = new EMP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JTextField textField_Band_Name;
	private JTextField textField_Places;
	private JTextField textField_Dates;
	private JTextField textField_Tickets;
	private JTextField textField_Ticket_num;
	private JTable table;
	private JLabel lblCodeConcert;
	private JTextField textField_CC;
	private ImageIcon format = null;
	private JLabel lblimage;
	private JTextField textField_A;
	private JTextField textField_B;
	private JTextField textField_C;
	private JTextField textField_Tick_A;
	private JTextField textField_Tick_B;
	private JTextField textField_Tick_C;
	private JLabel lblSirname;
	private JLabel lblName;
	private JTextField textField_Sirname;
	private JTextField textField_Name;
	private JLabel lblTicketsCostA;
	private JTextField textField_Total_Cost;
	int num1, num2, num3, num4, num5, num6, num7, num8, ans, ans1, ans2, ans3,TotalA, TotalB, TotalC, Total_Cost;
	
	
	public void refreshTable(){
		try {
			String query = "select synID,band_name,places,dates,tickets,A,B,C from synaylies where band_name=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, (String) comboBox_Name.getSelectedItem());
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fillCompoBox() {
		try {
			String query = "select distinct band_name from synaylies";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				comboBox_Name.addItem(rs.getString("band_name"));
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	JTextArea area;
	private JScrollPane scrollPane_1;
	static JTextField textField;
	private JScrollPane scrollPane_2;
	private JTextField txtEmpid;
	
	private Image fix_it(Image imeg,int w,int h){         
		BufferedImage resizedImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);         
		Graphics2D g2=resizedImage.createGraphics();         
		g2.drawImage(imeg,0, 0, w,h,null);        
		g2.dispose();      
		return resizedImage;     
		}   
	
	/**
	 * Create the frame.
	 */
	public EMP() {
		setTitle("\u039A\u0391\u03A1\u03A4\u0395\u039B\u0391 \u03A5\u03A0\u0391\u039B\u039B\u0397\u039B\u03A9\u039D");
		connection = DBconnect.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 50, 657, 651);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox_Name = new JComboBox<String>();
		comboBox_Name.setForeground(Color.BLACK);
		comboBox_Name.setBackground(Color.LIGHT_GRAY);
		comboBox_Name.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		comboBox_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select synID,band_name,places,dates,tickets,A,B,C from synaylies where band_name=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String) comboBox_Name.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					String query = "select description from synaylies where band_name=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String) comboBox_Name.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						area.setText(rs.getString("description"));
						
					}
					
					
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					String query = "select image from synaylies where band_name=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String) comboBox_Name.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
						byte[] imagedata=rs.getBytes("image");                
						InputStream in = new ByteArrayInputStream(imagedata);                 
						BufferedImage bImageFromConvert = ImageIO.read(in);                 
						format=new ImageIcon(fix_it(bImageFromConvert,lblimage.getWidth(),lblimage.getHeight()));                  
						lblimage.setIcon(format); 
					}

					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		comboBox_Name.setFont(new Font("Times New Roman", Font.BOLD, 16));
		comboBox_Name.setBounds(10, 11, 103, 26);
		contentPane.add(comboBox_Name);

		textField_Band_Name = new JTextField();
		textField_Band_Name.setEditable(false);
		textField_Band_Name.setForeground(Color.WHITE);
		textField_Band_Name.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Band_Name.setBackground(Color.BLACK);
		textField_Band_Name.setBounds(133, 50, 134, 27);
		contentPane.add(textField_Band_Name);
		textField_Band_Name.setColumns(10);

		JLabel lblBandName = new JLabel("Band Name");
		lblBandName.setForeground(Color.WHITE);
		lblBandName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBandName.setBounds(10, 50, 103, 27);
		contentPane.add(lblBandName);

		JLabel lblPlaces = new JLabel("Place");
		lblPlaces.setForeground(Color.WHITE);
		lblPlaces.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPlaces.setBounds(10, 90, 103, 26);
		contentPane.add(lblPlaces);

		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDate.setBounds(10, 130, 103, 26);
		contentPane.add(lblDate);

		JLabel lblTickets_Total = new JLabel("Total Tick");
		lblTickets_Total.setForeground(Color.WHITE);
		lblTickets_Total.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTickets_Total.setBounds(10, 170, 103, 29);
		contentPane.add(lblTickets_Total);

		textField_Places = new JTextField();
		textField_Places.setEditable(false);
		textField_Places.setForeground(Color.WHITE);
		textField_Places.setBackground(Color.BLACK);
		textField_Places.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Places.setBounds(133, 90, 134, 26);
		contentPane.add(textField_Places);
		textField_Places.setColumns(10);

		textField_Dates = new JTextField();
		textField_Dates.setEditable(false);
		textField_Dates.setForeground(Color.WHITE);
		textField_Dates.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Dates.setBackground(Color.BLACK);
		textField_Dates.setBounds(133, 130, 134, 27);
		contentPane.add(textField_Dates);
		textField_Dates.setColumns(10);

		textField_Tickets = new JTextField();
		textField_Tickets.setEditable(false);
		textField_Tickets.setForeground(Color.WHITE);
		textField_Tickets.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Tickets.setBackground(Color.BLACK);
		textField_Tickets.setBounds(133, 170, 134, 27);
		contentPane.add(textField_Tickets);
		textField_Tickets.setColumns(10);

		JLabel lblTicketsNum = new JLabel("Tickets num");
		lblTicketsNum.setForeground(Color.WHITE);
		lblTicketsNum.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTicketsNum.setBounds(10, 450, 103, 26);
		contentPane.add(lblTicketsNum);

		textField_Ticket_num = new JTextField();
		textField_Ticket_num.setEditable(false);
		textField_Ticket_num.setForeground(Color.WHITE);
		textField_Ticket_num.setBackground(Color.BLACK);
		textField_Ticket_num.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Ticket_num.setBounds(133, 450, 134, 27);
		contentPane.add(textField_Ticket_num);
		textField_Ticket_num.setColumns(10);

		JButton btnPrintTicket = new JButton("Print Ticket");
		btnPrintTicket.setForeground(Color.WHITE);
		btnPrintTicket.setBackground(Color.BLACK);
		btnPrintTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Date currentDate = new Date();
				String txtdate = new SimpleDateFormat("yyyy/MM/dd/  HH:mm:ss").format(currentDate);
				
				int num1, num2, num3, num4, num5, num6, num7, num8, ans, ans1, ans2, ans3;
				try {
					if(textField_Tick_A.getText().equals("") || textField_Tick_B.getText().equals("")
							   || textField_Tick_C.getText().equals("") || textField_A.getText().equals("")
							   || textField_B.getText().equals("") || textField_C.getText().equals("")
							   || textField_Band_Name.getText().equals("")
							   || textField_Places.getText().equals("") || textField_Dates.getText().equals("")
							   || textField_Ticket_num.getText().equals("") || textField_Total_Cost.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Choose Tickets!!!");
							}else{
					num1 = Integer.parseInt(textField_Tickets.getText());
					num2 = Integer.parseInt(textField_A.getText());
					num3 = Integer.parseInt(textField_B.getText());
					num4 = Integer.parseInt(textField_C.getText());
					num5 = Integer.parseInt(textField_Tick_A.getText());
					num6 = Integer.parseInt(textField_Tick_B.getText());
					num7 = Integer.parseInt(textField_Tick_C.getText());
					num8 = Integer.parseInt(textField_Ticket_num.getText());
					if (num8>0){
					
					if (num1 >= num2+num3+num4) {
						ans1 = num2 - num5;
						ans2 = num3 - num6;
						ans3 = num4 - num7;
						ans = num1 - num5 - num6 - num7;
						
						textField_Tickets.setText(Integer.toString(ans));
						textField_A.setText(Integer.toString(ans1));
						textField_B.setText(Integer.toString(ans2));
						textField_C.setText(Integer.toString(ans3));
						textField_Ticket_num.setText(Integer.toString(num8));
						
						String query = "update synaylies set band_name='" + textField_Band_Name.getText() + "'"
								+ ",places='"+ textField_Places.getText() + "',dates='" + textField_Dates.getText() + "'"
										+ ",tickets='"+ textField_Tickets.getText() + "',A='"+textField_A.getText()+"'"
												+ ",B='"+textField_B.getText()+"',C='"+textField_C.getText()+"' "
														+ "where synID = '" + textField_CC.getText() + "'";
						PreparedStatement pst = connection.prepareStatement(query);

						pst.execute();
						pst.close();

						String query1 = "insert into print_tickets (band_name,places,dates,ticket_num,A,B,C,Name,Sirname,total_cost,c_date,EMPid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement pst1 = connection.prepareStatement(query1);
						pst1.setString(1, textField_Band_Name.getText());
						
						pst1.setString(2, textField_Places.getText());
						pst1.setString(3, textField_Dates.getText());
						pst1.setString(4, textField_Ticket_num.getText());
						pst1.setString(5, textField_Tick_A.getText());
						pst1.setString(6, textField_Tick_B.getText());
						pst1.setString(7, textField_Tick_C.getText());
						pst1.setString(8, textField_Name.getText());
						pst1.setString(9, textField_Sirname.getText());
						pst1.setString(10, textField_Total_Cost.getText());
						pst1.setString(11, txtdate);
						pst1.setString(12, textField.getText());
						

						pst1.execute();

						JOptionPane.showMessageDialog(null, "Data Saved");

						pst1.close();
					} else {
						JOptionPane.showMessageDialog(null, "Not enough Tickets");
					}
					} else {
						JOptionPane.showMessageDialog(null, "Choose Tickets");
					}
							}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter a valid number for Tickets");
				}
				String fn = "" + textField_Band_Name.getText() + ".txt";

				try {
					PrintWriter outpustream = new PrintWriter(fn);
					outpustream.printf("сумаукиа: ");
					outpustream.printf(textField_Band_Name.getText());
					outpustream.printf(", топос диенацыцгс:");
					outpustream.printf(textField_Places.getText());
					outpustream.printf(", глеяолгмиа: ");
					outpustream.printf(textField_Dates.getText());
					outpustream.println('\n');
					outpustream.printf("аяихлос еисгтгяиым: ");
					outpustream.printf(textField_Ticket_num.getText());
					outpustream.printf(", а фымг: ");
					outpustream.printf(textField_Tick_A.getText());
					outpustream.printf(", б фымг: ");
					outpustream.printf(textField_Tick_B.getText());
					outpustream.printf(", C фымг: ");
					outpustream.printf(textField_Tick_C.getText());
					outpustream.println('\n');
					outpustream.printf("омола: ");
					outpustream.printf(textField_Name.getText());
					outpustream.printf(", епымуло: ");
					outpustream.printf(textField_Sirname.getText());
					outpustream.println('\n');
					outpustream.printf("сумокийо йостос: ");
					outpustream.printf(textField_Total_Cost.getText());
					outpustream.println('\n');
					outpustream.printf("йыдийос упаккгкоу: ");
					outpustream.printf(textField.getText());
					outpustream.println('\n');
//					outpustream.print(format);
					outpustream.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nooooooot");
				}
				refreshTable();
			}
		});
		btnPrintTicket.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnPrintTicket.setBounds(275, 550, 358, 46);
		contentPane.add(btnPrintTicket);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 50, 360, 103);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int val = 0;
				textField_Ticket_num.setText(Integer.toString(val));
				textField_Tick_A.setText(Integer.toString(val));
				textField_Tick_B.setText(Integer.toString(val));
				textField_Tick_C.setText(Integer.toString(val));
				textField_CC.setText("");
								
				try {
					int row = table.getSelectedRow();
					String synID_ = (table.getModel().getValueAt(row, 0).toString());

					String query = "select * from synaylies where synID='" + synID_ + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						textField_CC.setText(rs.getString("synID"));
						textField_Band_Name.setText(rs.getString("band_name"));
						textField_Places.setText(rs.getString("places"));
						textField_Dates.setText(rs.getString("dates"));
						textField_Tickets.setText(rs.getString("tickets"));
						textField_A.setText(rs.getString("A"));
						textField_B.setText(rs.getString("B"));
						textField_C.setText(rs.getString("C"));
					}

					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		lblCodeConcert = new JLabel("Code Concert");
		lblCodeConcert.setForeground(Color.WHITE);
		lblCodeConcert.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCodeConcert.setBounds(10, 570, 117, 26);
		contentPane.add(lblCodeConcert);

		textField_CC = new JTextField();
		textField_CC.setEditable(false);
		textField_CC.setForeground(Color.WHITE);
		textField_CC.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_CC.setBackground(Color.BLACK);
		textField_CC.setBounds(133, 570, 134, 27);
		contentPane.add(textField_CC);
		textField_CC.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 376, 117, -35);
		contentPane.add(lblNewLabel_1);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.BLACK);
		desktopPane.setBounds(275, 279, 360, 187);
		contentPane.add(desktopPane);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 360, 187);
		desktopPane.add(scrollPane_2);
		
		lblimage = new JLabel("");
		lblimage.setFont(new Font("Times New Roman", Font.BOLD, 20));
		scrollPane_2.setViewportView(lblimage);
		lblimage.setBackground(Color.BLACK);
		
		JLabel lblA = new JLabel("A zone");
		lblA.setForeground(Color.WHITE);
		lblA.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblA.setBounds(10, 210, 103, 26);
		contentPane.add(lblA);
		
		JLabel lblB = new JLabel("B zone");
		lblB.setForeground(Color.WHITE);
		lblB.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblB.setBounds(10, 250, 103, 26);
		contentPane.add(lblB);
		
		JLabel lblC = new JLabel("C zone");
		lblC.setForeground(Color.WHITE);
		lblC.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblC.setBounds(10, 290, 103, 26);
		contentPane.add(lblC);
		
		textField_A = new JTextField();
		textField_A.setEditable(false);
		textField_A.setForeground(Color.WHITE);
		textField_A.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_A.setBackground(Color.BLACK);
		textField_A.setBounds(133, 210, 134, 27);
		contentPane.add(textField_A);
		textField_A.setColumns(10);
		
		textField_B = new JTextField();
		textField_B.setEditable(false);
		textField_B.setForeground(Color.WHITE);
		textField_B.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_B.setBackground(Color.BLACK);
		textField_B.setBounds(133, 250, 134, 27);
		contentPane.add(textField_B);
		textField_B.setColumns(10);
		
		textField_C = new JTextField();
		textField_C.setEditable(false);
		textField_C.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_C.setForeground(Color.WHITE);
		textField_C.setBackground(Color.BLACK);
		textField_C.setBounds(133, 290, 134, 27);
		contentPane.add(textField_C);
		textField_C.setColumns(10);
		
		final JLabel lblTick_B = new JLabel("Tickets B");
		lblTick_B.setForeground(Color.WHITE);
		lblTick_B.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTick_B.setBounds(10, 370, 117, 26);
		contentPane.add(lblTick_B);
		
		final JLabel lblTick_C = new JLabel("Tickets C");
		lblTick_C.setForeground(Color.WHITE);
		lblTick_C.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTick_C.setBounds(10, 410, 117, 26);
		contentPane.add(lblTick_C);
		
		final JLabel lblTick_A = new JLabel("Tickets A");
		lblTick_A.setForeground(Color.WHITE);
		lblTick_A.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTick_A.setBounds(10, 330, 103, 28);
		contentPane.add(lblTick_A);
		
		textField_Tick_A = new JTextField();
		textField_Tick_A.setForeground(Color.WHITE);
		textField_Tick_A.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Tick_A.setBackground(Color.BLACK);
		textField_Tick_A.setBounds(133, 330, 134, 27);
		contentPane.add(textField_Tick_A);
		textField_Tick_A.setColumns(10);
		
		textField_Tick_B = new JTextField();
		textField_Tick_B.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Tick_B.setForeground(Color.WHITE);
		textField_Tick_B.setBackground(Color.BLACK);
		textField_Tick_B.setBounds(133, 370, 134, 27);
		contentPane.add(textField_Tick_B);
		textField_Tick_B.setColumns(10);
		
		textField_Tick_C = new JTextField();
		textField_Tick_C.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Tick_C.setForeground(Color.WHITE);
		textField_Tick_C.setBackground(Color.BLACK);
		textField_Tick_C.setBounds(133, 410, 134, 27);
		contentPane.add(textField_Tick_C);
		textField_Tick_C.setColumns(10);
		
		JButton btnTotal = new JButton("Total Cost");
		btnTotal.setForeground(Color.WHITE);
		btnTotal.setBackground(Color.BLACK);
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int  num1, num2, num3, num4, num5, num6, ans, TotalA, TotalB, TotalC, Total_Cost;
				int val = 0;
				try {
					if(textField_Tick_A.getText().equals("") || textField_Tick_B.getText().equals("")
					   || textField_Tick_C.getText().equals("") || textField_A.getText().equals("")
					   || textField_B.getText().equals("") || textField_C.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Choose Tickets!!!");
					}else{
					num1 = Integer.parseInt(textField_Tick_A.getText());
					num2 = Integer.parseInt(textField_Tick_B.getText());
					num3 = Integer.parseInt(textField_Tick_C.getText());
					num4 = Integer.parseInt(textField_A.getText());
					num5 = Integer.parseInt(textField_B.getText());
					num6 = Integer.parseInt(textField_C.getText());
					ans = num1 + num2 + num3;
					
					
					if (num1<=num4){
					TotalA = num1*120;
					if (num2<=num5){
					TotalB = num2*80;
					if (num3<=num6){
					TotalC = num3*40;
					Total_Cost = TotalA + TotalB + TotalC ;
			//		if (Total_Cost>0){
					
					textField_Ticket_num.setText(Integer.toString(ans));
					textField_Total_Cost.setText(Integer.toString(Total_Cost));
			//		}else{
			//			JOptionPane.showMessageDialog(null, "Choose Tickets!!!");
			//		}
					}else{
						JOptionPane.showMessageDialog(null, "Not enough Tickets for C zone");
						textField_Tick_C.setText(Integer.toString(val));						
					}
					}else{
						JOptionPane.showMessageDialog(null, "Not enough Tickets for B zone");
						textField_Tick_B.setText(Integer.toString(val));
					}
					}else{
						JOptionPane.showMessageDialog(null, "Not enough Tickets for A zone");
						textField_Tick_A.setText(Integer.toString(val));
					}
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter a valid number for Tickets");
				}
			}
		});
		btnTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnTotal.setBounds(275, 477, 189, 46);
		contentPane.add(btnTotal);
		
		lblSirname = new JLabel("Sirname");
		lblSirname.setForeground(Color.WHITE);
		lblSirname.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSirname.setBounds(10, 490, 103, 26);
		contentPane.add(lblSirname);
		
		lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblName.setBounds(10, 530, 103, 26);
		contentPane.add(lblName);
		
		textField_Sirname = new JTextField();
		textField_Sirname.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Sirname.setForeground(Color.WHITE);
		textField_Sirname.setBackground(Color.BLACK);
		textField_Sirname.setBounds(133, 490, 134, 27);
		contentPane.add(textField_Sirname);
		textField_Sirname.setColumns(10);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Name.setForeground(Color.WHITE);
		textField_Name.setBackground(Color.BLACK);
		textField_Name.setBounds(133, 530, 134, 27);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);
		
		lblTicketsCostA = new JLabel("Tickets Cost: A zone = 120$, B zone = 80$, C zone = 40$");
		lblTicketsCostA.setForeground(Color.WHITE);
		lblTicketsCostA.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTicketsCostA.setBounds(115, 11, 380, 26);
		contentPane.add(lblTicketsCostA);
		
		textField_Total_Cost = new JTextField();
		textField_Total_Cost.setEditable(false);
		textField_Total_Cost.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_Total_Cost.setForeground(Color.WHITE);
		textField_Total_Cost.setBackground(Color.BLACK);
		textField_Total_Cost.setBounds(474, 477, 159, 46);
		contentPane.add(textField_Total_Cost);
		textField_Total_Cost.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(275, 162, 356, 103);
		contentPane.add(scrollPane_1);
		
		area = new JTextArea(6,64);
		area.setEditable(false);
		area.setForeground(Color.WHITE);
		area.setBackground(Color.BLACK);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		scrollPane_1.setViewportView(area);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField.setForeground(Color.WHITE);
		textField.setBackground(Color.BLACK);
		textField.setBounds(551, 12, 39, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtEmpid = new JTextField();
		txtEmpid.setText("EMPid:");
		txtEmpid.setBackground(Color.BLACK);
		txtEmpid.setForeground(Color.WHITE);
		txtEmpid.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtEmpid.setEditable(false);
		txtEmpid.setBounds(495, 12, 58, 26);
		contentPane.add(txtEmpid);
		txtEmpid.setColumns(10);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.setBounds(592, 11, 41, 27);
		contentPane.add(btnNewButton);

		fillCompoBox();
	}
}
