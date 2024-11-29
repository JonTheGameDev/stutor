import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class C_Student_Reg extends JFrame {
    public C_Student_Reg() {
        setTitle("Student Registration Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new GridLayout(6, 2, 10, 10));
        p1.setBackground(new Color(116,198,157));
        JLabel l1 = new JLabel("Name:");
        p1.add(l1);
        JTextField nameField = new JTextField();
        p1.add(nameField);
        JLabel l2 = new JLabel("Date of Birth (dd/mm/yyyy):");
        p1.add(l2);
        JTextField dobField = new JTextField();
        p1.add(dobField);
        JLabel l3 = new JLabel("Gender:");
        p1.add(l3);
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setActionCommand("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setActionCommand("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        p1.add(genderPanel);
        JLabel l4 = new JLabel("Contact:");
        p1.add(l4);
        JTextField contactField = new JTextField();
        p1.add(contactField);
        JLabel l5 = new JLabel("Father Name:");
        p1.add(l5);
        JTextField fathername = new JTextField();
        p1.add(fathername);
        JLabel l6 = new JLabel("Mother Name:");
        p1.add(l6);
        JTextField mothername = new JTextField();
        p1.add(mothername);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(7, 2, 10, 10));
        p2.setBackground(new Color(116,198,157));
        JLabel l7 = new JLabel("Roll Number:");
        p2.add(l7);
        JTextField rollNumberField = new JTextField();
        p2.add(rollNumberField);
        JLabel l8 = new JLabel("Admission Number:");
        p2.add(l8);
        JTextField admissionnumberfield = new JTextField();
        p2.add(admissionnumberfield);
        JLabel l9 = new JLabel("Course:");
        p2.add(l9);
        String[] courses = {"B.Tech", "B.E", "B.Sc", "B.Com", "MBA"};
        JComboBox<String> courseBox = new JComboBox<>(courses);
        p2.add(courseBox);
        JLabel l10 = new JLabel("Degree:");
        String[] degrees = {"CSE","ECE","EEE","Bio-Technology","Mechanical","Civil","Bio-Medical","AIDS","IT"};
        JComboBox<String> degree = new JComboBox<>(degrees);
        p2.add(l10);
        p2.add(degree);
        JLabel l11 = new JLabel("Year:");
        p2.add(l11);
        String[] years = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
        JComboBox<String> yearBox = new JComboBox<>(years);
        p2.add(yearBox);
        JLabel l12 = new JLabel("CGPA:");
        p2.add(l12);
        JTextField cgpa = new JTextField();
        p2.add(cgpa);
        JLabel l13 = new JLabel("Staff Advisor ID:");
        p2.add(l13);
        JTextField staff = new JTextField();
        p2.add(staff);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personal Details", p1);
        tabbedPane.addTab("Education Details", p2);
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("<-Back");
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(submitButton,BorderLayout.CENTER);
        p.add(backButton,BorderLayout.LINE_START);
        add(tabbedPane, BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		C_Admin ob= new C_Admin();
                ob.setVisible(true);
                dispose();
        	}
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
		            Statement stmt=con.createStatement();
	                String name=nameField.getText();
	                String dob=dobField.getText();
	                String contact=contactField.getText();
	                String fname=fathername.getText();
	                String mname=mothername.getText();
	                String rno=rollNumberField.getText();
	                String adno=admissionnumberfield.getText();
	                String course=courseBox.getSelectedItem().toString();
	                String Degree=degree.getSelectedItem().toString();
	                String year=yearBox.getSelectedItem().toString();
	                String Cgpa=cgpa.getText();
	                String Staff=staff.getText();
	                int staffid=0;
	                if(!name.equals("")&&!dob.equals("")&&!contact.equals("")&&!fname.equals("")&&!mname.equals("")&&!rno.equals("")&&!adno.equals("")&&!Cgpa.equals("")&&!Staff.equals("")) {
	                	if(genderGroup.getSelection()!=null)
	                	{
	                		String gender=genderGroup.getSelection().getActionCommand();
	                		if(!(contact.length()>10||contact.length()<10)) {
	                			boolean pass=true;
	                			try {Long.parseLong(contact);}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Only digits should be present in contact field!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
	                			try {
	                				float cg=Float.parseFloat(Cgpa);
	                				if(cg>10|cg<0) {
	                					pass=false;
	                					JOptionPane.showMessageDialog(null, "Cgpa must be between 0 and 1!","Registration Failed", JOptionPane.ERROR_MESSAGE);
	                				}
	                			}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Only digits should be present in cgpa field!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
	                			try {int Adno =Integer.parseInt(adno);
	                				if(Adno<10000||Adno>99999) {
	                					pass=false;
	                					JOptionPane.showMessageDialog(null, "Admission number should be 5 digits!","Registration Failed", JOptionPane.ERROR_MESSAGE);
	                				}
	                			}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Only digits should be present in Admission number field!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
	                			try {
	                				Integer.parseInt(dob.substring(0,2));
	                				Integer.parseInt(dob.substring(3,5));
	                				Integer.parseInt(dob.substring(6,10));
	                			}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Date of birth given in wrong format!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
	                			if(dob.charAt(2)!='/'||dob.charAt(5)!='/') {pass=false;JOptionPane.showMessageDialog(null, "Date of birth given in wrong format!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
	                			try {
	                				ResultSet rs=stmt.executeQuery("select * from teachers");
	                				staffid=Integer.parseInt(Staff);
	                				boolean found=false;
	                				while(rs.next()) {
	                					if(rs.getInt(4)==staffid)
	                						found=true;
	                				}
	                				if(!found) {
	                					pass=false;
	                					JOptionPane.showMessageDialog(null, "Invalid Staff ID!","Registration Failed", JOptionPane.ERROR_MESSAGE);
	                				}	
	                			}catch(Exception err){ System.out.println(err.getMessage());}
	                			if(pass) {
	    		                	stmt.executeUpdate("insert into students values('"+name+"','"+dob+"','"+gender+"',"+Long.parseLong(contact)+",'"+fname+"','"+mname+"','"+rno+"',"+Integer.parseInt(adno)+",'"+course+"','"+Degree+"','"+year+"',"+Float.parseFloat(Cgpa)+","+staffid+")");
	    		                	stmt.executeUpdate("insert into user_lgn values('"+name+"','"+adno+"','student')");
	    		                	stmt.executeUpdate("insert into attendance values('"+adno+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"','"+fill()+"')");
	    		                	JOptionPane.showMessageDialog(null, "Data as been registered successfully!");
	    		                	C_Admin ob= new C_Admin();
	    		                    ob.setVisible(true);
	    		                    dispose();
	                			}
	                		}
	                		else
	                    		JOptionPane.showMessageDialog(null, "Contact number should be 10 digits!","Registration Failed", JOptionPane.ERROR_MESSAGE);
	                	}
	                	else
	                		JOptionPane.showMessageDialog(null, "Gender is not given!","Registration Failed", JOptionPane.ERROR_MESSAGE);
	                }
	                else
	                	JOptionPane.showMessageDialog(null, "One of the field is empty!","Registration Failed", JOptionPane.ERROR_MESSAGE);
            }catch(Exception er){ System.out.println(er.getMessage());}
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                C_Admin ob= new C_Admin();
                ob.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        JFrame f = new C_Student_Reg();
    }
    static String fill() {
    	StringBuilder s=new StringBuilder();
    	for(int i=0;i<31;i++) 
    		s.append("N/A-");
    	return s.toString();
    }
}