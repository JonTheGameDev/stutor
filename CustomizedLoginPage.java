import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class CustomizedLoginPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);  // Set background color of the panel
        panel.setLayout(null);  // Absolute positioning
        JLabel labelLoginAs = new JLabel("Login as:");
        labelLoginAs.setBounds(50, 20, 100, 30);
        labelLoginAs.setForeground(Color.BLUE);  // Change font color
        labelLoginAs.setFont(new Font("Arial", Font.BOLD, 14));  // Change font style
        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setBounds(50, 70, 100, 30);
        labelUsername.setForeground(Color.BLACK);
        labelUsername.setFont(new Font("Arial", Font.BOLD, 14));  // Set font style
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(50, 120, 100, 30);
        labelPassword.setForeground(Color.BLACK);
        labelPassword.setFont(new Font("Arial", Font.BOLD, 14));  // Set font style
        String[] roles = {"Admin","Teacher", "Student"};
        JComboBox<String> comboRole = new JComboBox<>(roles);
        comboRole.setBounds(150, 20, 150, 30);
        comboRole.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField textUsername = new JTextField();
        textUsername.setBounds(150, 70, 150, 30);
        textUsername.setFont(new Font("Arial", Font.PLAIN, 14));  // Set font style
        textUsername.setForeground(Color.DARK_GRAY);  // Change font color
        JPasswordField textPassword = new JPasswordField();
        textPassword.setBounds(150, 120, 150, 30);
        textPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        textPassword.setForeground(Color.DARK_GRAY);  // Change font color
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 180, 100, 30);
        btnLogin.setBackground(Color.GREEN);  // Set background color
        btnLogin.setForeground(Color.WHITE);  // Set font color
        btnLogin.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(labelLoginAs);
        panel.add(comboRole);
        panel.add(labelUsername);
        panel.add(textUsername);
        panel.add(labelPassword);
        panel.add(textPassword);
        panel.add(btnLogin);
        frame.add(panel);
        frame.setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
	            	Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
		            Statement stmt=con.createStatement();
		            ResultSet rs=stmt.executeQuery("select * from user_lgn");
	                String selectedRole = comboRole.getSelectedItem().toString();
	                String username = textUsername.getText();
	                String password = new String(textPassword.getPassword());
	                String n,p,u;
	                boolean found=false;
	                if(!username.equals("")&&!password.equals("")) {
		                if (selectedRole.equals("Teacher")) {
		                	while(rs.next()) {
		                		n=rs.getString(1);
		                		p=rs.getString(2);
		                		u=rs.getString(3);
		                		if(username.equals(n) && password.equals(p) && u.equals("teacher")) {
		                			found=true;
		                		}
		                	}
		                	if(found) {
		                		Teacher_Page ob=new Teacher_Page(Integer.parseInt(password));
		                		ob.build();
		                		JOptionPane.showMessageDialog(null, "Logged in successfully!");
		                		frame.dispose();
		                	}
		                	else
		                		JOptionPane.showMessageDialog(frame, "Invalid Teacher credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
		                } else if (selectedRole.equals("Student")) {
		                	while(rs.next()) {
		                		n=rs.getString(1);
		                		p=rs.getString(2);
		                		u=rs.getString(3);
		                		if(username.equals(n) && password.equals(p) && u.equals("student")) {
		                			found=true;
		                		}
		                	}
		                	if(found) {
		                		View_Attend ob=new View_Attend(0,Integer.parseInt(password));
		                		ob.build();
		                		JOptionPane.showMessageDialog(null, "Logged in successfully!");
		                	}
		                	else
		                		JOptionPane.showMessageDialog(frame, "Invalid student credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
		                } else if (selectedRole.equals("Admin")) {
		                	while(rs.next()) {
		                		n=rs.getString(1);
		                		p=rs.getString(2);
		                		u=rs.getString(3);
		                		if(username.equals(n) && password.equals(p) && u.equals("admin")) {
		                			found=true;
		                		}
		                	}
		                	if(found) {
		                		C_Admin ad=new C_Admin();
		                		ad.setVisible(true);
		                		frame.setVisible(false);
		                	}
		                	else
		                		JOptionPane.showMessageDialog(frame, "Invalid Admin credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
		                }
	                }
	                else
	                	JOptionPane.showMessageDialog(frame, "One of the field is empty!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            	}catch(Exception err){ System.out.println(err.getMessage());}
            }
        });
        frame.setVisible(true);
    }
}