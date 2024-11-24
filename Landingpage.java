import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
public class Landingpage{
	public static void main(String []args) {
		try {
		JFrame jf=new JFrame();
		jf.setSize(400,550);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel l=new JLabel("Attendence Management System");
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setVerticalAlignment(SwingConstants.CENTER);
		l.setFont(new Font("Tahoma", Font. BOLD, 14));
		l.setOpaque(true);
		l.setBackground(new Color(202,103,2));
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		JPanel p=new JPanel();
		BufferedImage myPicture = ImageIO.read(new File("src/landing_img.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		JButton login =new JButton("LOGIN");
		p.add(picLabel);
		jf.add(p,BorderLayout.CENTER);
		jf.add(l,BorderLayout.NORTH);
		jf.add(login,BorderLayout.SOUTH);
		jf.setVisible(true);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomizedLoginPage.main(null);
				jf.dispose();
			}
		});
		}catch(Exception e) {System.err.println(e);}
		
	}
}