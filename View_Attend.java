import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
public class View_Attend {
	int month;
	int id;
	public View_Attend(int m,int id) {
		month=m;
		this.id=id;
	}
	public void build() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from attendance");
            StringTokenizer st=null;
            while(rs.next()) {
            	if(rs.getInt(1)==id)
            		st=new StringTokenizer(rs.getString(month+2),"-");
            }
			String []months={"January","February","March","April","May","June","July","August","September","October","November","December"};
			JFrame jf = new JFrame();
			ResultSet rs1=stmt.executeQuery("select name from students where admission_no="+id);
			rs1.next();
			jf.setTitle("Attendance of student: "+rs1.getString(1)+"("+id+")");
		    jf.setSize(800,600);
		    jf.setBackground(new Color(170,201,206));
		    JPanel p = new JPanel(new GridLayout(6, 7));
		    JPanel p1 = new JPanel(new GridLayout(1, 7));
		    JPanel p2 = new JPanel();
		    p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true) ,months[month], TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", Font. BOLD, 16)));
		    p2.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
		    Calendar cal = Calendar.getInstance();
		    cal.set(Calendar.MONTH, month);
		    cal.set(Calendar.DATE, 1);
		    int maxday=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		    int c=1;
		    String []days={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		    for(int i = 0; i < 7; i++){
		        JLabel l = new JLabel(days[i]);
		        l.setHorizontalAlignment(SwingConstants.CENTER);
		        l.setVerticalAlignment(SwingConstants.CENTER);
		        l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		        l.setFont(new Font("Tahoma", Font. BOLD, 14));
		        p1.add(l);
		    }
		    for(int i=0;i<6;i++)
		        for(int j=1;j<=7;j++) {
		            JLabel l = new JLabel("",SwingConstants.CENTER);
		            l.setHorizontalAlignment(SwingConstants.CENTER);
		            l.setVerticalAlignment(SwingConstants.CENTER);
		            l.setOpaque(true);
		            if(cal.get(Calendar.DAY_OF_WEEK)==j && c<=maxday){
		                Date date = cal.getTime();
		                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		                String date1 = format1.format(date);
		                l.setText(date1);
		                cal.add(Calendar.DATE, 1);
		                if(j!=1) {
			                String att=st.nextToken();
			                l.setText("<html>"+date1+"<br><h1 style=\"text-align:center;\">"+att+"</h1></html>");
			                if(att.equals("OD")) {l.setBackground(Color.ORANGE);}
			                else if(att.equals("L")) {l.setBackground(Color.CYAN);}
			                else if(att.equals("AB")) {l.setBackground(Color.red);}
			                else if(att.equals("P")) {l.setBackground(Color.green);}
		            	}
		                else {l.setBackground(Color.lightGray);}
		                l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		            	p.add(l);
		                c++;
		            }
		            else {
		                l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		                l.setBackground(new Color(227,237,231));
		                p.add(l);
		            }
		        }
		    JButton prevbtn=new JButton("<-");
	        JButton closebtn=new JButton("Close");
	        JButton nextbtn=new JButton("->");
	        p2.add(prevbtn);
	        p2.add(closebtn);
	        p2.add(nextbtn);
	        p1.setBackground(new Color(170,201,206));
	        p2.setBackground(new Color(170,201,206));
		    jf.add(p1,BorderLayout.NORTH);
		    jf.add(p,BorderLayout.CENTER);
		    jf.add(p2,BorderLayout.SOUTH);
		    jf.setVisible(true);
		    View_Attend ob=new View_Attend(month-1,id);
		    View_Attend ob2=new View_Attend(month+1,id);
		    prevbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(month!=0) {		
						ob.build();
						jf.dispose();
					}
				}
			});
		    nextbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(month!=11) {		
						ob2.build();
						jf.dispose();
					}
				}
			});
		    closebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jf.dispose();
				}
			});
		}catch(Exception er){ System.out.println(er.getMessage());}
	}
}
