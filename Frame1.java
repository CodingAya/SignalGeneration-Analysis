package App;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class Frame1 {

    public JFrame frame;


    /**
     * Create the application.
     */
    public Frame1() {

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(41, 48, 57));
        frame.setBackground(Color.WHITE);
        frame.setBounds(100, 100, 900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ayael\\Desktop\\Aya'sGif.gif"));
        lblNewLabel.setBounds(200, 10, 500, 500);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("Click here to generate");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton.setBackground(Color.RED);
        btnNewButton.setBounds(350, 555, 200, 29);
        frame.getContentPane().add(btnNewButton);

        Fenetre frame2 =new Fenetre();


        btnNewButton.addActionListener(new ActionListener() {
            //capturer un événement sur le JTextField
            public void actionPerformed(ActionEvent e) {
                //basculer vers frame2
                frame2.frame.setVisible(true);


            }});

    }


}
