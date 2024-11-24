import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
public class Update_Attend {
	int month;
	int id;
	public Update_Attend(int m,int id) {
		month=m;
		this.id=id;
	}
	public void build() {
		ArrayList<JComboBox> choices=new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
            Statement stmt=con.createStatement();
            System.out.println("Pass Point 1");
            ResultSet rs=stmt.executeQuery("select * from attendance");
            System.out.println("Pass Point 2");
            StringTokenizer st=null;
            while(rs.next()) {
            	if(rs.getInt(1)==id)
            		st=new StringTokenizer(rs.getString(month+2),"-");
            }
            System.out.println("Pass Point 3");
            JFrame jf = new JFrame();
            ResultSet rs1=stmt.executeQuery("select name from students where admission_no="+id);
            System.out.println(id);
            System.out.println("Pass Point 4");
			rs1.next();
			jf.setTitle("Attendance of student: "+rs1.getString(1)+"("+id+")");
			System.out.println("Pass Point 5");
			String []months={"January","February","March","April"};
		    jf.setSize(800,600);
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
			                String[] att = {"N/A","OD","P","L","AB"};
			                JComboBox<String> Att = new JComboBox<>(att);
			                String data=st.nextToken();
			                if(data.equals("OD")) {Att.setSelectedIndex(1);l.setBackground(Color.ORANGE);}
			                else if(data.equals("P")) {Att.setSelectedIndex(2);l.setBackground(Color.green);}
			                else if(data.equals("L")) {Att.setSelectedIndex(3);l.setBackground(Color.CYAN);}
			                else if(data.equals("AB")) {Att.setSelectedIndex(4);l.setBackground(Color.red);}
			                JPanel p3=new JPanel(new GridLayout(2,1));
			                p3.add(l);
			                p3.add(Att);
			                p3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
			                p.add(p3);
			                choices.add(Att);
			                Att.addActionListener(new ActionListener() {
			                    public void actionPerformed(ActionEvent e)
			                    {
			                        String ch=Att.getSelectedItem().toString();
			                        if(ch.equals("OD"))
			                            l.setBackground(Color.ORANGE);
			                        else if(ch.equals("P"))
			                            l.setBackground(Color.green);
			                        else if(ch.equals("L"))
			                            l.setBackground(Color.CYAN);
			                        else if(ch.equals("AB"))
			                            l.setBackground(Color.red);
			                        else
			                        	l.setBackground(Color.white);
			                    }
			                });
		            	}
		                else {
		                	l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		                	l.setBackground(Color.lightGray);
		                	p.add(l);
		                }
		                c++;
		            }
		            else {
		                l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		                l.setBackground(new Color(227,237,231));
		                p.add(l);
		            }
		        }
		    JButton updatebtn=new JButton("Update Attendance");
		    p2.add(updatebtn);
		    p1.setBackground(new Color(170,201,206));
	        p2.setBackground(new Color(170,201,206));
		    jf.add(p1,BorderLayout.NORTH);
		    jf.add(p,BorderLayout.CENTER);
		    jf.add(p2,BorderLayout.SOUTH);
		    jf.setVisible(true);
		    updatebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StringBuilder sb=new StringBuilder();
					for(JComboBox jcb : choices) {
						sb.append(jcb.getSelectedItem().toString()+"-");
					}
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","MinePixel25");
			            Statement stmt=con.createStatement();
			            stmt.executeUpdate("Update attendance set "+months[month]+"='"+sb.toString()+"' where admno="+id);
			            JOptionPane.showMessageDialog(null, "Attendance updated successfully!");
					}catch(Exception er){ System.out.println(er.getMessage());}
				}
			});
		}catch(Exception er){ System.out.println(er.getMessage());}
	}
}
