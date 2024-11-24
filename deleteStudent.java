import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class deleteStudent extends JFrame implements ActionListener{
    JTextField stext;
    public deleteStudent(){
        setTitle("Delete Student Entry");
        setSize(400, 250);
        setResizable(false);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(116,198,157));
        p1.setLayout(null);
        JLabel sid = new JLabel("Student Admission number: ");
        sid.setBounds(40,50,200,30);
        p1.add(sid);
        stext = new JTextField(15);
        stext.setBounds(210,50,130,30);
        p1.add(stext);
        JButton delete = new JButton("Delete");
        delete.setBounds(130,120,100,30);
        p1.add(delete);
        delete.addActionListener(this);
        add(p1,BorderLayout.CENTER);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
            Statement stmt=con.createStatement();
            String admissionnumber = stext.getText();
            int adno=0;
            boolean pass=true,found=false;
            try {adno=Integer.parseInt(admissionnumber);}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Enter only digits in the Field!","Deletion Failed", JOptionPane.ERROR_MESSAGE);}
            if(pass) {
            	ResultSet rs=stmt.executeQuery("select * from students");
            	int Adno;
            	String name="";
            	while(rs.next()) {
            		Adno=rs.getInt(8);
            		if(Adno==adno) {
            			found=true;
            			name=rs.getString(1);
            		}
            	}
            	if(found) {
            		stmt.executeUpdate("delete from students where admission_no="+adno);
            		stmt.executeUpdate("delete from user_lgn where passwd='"+adno+"'");
            		stmt.executeUpdate("delete from attendance where admno="+adno);
            		JOptionPane.showMessageDialog(null, "Data has been deleted successfully");
            		C_Admin ob= new C_Admin();
                    ob.setVisible(true);
                    dispose();
            	}
            	else
            		JOptionPane.showMessageDialog(null, "Admission number not found!", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
            }
    	}catch(Exception er){ System.out.println(er.getMessage());}
        
    }
    public static void main(String[] args){
        deleteStudent f = new deleteStudent();
    }
}