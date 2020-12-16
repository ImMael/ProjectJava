package jav.project;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(){
        super("Bibliotheque");
        setSize(1280,720);

        JPanel monPanel = new JPanel();
        this.setContentPane(monPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        monPanel.setBackground(Color.darkGray);

        FlowLayout myFlowLayout = new FlowLayout(FlowLayout.CENTER);
        monPanel.setLayout(myFlowLayout);


    }
}
