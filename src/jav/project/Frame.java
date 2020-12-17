package jav.project;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystem;
import javax.swing.ImageIcon;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Frame extends JFrame{
    public Frame() {
        super("Bibliotheque");
        setSize(1280, 720);     // Set the default size of the window

        JPanel monPanel = new JPanel();
        this.setContentPane(monPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        monPanel.setBackground(Color.darkGray);

        FlowLayout myFlowLayout = new FlowLayout(FlowLayout.CENTER);
        monPanel.setLayout(myFlowLayout);

        // Menu
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileFichier = new JMenu();
        fileFichier.setText("Fichiers");
        JMenu fileEdit = new JMenu();
        fileEdit.setText("Editer");
        JMenu filePropos = new JMenu();
        filePropos.setText("A propos");
        menuBar.add(fileFichier);
        menuBar.add(fileEdit);
        menuBar.add(filePropos);


        // Sous menu
        JMenuItem ouvrir = new JMenuItem();
        JMenuItem nouveau = new JMenuItem();
        JMenuItem quitter = new JMenuItem();
        JMenuItem raz = new JMenuItem();
        ouvrir.setText("Ouvrir ...");
        nouveau.setText("Nouveau");
        quitter.setText("Quitter");
        raz.setText("RAZ");
        fileFichier.add(ouvrir);
        fileFichier.add(nouveau);
        fileFichier.add(quitter);
        fileEdit.add(raz);


        GridBagLayout gLayout = new GridBagLayout();
        monPanel.setLayout(gLayout);

        // Create the grid to place on
        GridBagConstraints grid = new GridBagConstraints();

        // Create all Text field and Text Area
        JTextField title = new JTextField();    // Create a text field
        title.setText("Titre");     // Set a title on the text field
        title.setPreferredSize(new Dimension(300, 50));     // Set the size of text field
        JTextField author = new JTextField();
        author.setText("Auteur");
        author.setPreferredSize(new Dimension(300, 50));
        JTextField release = new JTextField();
        release.setText("Date de sortie");
        release.setPreferredSize(new Dimension(300, 50));
        JTextField column = new JTextField();
        column.setText("Colonne");
        column.setPreferredSize(new Dimension(300, 50));
        JTextField row = new JTextField();
        row.setText("Rangée");
        row.setPreferredSize(new Dimension(300, 50));
        JTextArea pitch = new JTextArea();
        pitch.setText("Résumé");
        pitch.setPreferredSize(new Dimension(300, 150));

        // Style of border
        Border roundedBorder = new LineBorder(Color.WHITE, 2, true);

        // Set the Style of all Text field
        title.setBorder(roundedBorder);     // Round the border
        title.setOpaque(false);     // Set the opacity
        title.setForeground(Color.WHITE);       // Change the color of the text
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
        // Set the style of Text Area
        pitch.setBorder(roundedBorder);
        pitch.setOpaque(false);
        pitch.setForeground(Color.WHITE);

        // Creation of both button Validate and Cancel
        JButton buttonValidate = new JButton();     // Create a button
        JButton buttonCancel = new JButton();       // Create a button
        try {
            Image img = ImageIO.read(getClass().getResource("assets/check_1.png"));     // Get a image and put it in img
            buttonValidate.setIcon(new ImageIcon(img));     // Set a image on button
            Image imgCancel = ImageIO.read(getClass().getResource("assets/close_1.png"));
            buttonCancel.setIcon(new ImageIcon(imgCancel));
        } catch (IOException ignored) {
        }

        buttonValidate.setPreferredSize(new Dimension(100, 30));    // Set the size of the button
        buttonCancel.setPreferredSize(new Dimension(100, 30));      // Set the size of the button
        buttonValidate.setBorder(roundedBorder);    // Round the border
        buttonValidate.setBackground(Color.darkGray);   // Set the opacity
        buttonCancel.setBorder(roundedBorder);
        buttonCancel.setBackground(Color.darkGray);

        // Table where the info of books are display and save
        JTable infoTable = new JTable();    //create JTable
        infoTable.setPreferredSize(new Dimension(800, 400));    // Size of JTable
        infoTable.setBorder(roundedBorder);    // To have rounded border (style)
        infoTable.setOpaque(false);     // To set opacity of JTable
        infoTable.setForeground(Color.WHITE);       // Color of text

        // Create blank column to create space
        JTable blankColumn = new JTable();
        blankColumn.setPreferredSize(new Dimension(80, 400));
        blankColumn.setOpaque(false);


        // Placement of the elements and added to monPanel
        // Text field Title
        grid.gridx = 2;
        grid.gridy = 0;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(title, grid);

        // Text field Author
        grid.gridx = 2;
        grid.gridy = 1;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.insets = new Insets(10, 0, 0, 0);
        monPanel.add(author, grid);

        // Text field Release date
        grid.gridx = 2;
        grid.gridy = 2;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(release, grid);

        // Text field Column
        grid.gridx = 2;
        grid.gridy = 3;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(column, grid);

        // Text field Row
        grid.gridx = 2;
        grid.gridy = 4;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(row, grid);

        // Text field Pitch of the book
        grid.gridx = 2;
        grid.gridy = 5;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        monPanel.add(pitch, grid);

        // Button validate the form
        grid.gridx = 2;
        grid.gridy = 6;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.insets = new Insets(10, 0, 0, 100);
        monPanel.add(buttonValidate, grid);

        // Button cancel the form
        grid.gridx = 3;
        grid.gridy = 6;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.insets = new Insets(10, 0, 0, 0);
        monPanel.add(buttonCancel, grid);

        grid.insets = new Insets(0, 0, 0, 0);
        // Info Table
        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridheight = 6;
        grid.gridwidth = 1;
        monPanel.add(infoTable, grid);

        // Blank column to create space between InfoTable and the Form
        grid.gridx = 1;
        grid.gridy = 0;
        grid.gridheight = 6;
        grid.gridwidth = 1;
        monPanel.add(blankColumn, grid);


        // Mise en place de la recherche de fichier .txt
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only txt files","txt");
        fileChooser.setFileFilter(filter);


        // Efface le label pour pouvoir ecrire
        title.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(title.getText().equals("Titre")){
                    title.setText(" ");
                } else if(title.getText().equals("")){
                    title.setText("Titre");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        author.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(author.getText().equals("Auteur")){
                    author.setText(" ");
                } else if(title.getText().equals("")){
                    author.setText("Auteur");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        release.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(release.getText().equals("Date de sortie")){
                    release.setText(" ");
                } else if(title.getText().equals("")){
                    release.setText("Date de sortie");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        column.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(column.getText().equals("Colonne")){
                    column.setText(" ");
                } else if(column.getText().equals("")){
                    column.setText("Colonne");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        row.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(row.getText().equals("Rangée")){
                    row.setText(" ");
                } else if(row.getText().equals("")){
                    row.setText("Rangée");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        pitch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(pitch.getText().equals("Résumé")){
                    pitch.setText(" ");
                } else if(pitch.getText().equals("")){
                    pitch.setText("Resumé");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title.setText("Titre");
                author.setText("Auteur");
                release.setText("Date de Sortie");
                column.setText("Colonne");
                row.setText("Rangée");
                pitch.setText("Résumé");
            }
        });
        filePropos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String Message = "Dev: Walid, Allan, Baptiste et Maël\n Version: 1.0";
                JOptionPane.showMessageDialog(monPanel, Message);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });


        // Sous menu pour ouvrir un fichier .txt
        ouvrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Val = fileChooser.showOpenDialog(monPanel);
                if(Val == JFileChooser.APPROVE_OPTION){
                    // System.out.println("You choose to open this: "+fileChooser.getSelectedFile());
                    File file = new File(String.valueOf(fileChooser.getSelectedFile()));
                    System.out.println(file);
                    Desktop desktop = Desktop.getDesktop();
                    if(file.exists()){
                        try {
                            desktop.open(file);
                        } catch (IOException exceptError) {
                            JOptionPane.showMessageDialog(monPanel,"Erreur, le fichier est incorrect.");
                        }
                    }

                }
            }
        });
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
