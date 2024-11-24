import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class deleteTeacher extends JFrame implements ActionListener {
    JTextField stext;
    public deleteTeacher(){
        setTitle("Delete Teacher Entry");
        setSize(400, 250);
        setResizable(false);
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(116,198,157));
        JLabel sid = new JLabel("Teacher ID: ");
        sid.setBounds(80,50,150,30);
        p1.add(sid);
        stext = new JTextField(15);
        stext.setBounds(170,50,130,30);
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
            String teacherid = stext.getText();
            int id=0;
            boolean pass=true,found=false;
            try {id=Integer.parseInt(teacherid);}catch(Exception err) {pass=false;JOptionPane.showMessageDialog(null, "Enter only digits in the Field!","Deletion Failed", JOptionPane.ERROR_MESSAGE);}
            if(pass) {
            	ResultSet rs=stmt.executeQuery("select * from teachers");
            	int Id;
            	String name="";
            	while(rs.next()) {
            		Id=rs.getInt(4);
            		if(Id==id) {
            			found=true;
            			name=rs.getString(1);
            		}
            	}
            	if(found) {
            		stmt.executeUpdate("delete from teachers where id="+id);
            		stmt.executeUpdate("delete from user_lgn where passwd='"+id+"'");
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
        deleteTeacher f1 = new deleteTeacher();
    }
}
