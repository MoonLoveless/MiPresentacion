package myPresentation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIPresentation extends JFrame {
    public GUIPresentation(){
        initGUI();
        this.setFocusable(true);
        this.setTitle("My presentation");
        this.setSize(600, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.addKeyListener(listener);
        this.requestFocusInWindow();
    }

    private void initGUI() {
        title = new Title("Alexandra's presentation", Color.gray);
        myPhoto = new JButton("my photo");
        myHobby = new JButton("this ismy hobby");
        myExpectations = new JButton("this are my expectations");
        containerButtons = new JPanel();
        containerIMages = new JPanel();
        listener = new Listener();
        imageLabel = new JLabel();
        expectativeText = new JTextArea(12, 12);

        containerIMages.setBorder(BorderFactory.createTitledBorder(null, "about me",
                TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION,
                new Font(Font.MONOSPACED, Font.PLAIN, 20), Color.BLACK));

        containerIMages.add(imageLabel);
        containerButtons.add(myPhoto);
        containerButtons.add(myHobby);
        containerButtons.add(myExpectations);
        myPhoto.addActionListener(listener);
        myHobby.addActionListener(listener);
        myExpectations.addActionListener(listener);

        this.add(title, BorderLayout.NORTH);
        this.add(containerButtons, BorderLayout.SOUTH);
        this.add(containerIMages, BorderLayout.CENTER);
    }

    private  JButton myPhoto, myHobby, myExpectations;
    private Title title;
    private JPanel containerButtons, containerIMages;

    private Listener listener;

    private JLabel imageLabel;

    private JTextArea expectativeText;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                GUIPresentation myGui = new GUIPresentation();
            }
        });
    }

    private class Listener implements ActionListener {
        private ImageIcon image;
        private long lastClickTime = 0;

        public Listener() {
            //Creamos un KeyStroke asociado a la tecla "m"
            KeyStroke key = KeyStroke.getKeyStroke('m');

            //Creamos un Action asociado al KeyStroke que despliega el mensaje
            AbstractAction action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imageLabel.setIcon(null);
                    containerIMages.remove(expectativeText);
                    expectativeText.setText(" I expect to learn about java with events to improve \n" + "my programming skills");
                    expectativeText.setBackground(null);
                    expectativeText.setForeground(Color.BLACK);
                    containerIMages.add(expectativeText);
                }
            };

            //Obtenemos el InputMap y el ActionMap de containerIMages
            InputMap im = containerIMages.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = containerIMages.getActionMap();

            //Asociamos el KeyStroke al Action en el InputMap y el ActionMap
            im.put(key, "myAction");
            am.put("myAction", action);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            imageLabel.setIcon(null);
            containerIMages.remove(expectativeText);
            if (e.getSource() == myPhoto) {
                this.image = new ImageIcon(getClass().getResource("/recourses/me.jpg"));
                imageLabel.setIcon(image);
            } else if (e.getSource() == myHobby) {
                long time = System.currentTimeMillis();
                if (time - lastClickTime < 500) { // si se ha hecho doble clic
                    this.image = new ImageIcon(getClass().getResource("/recourses/HOBBY.jpg"));
                    imageLabel.setIcon(image);
                }
                lastClickTime = time;
            }
            revalidate();
            repaint();
        }
    }
}