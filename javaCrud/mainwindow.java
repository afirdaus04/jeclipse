package javaCrud;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class mainwindow {

	private JFrame frame;
	private JTextField textbookname;
	private JTextField textedition;
	private JTextField textprice;
	private JTable table;
	private JTextField textbookid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainwindow window = new mainwindow();
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
	public mainwindow() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
		
	public void Connect ()
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
			}
						
			catch (ClassNotFoundException ex)
			{
	
			}
			catch (SQLException ex)
			{
			
			}		
		}
	
	public void table_load()
		{
				try 
				{
					pst = con.prepareStatement("select * from book");
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				
				
				catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(300, 11, 162, 76);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(45, 80, 308, 147);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 31, 87, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 70, 87, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 111, 87, 14);
		panel.add(lblNewLabel_1_1_1);
		
		textbookname = new JTextField();
		textbookname.setBounds(107, 30, 173, 20);
		panel.add(textbookname);
		textbookname.setColumns(10);
		
		textedition = new JTextField();
		textedition.setColumns(10);
		textedition.setBounds(107, 69, 173, 20);
		panel.add(textedition);
		
		textprice = new JTextField();
		textprice.setColumns(10);
		textprice.setBounds(107, 110, 173, 20);
		panel.add(textprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(45, 232, 87, 48);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(156, 232, 87, 48);
		frame.getContentPane().add(btnExit);
		
	
		// Clear Button
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textbookname.setText("");
				textedition.setText("");
				textprice.setText("");
				textbookname.requestFocus();
				
				
			}
		});
		
		
		btnClear.setBounds(266, 232, 87, 48);
		frame.getContentPane().add(btnClear);
		
		JScrollPane javatable = new JScrollPane();
		javatable.setBounds(381, 80, 310, 200);
		frame.getContentPane().add(javatable);
		
		table = new JTable();
		javatable.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(45, 291, 308, 76);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		// Develop Table
		textbookid = new JTextField();
		textbookid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			
			{
				
				try 
				{
					String id = textbookid.getText();
						
						pst = con.prepareStatement("select name,edition,price from book where id = ?");
						pst.setString(1, id);
						ResultSet rs = pst.executeQuery();
						
					if(rs.next()==true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						textbookname.setText(name);
						textedition.setText(edition);
						textprice.setText(price);
						
					}
					
					else
					{
						textbookname.setText("");
						textedition.setText("");
						textprice.setText("");
					}
					
				}
				
				catch (SQLException ex) 
				
				{
					
				}
					
				
			}
		});
		textbookid.setColumns(10);
		textbookid.setBounds(107, 32, 173, 20);
		panel_1.add(textbookid);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(32, 33, 65, 14);
		panel_1.add(lblNewLabel_1_1_2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// Develop Update button 
				String bookname,edition,price,bid;
				
				bookname = textbookname.getText();
				edition = textedition.getText();
				price = textprice.getText();
				bid = textbookid.getText();
				
				
				try {
					pst = con.prepareStatement("update book set name= ?,edition= ?, price=? where id= ?");
					pst.setString (1, bookname);
					pst.setString (2, edition);
					pst.setString (3, price);
					pst.setString (4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					table_load();
					textbookname.setText("");
					textedition.setText("");
					textprice.setText("");
					textbookname.requestFocus();
				}
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnUpdate.setBounds(381, 307, 87, 48);
		frame.getContentPane().add(btnUpdate);
		

		// Develop Delete Button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bid;
				bid = textbookid.getText();
				
				
				try {
					pst = con.prepareStatement("delete from book where id= ?");
					pst.setString (1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted");
					table_load();
					textbookname.setText("");
					textedition.setText("");
					textprice.setText("");
					textbookname.requestFocus();
				}
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnDelete.setBounds(478, 307, 87, 48);
		frame.getContentPane().add(btnDelete);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
				
			}
		});
		
		// Develop Save Button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookname,edition,price;
				
				bookname = textbookname.getText();
				edition = textedition.getText();
				price = textprice.getText();
				
				try {
					pst = con.prepareStatement("insert into book(name, edition, price)values(?,?,?)");
					pst.setString (1, bookname);
					pst.setString (2, edition);
					pst.setString (3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Added Record");
					table_load();
					textbookname.setText("");
					textedition.setText("");
					textprice.setText("");
					textbookname.requestFocus();
				}
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
	}
}
