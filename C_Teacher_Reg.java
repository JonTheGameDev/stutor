import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class C_Teacher_Reg extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane;
    private JPanel personalPanel, educationalPanel;
    private JTextField nameField, ageField, idField, emailField, language, subjectField, experienceField, qualificationField;
    private JTextArea addressField;
    private JScrollPane sp;
    private JButton submitButton,backButton;
    public C_Teacher_Reg() {
        setTitle("Teacher Registration Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        personalPanel = new JPanel();
        personalPanel.setBackground(new Color(116,198,157));
        personalPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        personalPanel.setLayout(new GridLayout(5, 2,5,5));
        personalPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        personalPanel.add(nameField);
        personalPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        personalPanel.add(ageField);
        personalPanel.add(new JLabel("Address:"));
        addressField = new JTextArea();
        sp =new JScrollPane(addressField);
        personalPanel.add(sp);
        personalPanel.add(new JLabel("Teacher ID:"));
        idField = new JTextField();
        personalPanel.add(idField);
        personalPanel.add(new JLabel("email ID:"));
        emailField = new JTextField();
        personalPanel.add(emailField);
        educationalPanel = new JPanel();
        educationalPanel.setBackground(new Color(116,198,157));
        educationalPanel.setBorder(BorderFactory.createTitledBorder("Educational Details"));
        educationalPanel.setLayout(new GridLayout(4, 2,5,5));
        educationalPanel.add(new JLabel("Languages known:"));
        language = new JTextField();
        educationalPanel.add(language);
        educationalPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        educationalPanel.add(subjectField);
        educationalPanel.add(new JLabel("Years of Experience:"));
        experienceField = new JTextField();
        educationalPanel.add(experienceField);
        educationalPanel.add(new JLabel("Qualification:"));
        qualificationField = new JTextField();
        educationalPanel.add(qualificationField);
        tabbedPane.addTab("Personal Details", personalPanel);
        tabbedPane.addTab("Educational Details", educationalPanel);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        backButton = new JButton("<-Back");
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(submitButton,BorderLayout.CENTER);
        p.add(backButton,BorderLayout.LINE_START);
        add(tabbedPane, BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		C_Admin ob= new C_Admin();
                ob.setVisible(true);
                dispose();
        	}
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
	            Statement stmt=con.createStatement();
                String name=nameField.getText();
                String age=ageField.getText();
                String addr=addressField.getText();
                String id=idField.getText();
                String mail=emailField.getText();
                String lan=language.getText();
                String sub=subjectField.getText();
                String yoe=experienceField.getText();
                String quali=qualificationField.getText();
                if(!name.equals("")&&!age.equals("")&&!addr.equals("")&&!id.equals("")&&!mail.equals("")&&!lan.equals("")&&!sub.equals("")&&!yoe.equals("")&&!quali.equals("")) {
                	boolean pass=true;
                	int Age=0;
                	try {
                		Age=Integer.parseInt(age);
                		if(Age<22||Age>=60) {pass=false;JOptionPane.showMessageDialog(null, "Incorrect Age given!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
                	}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Enter only digits in Age field!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
                	try {Age=Integer.parseInt(id);}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Enter only digits in ID Field!","Registration Failed", JOptionPane.ERROR_MESSAGE);}
                	if(pass) {
                		stmt.executeUpdate("insert into teachers values('"+name+"',"+Integer.parseInt(age)+",'"+addr+"',"+Integer.parseInt(id)+",'"+mail+"','"+lan+"','"+sub+"','"+yoe+"','"+quali+"')");
	                	stmt.executeUpdate("insert into user_lgn values('"+name+"','"+id+"','teacher')");
	                	JOptionPane.showMessageDialog(null, "Data as been registered successfully!");
	                	C_Admin ob= new C_Admin();
	                    ob.setVisible(true);
	                    dispose();
                	}
                }
                else
                	JOptionPane.showMessageDialog(null, "One of the field is empty!","Registration Failed", JOptionPane.ERROR_MESSAGE);
        	}catch(Exception er){ System.out.println(er.getMessage());}
        }
    }
    public static void main(String[] args) {
        JFrame frame = new C_Teacher_Reg();
        frame.setVisible(true);
    }
}
