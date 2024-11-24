import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class C_Admin extends JFrame implements ActionListener {
    private JTextField studentNameField, studentAgeField, teacherNameField, teacherSubjectField;
    private JTextArea displayArea;
    private JButton backbtn, addStudentButton, addTeacherButton,deleteTeacherButton,deleteStudentButton;
    public C_Admin() {
        setTitle("Admin Page");
        setSize(500, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        backbtn=new JButton("<-Back");
        backbtn.setBounds(5, 5, 100, 30);
        backbtn.addActionListener(this);
        inputPanel.setLayout(null);
        inputPanel.setBackground(new Color(116,198,157));
        addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(60, 60, 150, 30);
        addStudentButton.addActionListener(this);
        inputPanel.add(addStudentButton);
        addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.setBounds(250, 60, 150, 30);
        addTeacherButton.addActionListener(this);
        inputPanel.add(addTeacherButton);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        deleteTeacherButton = new JButton("Delete Teacher");
        deleteTeacherButton.setBounds(250, 130, 150, 30);
        deleteTeacherButton.addActionListener(this);
        inputPanel.add(deleteTeacherButton);
        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.setBounds(60, 130, 150, 30);
        deleteStudentButton.addActionListener(this);
        inputPanel.add(deleteStudentButton);
        add(backbtn);
        add(inputPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudentButton) {
            C_Student_Reg s = new C_Student_Reg();
            s.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == addTeacherButton) {
            C_Teacher_Reg t = new C_Teacher_Reg();
            t.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == deleteStudentButton) {
            deleteStudent ds = new deleteStudent();
            ds.setVisible(true);
        } else if (e.getSource() == deleteTeacherButton) {
            deleteTeacher dt = new deleteTeacher();
            dt.setVisible(true);
        } else if (e.getSource() == backbtn) {
        	CustomizedLoginPage.main(null);
            dispose();
        }
    }
    public static void main(String[] args) {
        JFrame f = new C_Admin();
        f.setVisible(true);
    }
}