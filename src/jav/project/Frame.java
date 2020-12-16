package jav.project;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Frame extends JFrame {
    public Frame() {
        super("Bibliotheque");
        setSize(1280, 720);

        JPanel monPanel = new JPanel();
        this.setContentPane(monPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        monPanel.setBackground(Color.darkGray);

        Border roundedBorder = new LineBorder(Color.WHITE, 2, true);

        FlowLayout myFlowLayout = new FlowLayout(FlowLayout.CENTER);
        monPanel.setLayout(myFlowLayout);

        GridBagLayout gLayout = new GridBagLayout();
        monPanel.setLayout(gLayout);

        GridBagConstraints grid = new GridBagConstraints();

        JTextField title = new JTextField();
        title.setPreferredSize(new Dimension(300, 50));
        JTextField author = new JTextField();
        author.setPreferredSize(new Dimension(300, 50));
        JTextField release = new JTextField();
        release.setPreferredSize(new Dimension(300, 50));
        JTextField column = new JTextField();
        column.setPreferredSize(new Dimension(300, 50));
        JTextField row = new JTextField();
        row.setPreferredSize(new Dimension(300, 50));
        JTextArea pitch = new JTextArea();
        pitch.setPreferredSize(new Dimension(300, 150));

        title.setBorder(roundedBorder);
        title.setOpaque(false);
        title.setForeground(Color.WHITE);
        author.setBorder(roundedBorder);
        author.setOpaque(false);
        author.setForeground(Color.WHITE);
        release.setBorder(roundedBorder);
        release.setOpaque(false);
        release.setForeground(Color.WHITE);
        column.setBorder(roundedBorder);
        column.setOpaque(false);
        column.setForeground(Color.WHITE);
        row.setBorder(roundedBorder);
        row.setOpaque(false);
        row.setForeground(Color.WHITE);
        pitch.setBorder(roundedBorder);
        pitch.setOpaque(false);
        pitch.setForeground(Color.WHITE);


        JButton buttonValidate = new JButton();
        JButton buttonCancel = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("assets/check_1.png"));
            buttonValidate.setIcon(new ImageIcon(img));
            Image imgCancel = ImageIO.read(getClass().getResource("assets/close_1.png"));
            buttonCancel.setIcon(new ImageIcon(imgCancel));
        } catch (IOException ex) {
        }

        buttonValidate.setPreferredSize(new Dimension(150, 30));
        buttonCancel.setPreferredSize(new Dimension(150, 30));


        buttonValidate.setBorder(roundedBorder);
        buttonValidate.setOpaque(false);
        buttonCancel.setBorder(roundedBorder);
        buttonCancel.setOpaque(false);


        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(title, grid);

        grid.gridx = 0;
        grid.gridy = 1;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(author, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(release, grid);

        grid.gridx = 0;
        grid.gridy = 3;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(column, grid);

        grid.gridx = 0;
        grid.gridy = 4;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(row, grid);

        grid.gridx = 0;
        grid.gridy = 5;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(pitch, grid);

        grid.gridx = 0;
        grid.gridy = 6;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        monPanel.add(buttonValidate, grid);


        grid.gridx = 1;
        grid.gridy = 6;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        monPanel.add(buttonCancel, grid);


    }
}
