import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class Teacher_Page {
	int id;
	Teacher_Page(int id){
		this.id=id;
	}
	public void build() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
            Statement stmt=con.createStatement();
			JFrame f=new JFrame();
			JPanel p=new JPanel();
			p.setLayout(null);
			ResultSet rs1=stmt.executeQuery("select name from teachers where id="+id);
			System.out.println(id);
			rs1.next();
			System.out.println("Pass Point 1");
			f.setTitle("Staff "+rs1.getString(1)+"'s Attendance Register");
			System.out.println("Pass Point 2");
			f.setSize(500,300);
			f.setResizable(false);
			p.setBackground(new Color(116,198,157));
			ArrayList<String> names=new ArrayList<>();
			ResultSet rs=stmt.executeQuery("select * from students");
			System.out.println("Pass Point 3");
			while(rs.next()) {
				if(rs.getInt(13)==id)
					names.add(rs.getString(1)+"("+rs.getInt(8)+")");
			}
			System.out.println("Pass Point 4");
			JButton backbtn=new JButton("<-Back");
			backbtn.setBounds(5,5,80,30);
	        p.add(backbtn);
			JComboBox<String> students = new JComboBox<>(names.toArray(new String[0]));
			students.setBounds(70,70,150,30);
	        p.add(students);
	        String[] Months = {"January","February","March","April"};
	        JComboBox<String> months = new JComboBox<>(Months);
	        months.setBounds(250,70,150,30);
	        p.add(months);
	        JButton updatebtn=new JButton("Update Attendance");
	        updatebtn.setBounds(70,130,150,30);
	        p.add(updatebtn);
	        JButton viewbtn=new JButton("View Attendance");
	        viewbtn.setBounds(250,130,150,30);
	        p.add(viewbtn);
	        f.add(p);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			updatebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name=students.getSelectedItem().toString();
					int l=name.length();
					int admno=Integer.parseInt(name.substring(l-6,l-1));
					int month=months.getSelectedIndex();
					Update_Attend ob=new Update_Attend(month,admno);
					ob.build();
				}
			});
			viewbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name=students.getSelectedItem().toString();
					int l=name.length();
					int admno=Integer.parseInt(name.substring(l-6,l-1));
					int month=months.getSelectedIndex();
					View_Attend ob=new View_Attend(month,admno);
					ob.build();
				}
			});
			backbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CustomizedLoginPage.main(null);
					f.dispose();
				}
			});
		}catch(Exception e){ System.out.println(e.getMessage());}
	}
}
