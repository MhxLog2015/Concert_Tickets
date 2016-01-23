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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import net.proteanit.sql.DbUtils;

public class CEO extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final BufferedImage ScaledImage = null;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CEO frame = new CEO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JLabel lblBandName;
	private JLabel lblPlaces;
	private JLabel lblDates;
	private JLabel lblTickets;
	private JTextField textField_Band_Name;
	private JTextField textField_Places;
	private JTextField textField_Dates;
	private JTextField textField_Tickets;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnLoad_Employee_List;
	private JTextField textField_Name;
	private JTextField textField_Sirname;
	private JTextField textField_Age;
	private JTextField textField_Username;
	private JTextField textField_Password;
	private JTextField textField_Title;
	private JButton btnUpdate_1;
	private JButton btnDelete_1;
	private JTextField textField_CC;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JLabel lblA;
	private JTextField textField_A;
	private JLabel lblB;
	private JTextField textField_B;
	private JLabel lblC;
	private JTextField textField_C;
	private JTextField textField_Emp_ID;
	private JTextArea textArea_Description;
	private JScrollPane scrollPane_2;
	private JLabel lblimage;
	private JButton btnNewButton;
	private ImageIcon format = null;
	private ImageIcon format1 = null;
	private FileInputStream fis;
	
	String filename = null;
	byte[] poster = null;	
	private JScrollPane scrollPane_3;
	public byte[]imagedata;
	private JButton btnNewButton_1;
	
	private Image fix_it(Image imeg,int w,int h){         
		BufferedImage resizedImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);         
		Graphics2D g2=resizedImage.createGraphics();         
		g2.drawImage(imeg,0, 0, w,h,null);        
		g2.dispose();      
		return resizedImage;     
		}   

	public void refreshTable(){
		try {
		    String query="select synID,band_name,dates from synaylies";
		    PreparedStatement pst=connection.prepareStatement(query);
		    ResultSet rs=pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		    pst.close();
		    rs.close();
		    				    
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		lblimage.setIcon(format1);
		textField_CC.setText("");
		textField_Band_Name.setText("");
		textField_Places.setText("");
		textField_Dates.setText("");
		textField_Tickets.setText("");
		textField_A.setText("");
		textField_B.setText("");
		textField_C.setText("");
		textArea_Description.setText("");
	}
	public void refreshTable1(){
		try {
		    String query="select sirname, name from employeeinfo";
		    PreparedStatement pst=connection.prepareStatement(query);
		    ResultSet rs=pst.executeQuery();
		    table_1.setModel(DbUtils.resultSetToTableModel(rs));
		    pst.close();
		    rs.close();
		    				    
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		textField_Name.setText("");
		textField_Sirname.setText("");
		textField_Age.setText("");
		textField_Username.setText("");
		textField_Password.setText("");
		textField_Title.setText("");
		textField_Emp_ID.setText("");
	}
	/**
	 * Create the frame.
	 */
	public CEO() {
		setTitle("\u039A\u0391\u03A1\u03A4\u0395\u039B\u0391 CEO");
		setBackground(Color.BLACK);
		connection=DBconnect.dbConnector(); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 50, 852, 536);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JButton btnLoadData = new JButton("Load Concert Data");
		btnLoadData.setForeground(Color.WHITE);
		btnLoadData.setBackground(Color.BLACK);
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int val = 0;
				
				textField_Tickets.setText(Integer.toString(val));
				textField_A.setText(Integer.toString(val));
				textField_B.setText(Integer.toString(val));
				textField_C.setText(Integer.toString(val));
				try {
				    String query="select synID,band_name,dates from synaylies";
				    PreparedStatement pst=connection.prepareStatement(query);
				    ResultSet rs=pst.executeQuery();
				    table.setModel(DbUtils.resultSetToTableModel(rs));
				    pst.close();
				    rs.close();
				    				    
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "упаявеи кахос стгм еисацыцг дедолемым");
				}
			}
		});
		btnLoadData.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLoadData.setBounds(10, 11, 198, 45);
		contentPane.add(btnLoadData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 11, 201, 172);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				String synID_ = (table.getModel().getValueAt(row, 0).toString());
				try {
					
					String query = "select synID,band_name,places,dates,tickets,A,B,C,description from synaylies where synID='" + synID_ + "'";
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
						textArea_Description.setText(rs.getString("description"));
					}

					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					String query = "select image from synaylies where synID='"+synID_+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()){
						byte[] imagedata=rs.getBytes("image");                
						InputStream in = new ByteArrayInputStream(imagedata);                 
						BufferedImage bImageFromConvert = ImageIO.read(in);                 
						format=new ImageIcon(fix_it(bImageFromConvert,lblimage.getWidth(),lblimage.getHeight()));                  
						lblimage.setIcon(format);   
					}

					pst.close();
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		lblBandName = new JLabel("Band");
		lblBandName.setForeground(Color.WHITE);
		lblBandName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBandName.setBounds(10, 107, 44, 27);
		contentPane.add(lblBandName);
		
		lblPlaces = new JLabel("Places");
		lblPlaces.setForeground(Color.WHITE);
		lblPlaces.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPlaces.setBounds(10, 146, 75, 27);
		contentPane.add(lblPlaces);
		
		lblDates = new JLabel("Dates");
		lblDates.setForeground(Color.WHITE);
		lblDates.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDates.setBounds(10, 184, 75, 27);
		contentPane.add(lblDates);
		
		lblTickets = new JLabel("Tickets");
		lblTickets.setForeground(Color.WHITE);
		lblTickets.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTickets.setBounds(10, 260, 75, 27);
		contentPane.add(lblTickets);
		
		textField_Band_Name = new JTextField();
		textField_Band_Name.setForeground(Color.WHITE);
		textField_Band_Name.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Band_Name.setBackground(Color.BLACK);
		textField_Band_Name.setBounds(74, 107, 130, 28);
		contentPane.add(textField_Band_Name);
		textField_Band_Name.setColumns(10);
		
		textField_Places = new JTextField();
		textField_Places.setForeground(Color.WHITE);
		textField_Places.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Places.setBackground(Color.BLACK);
		textField_Places.setBounds(74, 146, 130, 27);
		contentPane.add(textField_Places);
		textField_Places.setColumns(10);
		
		textField_Dates = new JTextField();
		textField_Dates.setForeground(Color.WHITE);
		textField_Dates.setBackground(Color.BLACK);
		textField_Dates.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Dates.setBounds(74, 184, 130, 27);
		contentPane.add(textField_Dates);
		textField_Dates.setColumns(10);
		
		textField_Tickets = new JTextField();
		textField_Tickets.setEditable(false);
		textField_Tickets.setForeground(Color.WHITE);
		textField_Tickets.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Tickets.setBackground(Color.BLACK);
		textField_Tickets.setBounds(74, 260, 130, 27);
		contentPane.add(textField_Tickets);
		textField_Tickets.setColumns(10);
		
		
		btnSave = new JButton("Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.BLACK);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int num1,num2,num3,ans;
				
				try {
					if(textField_Band_Name.getText().equals("") || textField_Places.getText().equals("")
					  || textField_Dates.getText().equals("") || textArea_Description.getText().equals("")
					  || textField_A.getText().equals("") || textField_B.getText().equals("")
					  || textField_C.getText().equals("") || poster == null ){
						JOptionPane.showMessageDialog(null, "Please complete all Data");
					}else{		
					num1 = Integer.parseInt(textField_A.getText());
					num2 = Integer.parseInt(textField_B.getText());
					num3 = Integer.parseInt(textField_C.getText());
					ans = num1 + num2 + num3;
					textField_Tickets.setText(Integer.toString(ans));
					
				    String query="insert into synaylies (band_name,places,dates,description,A,B,C,tickets,image) values (?,?,?,?,?,?,?,?,?)";
				    PreparedStatement pst=connection.prepareStatement(query);
				    pst.setString(1,textField_Band_Name.getText() );
				    pst.setString(2,textField_Places.getText() );
				    pst.setString(3,textField_Dates.getText() );
				    pst.setString(4,textArea_Description.getText());
				    pst.setString(5,textField_A.getText());
				    pst.setString(6,textField_B.getText());
				    pst.setString(7,textField_C.getText());
				    pst.setString(8,textField_Tickets.getText());
				    pst.setBytes(9, poster);
				    pst.execute();
				    pst.close();
				    
				    poster = null;
				    
				    JOptionPane.showMessageDialog(null, "Data Saved");
					}
				    
				    				    
				} catch (Exception e) {
					e.printStackTrace();
					}
				refreshTable();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSave.setBounds(10, 311, 192, 35);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num1,num2,num3,ans;
				
				try {
					if(textField_Band_Name.getText().equals("") || textField_Places.getText().equals("")
							  || textField_Dates.getText().equals("") || textArea_Description.getText().equals("")
							  || textField_A.getText().equals("") || textField_B.getText().equals("")
							  || textField_C.getText().equals("") )
					{
								JOptionPane.showMessageDialog(null, "Please complete all Data");
					}else{	
					num1 = Integer.parseInt(textField_A.getText());
					num2 = Integer.parseInt(textField_B.getText());
					num3 = Integer.parseInt(textField_C.getText());
					ans = num1 + num2 + num3;
					
					textField_Tickets.setText(Integer.toString(ans));
					
				    String query="update synaylies set band_name='"+textField_Band_Name.getText()+"',"
				    		+ "places='"+textField_Places.getText()+"',dates='"+textField_Dates.getText()+"',"
				    				+ "A='"+textField_A.getText()+"',B='"+textField_B.getText()+"',C='"+textField_C.getText()+"',"
				    						+ "description='"+textArea_Description.getText()+"' where synID ='"+textField_CC.getText()+"' ";
				    PreparedStatement pst=connection.prepareStatement(query);
				    
				    
				    pst.execute();
				    pst.close();
				    
				    if (poster == null){
				    String query1="update synaylies set image=? where synID ='"+textField_CC.getText()+"'";
				    PreparedStatement ps=connection.prepareStatement(query1);
				    ps.setBytes(1, imagedata);
				    
				    
				    ps.execute();
				    ps.close();
				    }else{
				    	String query1="update synaylies set image=? where synID ='"+textField_CC.getText()+"'";
					    PreparedStatement pst1=connection.prepareStatement(query1);
					    pst1.setBytes(1, poster);
					    
					    pst1.execute();
					    pst1.close();
				    }
				    String query2="update synaylies set tickets = '"+textField_Tickets.getText()+"' where synID ='"+textField_CC.getText()+"' ";
				    PreparedStatement ps2=connection.prepareStatement(query2);
				    
				    
				    ps2.execute();
				    
				    JOptionPane.showMessageDialog(null, "Data Update");

				    ps2.close();
							}
				    				    
				} catch (Exception e) {
					e.printStackTrace();
					}
				refreshTable();
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUpdate.setBounds(10, 356, 192, 35);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.BLACK);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(textField_CC.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Please complete all Data");
							}else{	
				    String query="delete from synaylies where synID='"+textField_CC.getText()+"'";
				    PreparedStatement pst=connection.prepareStatement(query);
				    
				    
				    pst.execute();
				    
				    JOptionPane.showMessageDialog(null, "Data Deleted");

				    pst.close();
							} 
				    
				   
				    				    
				} catch (Exception e) {
					e.printStackTrace();
					}
				refreshTable();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDelete.setBounds(10, 402, 192, 39);
		contentPane.add(btnDelete);

		btnLoad_Employee_List = new JButton("Load Emp. List");
		btnLoad_Employee_List.setForeground(Color.WHITE);
		btnLoad_Employee_List.setBackground(Color.BLACK);
		btnLoad_Employee_List.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    String query="select sirname, name from employeeinfo";
				    PreparedStatement pst=connection.prepareStatement(query);
				    ResultSet rs=pst.executeQuery();
				    table_1.setModel(DbUtils.resultSetToTableModel(rs));
				    pst.close();
				    rs.close();
				    				    
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnLoad_Employee_List.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLoad_Employee_List.setBounds(621, 11, 198, 45);
		contentPane.add(btnLoad_Employee_List);
		
		textField_Name = new JTextField();
		textField_Name.setForeground(Color.WHITE);
		textField_Name.setBackground(Color.BLACK);
		textField_Name.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Name.setBounds(621, 107, 130, 27);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);
		
		textField_Sirname = new JTextField();
		textField_Sirname.setForeground(Color.WHITE);
		textField_Sirname.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Sirname.setBackground(Color.BLACK);
		textField_Sirname.setBounds(621, 146, 130, 27);
		contentPane.add(textField_Sirname);
		textField_Sirname.setColumns(10);
		
		textField_Age = new JTextField();
		textField_Age.setForeground(Color.WHITE);
		textField_Age.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Age.setBackground(Color.BLACK);
		textField_Age.setBounds(621, 184, 130, 27);
		contentPane.add(textField_Age);
		textField_Age.setColumns(10);
		
		textField_Username = new JTextField();
		textField_Username.setForeground(Color.WHITE);
		textField_Username.setBackground(Color.BLACK);
		textField_Username.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Username.setBounds(621, 222, 130, 27);
		contentPane.add(textField_Username);
		textField_Username.setColumns(10);
		
		textField_Password = new JTextField();
		textField_Password.setForeground(Color.WHITE);
		textField_Password.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Password.setBackground(Color.BLACK);
		textField_Password.setBounds(621, 260, 130, 27);
		contentPane.add(textField_Password);
		textField_Password.setColumns(10);
		
		textField_Title = new JTextField();
		textField_Title.setForeground(Color.WHITE);
		textField_Title.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Title.setBackground(Color.BLACK);
		textField_Title.setBounds(621, 298, 130, 27);
		contentPane.add(textField_Title);
		textField_Title.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(761, 107, 75, 27);
		contentPane.add(lblName);
		
		JLabel lblSirname = new JLabel("Sirname");
		lblSirname.setForeground(Color.WHITE);
		lblSirname.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSirname.setBounds(761, 146, 75, 28);
		contentPane.add(lblSirname);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAge.setBounds(761, 184, 75, 27);
		contentPane.add(lblAge);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsername.setBounds(761, 222, 75, 27);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPassword.setBounds(761, 260, 63, 27);
		contentPane.add(lblPassword);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTitle.setBounds(761, 298, 36, 27);
		contentPane.add(lblTitle);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBackground(Color.BLACK);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						if(textField_Name.getText().equals("") || textField_Sirname.getText().equals("")
						   || textField_Age.getText().equals("") || textField_Username.getText().equals("")
						   || textField_Password.getText().equals("") || textField_Title.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Please complete all Data");
						}else{
				    String query="insert into employeeinfo (name,sirname,age,username,userpass,title) values(?,?,?,?,?,?)";
				    PreparedStatement pst=connection.prepareStatement(query);
				    pst.setString(1, textField_Name.getText());
				    pst.setString(2, textField_Sirname.getText());
				    pst.setString(3, textField_Age.getText());
				    pst.setString(4, textField_Username.getText());
				    pst.setString(5, textField_Password.getText());
				    pst.setString(6, textField_Title.getText());
				    
				    pst.execute();
				    
				    JOptionPane.showMessageDialog(null, "Data Saved");

				    pst.close();
						}	    
				} catch (Exception e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Username all ready exist!!\n Insert anotherone.");
					}
				refreshTable1();
			}
		});
		btnInsert.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnInsert.setBounds(621, 356, 192, 35);
		contentPane.add(btnInsert);
		
		btnUpdate_1 = new JButton("Update");
		btnUpdate_1.setForeground(Color.WHITE);
		btnUpdate_1.setBackground(Color.BLACK);
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textField_Name.getText().equals("") || textField_Sirname.getText().equals("")
							   || textField_Age.getText().equals("") || textField_Username.getText().equals("")
							   || textField_Password.getText().equals("") || textField_Title.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Please complete all Data");
							}else{
				    String query="update employeeinfo set name='"+textField_Name.getText()+"'"
				    		+ ",sirname='"+textField_Sirname.getText()+"',age='"+textField_Age.getText()+"'"
				    				+ ",username='"+textField_Username.getText()+"',userpass='"+textField_Password.getText()+"'"
				    						+ ",title='"+textField_Title.getText()+"' where EMPid='"+textField_Emp_ID.getText()+"' ";
				    PreparedStatement pst=connection.prepareStatement(query);
				    
				    
				    pst.execute();
				    
				    JOptionPane.showMessageDialog(null, "Data Update");

				    pst.close();
							}
				    				    
				} catch (Exception e) {
					e.printStackTrace();
					}
				refreshTable1();
			}
		});
		btnUpdate_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUpdate_1.setBounds(621, 404, 192, 35);
		contentPane.add(btnUpdate_1);
		
		btnDelete_1 = new JButton("Delete");
		btnDelete_1.setForeground(Color.WHITE);
		btnDelete_1.setBackground(Color.BLACK);
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textField_Emp_ID.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Please complete all Data");
							}else{
				    String query="delete from employeeinfo where EMPid ='"+textField_Emp_ID.getText()+"'";
				    PreparedStatement pst=connection.prepareStatement(query);
				    
				    
				    pst.execute();
				    
				    JOptionPane.showMessageDialog(null, "Data Deleted");

				    pst.close();
							}
				    				    
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable1();
			}
		});
		btnDelete_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDelete_1.setBounds(621, 452, 192, 35);
		contentPane.add(btnDelete_1);
		
				JLabel lblCC = new JLabel("ID");
				lblCC.setForeground(Color.WHITE);
		lblCC.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCC.setBounds(10, 67, 75, 29);
		contentPane.add(lblCC);
		
		textField_CC = new JTextField();
		textField_CC.setEditable(false);
		textField_CC.setForeground(Color.WHITE);
		textField_CC.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_CC.setBackground(Color.BLACK);
		textField_CC.setBounds(74, 67, 130, 29);
		contentPane.add(textField_CC);
		textField_CC.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(415, 11, 201, 172);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table_1.getSelectedRow();
					String sirname_= (table_1.getModel().getValueAt(row, 0).toString());

					String query = "select * from employeeinfo where sirname='" + sirname_ + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						textField_Name.setText(rs.getString("name"));
						textField_Sirname.setText(rs.getString("sirname"));
						textField_Age.setText(rs.getString("age"));
						textField_Username.setText(rs.getString("username"));
						textField_Password.setText(rs.getString("userpass"));
						textField_Title.setText(rs.getString("title"));
						textField_Emp_ID.setText(rs.getString("EMPid"));
					}

					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		lblA = new JLabel("A");
		lblA.setForeground(Color.WHITE);
		lblA.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblA.setBounds(10, 222, 11, 27);
		contentPane.add(lblA);
		
		textField_A = new JTextField();
		textField_A.setForeground(Color.WHITE);
		textField_A.setBackground(Color.BLACK);
		textField_A.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_A.setBounds(28, 222, 44, 27);
		contentPane.add(textField_A);
		textField_A.setColumns(10);
		
		lblB = new JLabel("B");
		lblB.setForeground(Color.WHITE);
		lblB.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblB.setBounds(74, 222, 11, 27);
		contentPane.add(lblB);
		
		textField_B = new JTextField();
		textField_B.setForeground(Color.WHITE);
		textField_B.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_B.setBackground(Color.BLACK);
		textField_B.setBounds(90, 222, 44, 27);
		contentPane.add(textField_B);
		textField_B.setColumns(10);
		
		lblC = new JLabel("C");
		lblC.setForeground(Color.WHITE);
		lblC.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblC.setBounds(141, 222, 11, 27);
		contentPane.add(lblC);
		
		textField_C = new JTextField();
		textField_C.setForeground(Color.WHITE);
		textField_C.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_C.setBackground(Color.BLACK);
		textField_C.setBounds(160, 222, 44, 27);
		contentPane.add(textField_C);
		textField_C.setColumns(10);
		
		textField_Emp_ID = new JTextField();
		textField_Emp_ID.setEditable(false);
		textField_Emp_ID.setForeground(Color.WHITE);
		textField_Emp_ID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_Emp_ID.setBackground(Color.BLACK);
		textField_Emp_ID.setBounds(621, 67, 130, 27);
		contentPane.add(textField_Emp_ID);
		textField_Emp_ID.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblId.setBounds(761, 67, 74, 27);
		contentPane.add(lblId);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(212, 192, 404, 120);
		contentPane.add(scrollPane_2);
		
		textArea_Description = new JTextArea();
		textArea_Description.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textArea_Description.setForeground(Color.WHITE);
		textArea_Description.setBackground(Color.BLACK);
		scrollPane_2.setViewportView(textArea_Description);
		textArea_Description.setLineWrap(true);
		textArea_Description.setWrapStyleWord(true);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(212, 322, 404, 119);
		contentPane.add(desktopPane);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 404, 119);
		desktopPane.add(scrollPane_3);
		
		lblimage = new JLabel("");
		scrollPane_3.setViewportView(lblimage);
		lblimage.setBackground(Color.BLACK);

		btnNewButton = new JButton("Attach image");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				
				try{
					
					File image = new File(filename);
					fis = new FileInputStream(image);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readnum; (readnum = fis.read(buf))!=-1;){
						bos.write(buf,0,readnum);
					}
					poster = bos.toByteArray();
				}
				catch(IOException ex){
					JOptionPane.showMessageDialog(null, ex);
				}
				
				
		}

			
			
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(10, 452, 192, 35);
		contentPane.add(btnNewButton);
		
		JButton btnTotal_stats = new JButton("Tickets Stats");
		btnTotal_stats.setForeground(Color.WHITE);
		btnTotal_stats.setBackground(Color.BLACK);
		btnTotal_stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a_zone = null, b_zone = null, c_zone = null, ticket_num = null;
				try{
				String query = "select Sum(A),Sum(B),Sum(C), Sum(ticket_num) from print_tickets ";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				
				while (rs.next()) {
					a_zone = rs.getString("Sum(A)");
					b_zone = rs.getString("Sum(B)");
					c_zone = rs.getString("Sum(C)");
					ticket_num = rs.getString("Sum(ticket_num)");
				}

				pst.close();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				DefaultPieDataset pieDataset = new DefaultPieDataset();
				pieDataset.setValue("A zone Tickets = "+a_zone+"", new Integer(a_zone));
				pieDataset.setValue("B zone Tickets = "+b_zone+"", new Integer(b_zone));
				pieDataset.setValue("C zone Tickets = "+c_zone+"", new Integer(c_zone));
				JFreeChart chart = ChartFactory.createPieChart3D("Total Tickets = "+ticket_num+"", pieDataset, true, true, true);
				PiePlot3D p=(PiePlot3D)chart.getPlot();
				p.setStartAngle(90);
				p.setDirection(Rotation.CLOCKWISE);
				p.setForegroundAlpha(1.0f);
				ChartFrame frame = new ChartFrame("Total Tickets Pie Chart",chart);
				frame.setVisible(true);
				frame.setSize(500,500);
			}
		});
		btnTotal_stats.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnTotal_stats.setBounds(212, 452, 169, 35);
		contentPane.add(btnTotal_stats);
		
		JButton btnBand_Stats = new JButton("Band Statistics");
		btnBand_Stats.setForeground(Color.WHITE);
		btnBand_Stats.setBackground(Color.BLACK);
		btnBand_Stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int A_zone = 0 ,B_zone = 0 ,C_zone = 0;
				
				
								
				try{
					if(textField_Band_Name.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Choose a Band");					
					}else{
				String query = "select Sum(A),Sum(B),Sum(C), Sum(ticket_num) from print_tickets where band_name = '"+textField_Band_Name.getText()+"'";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				
				while (rs.next()) {
					A_zone = rs.getInt("Sum(A)");
					B_zone = rs.getInt("Sum(B)");
					C_zone = rs.getInt("Sum(C)");
				}

				pst.close();
				
				DefaultCategoryDataset Dataset = new DefaultCategoryDataset();
				Dataset.setValue(new Integer(A_zone),"Tickets","A");
				Dataset.setValue(new Integer(B_zone),"Tickets","B");
				Dataset.setValue(new Integer(C_zone),"Tickets","C");
				JFreeChart chart = ChartFactory.createBarChart3D("Tickets For Band "+textField_Band_Name.getText()+"", "zone", "Tickets", Dataset, PlotOrientation.VERTICAL, false, true, false);
				chart.setBackgroundPaint(Color.LIGHT_GRAY);
				chart.getTitle().setPaint(Color.RED);
				CategoryPlot p = chart.getCategoryPlot();
				p.setRangeGridlinePaint(Color.BLUE);
				ChartFrame frame = new ChartFrame("Bar Chart for Bands", chart);
				frame.setVisible(true);
				frame.setSize(600,500);
				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			});
		btnBand_Stats.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBand_Stats.setBounds(445, 452, 169, 35);
		contentPane.add(btnBand_Stats);
		
		btnNewButton_1 = new JButton("\u03A7");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_1.setBounds(391, 452, 44, 34);
		contentPane.add(btnNewButton_1);
		
	}
}
