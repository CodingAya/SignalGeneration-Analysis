package App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.EventQueue;

public class Fenetre extends JFrame {

    public JFrame frame;
    public TraceSignal panel;
    private JTextField Amplitude;
    private JTextField Frequence;
    private JTextField Phase;
    private double A;
    private double P;
    private double F;

    /**
     * Create the application.
     */
    public Fenetre() {

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(41, 48, 57));
        frame.setBounds(100, 100, 796, 472);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //title
        JLabel lblNewLabel_3 = new JLabel(" SIGNAL GENERATOR");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setBounds(400, 30, 223, 16);
        frame.add(lblNewLabel_3);

        //Amplitude
        JLabel lblNewLabel = new JLabel("AMPLITUDE");
        lblNewLabel.setBounds(22, 100, 94, 16);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(lblNewLabel);

        Amplitude = new JTextField();
        Amplitude.setBounds(141, 100, 86, 20);
        Amplitude.setForeground(new Color(0, 0, 0));
        Amplitude.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(Amplitude);
        Amplitude.setColumns(10);

        //fréquence
        JLabel lblNewLabel_1 = new JLabel(" FREQUENCY");
        lblNewLabel_1.setBounds(10, 171, 121, 14);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(lblNewLabel_1);

        Frequence = new JTextField();
        Frequence.setBounds(141, 171, 86, 20);
        Frequence.setHorizontalAlignment(SwingConstants.CENTER);
        Frequence.setForeground(new Color(0, 0, 0));
        frame.add(Frequence);
        Frequence.setColumns(10);


        JLabel lblNewLabel_2 = new JLabel(" PHASE");
        lblNewLabel_2.setBounds(22, 242, 94, 14);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(lblNewLabel_2);

        Phase = new JTextField();
        Phase.setBounds(141, 242, 86, 20);
        Phase.setHorizontalAlignment(SwingConstants.CENTER);
        Phase.setForeground(new Color(0, 0, 0));
        frame.add(Phase);
        Phase.setColumns(10);


//bouton draw
        JButton DRAW = new JButton(" Generate the graphic");
        DRAW.setBounds(410, 389, 200, 20);
        DRAW.setFont(new Font("Dialog", Font.BOLD, 13));
        DRAW.setBackground(Color.RED);
        DRAW.setForeground(new Color(255, 255, 255));
        frame.add(DRAW);

        JPanel panel = new TraceSignal();
        panel.setBounds(260, 63, 494, 296);
        frame.getContentPane().add(panel);


        DRAW.addActionListener(new ActionListener() {
            //capturer un événement sur le JTextField
            public void actionPerformed(ActionEvent e) {
                //Demarer notre dessin
                ((TraceSignal) panel).setD(1);
                ((TraceSignal) panel).setN(0);
                ((TraceSignal) panel).setE(0);
                ((TraceSignal) panel).setF(0);
                ((TraceSignal) panel).setB(0);
                ((TraceSignal) panel).setQ(0);
                ((TraceSignal) panel).setA(Double.parseDouble(Amplitude.getText()));
                ((TraceSignal) panel).setPhi(Double.parseDouble(Phase.getText()));
                ((TraceSignal) panel).setF0(Double.parseDouble(Frequence.getText()));
                ((TraceSignal) panel).setCycles(4);


            }
        });


//Jpanel Cadre

        JButton button = new JButton(" Go back ");
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Dialog", Font.BOLD, 13));
        button.setBackground(Color.ORANGE);
        button.setBounds(72, 389, 116, 20);
        frame.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });


        JButton Num = new JButton("Numerisation");
        Num.setBounds(16, 275, 120, 20);
        Num.setFont(new Font("Dialog", Font.BOLD, 13));
        Num.setBackground(Color.CYAN);
        Num.setForeground(Color.BLACK);
        frame.getContentPane().add(Num);

        JButton FFT = new JButton("  FFT");
        FFT.setBounds(141, 275, 97, 20);
        FFT.setFont(new Font("Dialog", Font.BOLD, 13));
        FFT.setBackground(Color.CYAN);
        FFT.setForeground(Color.BLACK);
        frame.getContentPane().add(FFT);

        JButton Ech = new JButton("Sampling");
        Ech.setBounds(60, 305, 150, 20);
        Ech.setFont(new Font("Dialog", Font.BOLD, 13));
        Ech.setBackground(Color.CYAN);
        Ech.setForeground(Color.BLACK);
        frame.getContentPane().add(Ech);

        JButton Quanti = new JButton("Quantification");
        Quanti.setBounds(60, 335, 150, 20);
        Quanti.setFont(new Font("Dialog", Font.BOLD, 13));
        Quanti.setBackground(Color.CYAN);
        Quanti.setForeground(Color.BLACK);
        frame.getContentPane().add(Quanti);

        JButton Bruit = new JButton("Add noise");
        Bruit.setBounds(60, 365, 150, 20);
        Bruit.setFont(new Font("Dialog", Font.BOLD, 13));
        Bruit.setBackground(Color.CYAN);
        Bruit.setForeground(Color.BLACK);
        frame.getContentPane().add(Bruit);

        Ech.addActionListener(new ActionListener() {
            //capturer un événement sur le JTextField
            public void actionPerformed(ActionEvent e) {
                ((TraceSignal) panel).setD(1);
                ((TraceSignal) panel).setN(0);
                ((TraceSignal) panel).setE(1);
                ((TraceSignal) panel).setF(0);
                ((TraceSignal) panel).setB(0);
                ((TraceSignal) panel).setQ(0);
                ((TraceSignal) panel).setA(Double.parseDouble(Amplitude.getText()));
                ((TraceSignal) panel).setPhi(Double.parseDouble(Phase.getText()));
                ((TraceSignal) panel).setF0(Double.parseDouble(Frequence.getText()));
                ((TraceSignal) panel).setCycles(4);

            }
        });

        Quanti.addActionListener(new ActionListener() {
            //capturer un événement sur le JTextField
            public void actionPerformed(ActionEvent e) {
                ((TraceSignal) panel).setD(0);
                ((TraceSignal) panel).setN(0);
                ((TraceSignal) panel).setE(0);
                ((TraceSignal) panel).setF(0);
                ((TraceSignal) panel).setB(0);
                ((TraceSignal) panel).setQ(1);
                ((TraceSignal) panel).setA(Double.parseDouble(Amplitude.getText()));
                ((TraceSignal) panel).setPhi(Double.parseDouble(Phase.getText()));
                ((TraceSignal) panel).setF0(Double.parseDouble(Frequence.getText()));
                ((TraceSignal) panel).setCycles(4);

            }
        });

        Num.addActionListener(new ActionListener() {
            //capturer un événement sur le JTextField
            public void actionPerformed(ActionEvent e) {
                ((TraceSignal) panel).setD(0);
                ((TraceSignal) panel).setN(1);
                ((TraceSignal) panel).setE(0);
                ((TraceSignal) panel).setF(0);
                ((TraceSignal) panel).setQ(0);
                ((TraceSignal) panel).setB(0);
                ((TraceSignal) panel).setA(Double.parseDouble(Amplitude.getText()));
                ((TraceSignal) panel).setPhi(Double.parseDouble(Phase.getText()));
                ((TraceSignal) panel).setF0(Double.parseDouble(Frequence.getText()));
                ((TraceSignal) panel).setCycles(4);

            }
        });

        FFT.addActionListener(new ActionListener() {
            //capturer un événement sur le JTextField
            public void actionPerformed(ActionEvent e) {
                ((TraceSignal) panel).setF(1);
                ((TraceSignal) panel).setD(0);
                ((TraceSignal) panel).setN(0);
                ((TraceSignal) panel).setE(0);
                ((TraceSignal) panel).setB(0);
                ((TraceSignal) panel).setQ(0);
                ((TraceSignal) panel).setA(Double.parseDouble(Amplitude.getText()));
                ((TraceSignal) panel).setPhi(Double.parseDouble(Phase.getText()));
                ((TraceSignal) panel).setF0(Double.parseDouble(Frequence.getText()));
                ((TraceSignal) panel).setCycles(4);

            }
        });

        Bruit.addActionListener(new ActionListener() {
            //capturer un événement sur le JTextField
            public void actionPerformed(ActionEvent e) {
                ((TraceSignal) panel).setD(0);
                ((TraceSignal) panel).setN(0);
                ((TraceSignal) panel).setE(0);
                ((TraceSignal) panel).setF(0);
                ((TraceSignal) panel).setB(1);
                ((TraceSignal) panel).setQ(0);
                ((TraceSignal) panel).setA(Double.parseDouble(Amplitude.getText()));
                ((TraceSignal) panel).setPhi(Double.parseDouble(Phase.getText()));
                ((TraceSignal) panel).setF0(Double.parseDouble(Frequence.getText()));
                ((TraceSignal) panel).setCycles(4);
            }
        });

    }

}
