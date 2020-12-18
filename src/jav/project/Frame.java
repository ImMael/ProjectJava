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
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import jav.project.Book;

public class Frame extends JFrame {
    String[] dictionnaire = new String[6];

    public Frame() {
        super("Bibliotheque");
        setSize(1280, 720);     // Set the default size of the window

        LocalDateTime now = LocalDateTime.now();
        // System.out.println(now.getYear());

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
        fileFichier.setText("Fichier");
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
        JMenuItem stats = new JMenuItem();
        ouvrir.setText("Ouvrir ...");
        nouveau.setText("Nouveau");
        quitter.setText("Quitter");
        raz.setText("RAZ");
        stats.setText("Stats");
        fileFichier.add(ouvrir);
        fileFichier.add(nouveau);
        fileFichier.add(stats);
        fileFichier.add(quitter);
        fileEdit.add(raz);

        //Setting up the Layout
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
        Border invBorder = new LineBorder(Color.darkGray, 0, false);

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
        JButton addButton = new JButton();          // Create a button
        JButton deleteButton = new JButton();       // Create a button
        try {
            Image img = ImageIO.read(getClass().getResource("assets/check_1.png"));     // Get a image and put it in img
            buttonValidate.setIcon(new ImageIcon(img));     // Set a image on button
            Image imgCancel = ImageIO.read(getClass().getResource("assets/close_1.png"));
            buttonCancel.setIcon(new ImageIcon(imgCancel));
            Image imgDelete = ImageIO.read(getClass().getResource("assets/minus.png"));     // Get a image and put it in img
            deleteButton.setIcon(new ImageIcon(imgDelete));     // Set a image on button
            Image imgAdd = ImageIO.read(getClass().getResource("assets/add.png"));     // Get a image and put it in img
            addButton.setIcon(new ImageIcon(imgAdd));     // Set a image on button
        } catch (IOException ignored) {
        }
        // Borders add, Size Add
        buttonValidate.setPreferredSize(new Dimension(100, 30));    // Set the size of the button
        buttonCancel.setPreferredSize(new Dimension(100, 30));      // Set the size of the button
        buttonValidate.setBorder(roundedBorder);    // Round the border
        buttonValidate.setBackground(Color.darkGray);   // Set the opacity
        buttonCancel.setBorder(roundedBorder);
        buttonCancel.setBackground(Color.darkGray);
        addButton.setPreferredSize(new Dimension(30, 30));    // Set the size of the button
        deleteButton.setPreferredSize(new Dimension(30, 30));      // Set the size of the button
        addButton.setBorder(invBorder);    // Round the border
        addButton.setBackground(Color.darkGray);   // Set the opacity
        deleteButton.setBorder(invBorder);
        deleteButton.setBackground(Color.darkGray);

        // Setup of the Library
        Object[][] donnees = {
                {"Titre", "Auteur", "Date de Sortie", "Colonne", "Rangée", "Résumé"},
                {"Harry Potter", "J.K Rowling", "2009", "5", "2", "C'est l'histoire d'Harry Potter..."},
                {"Eragon", "C Poolini", "2000", "2", "2", "Eragon mène une vie simple.."},
                {"Le Vieil Homme et la Mer", "E Hemingway", "1952", "4", "3", "Un bouquin avec un gros poisson.."},
                {"Les Raisins de la Colère", "J Steinbeck", "1939", "2", "5", "La grande dépression c'était la Hess.."},
                {"Moby Dick", "H. Melville", "1851", "1", "6", "Un autre bouquin avec un gros poissson.."},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},
                {"", "", "", "", "", ""},

        };
        // System.out.println(donnees[x][0]);

        Book hp = new Book("Harry Potter", "J.K Rowling", 2009,5,2,"C'est l'histoire d'Harry Potter...");
        Book eragon = new Book("Eragon", "C Poolini",2000,2,2,"Eragon mène une vie simple..");

        String[] entetes = {"Titre", "Auteur", "Date de Sortie", "Colonne", "Rangee", "Pitch"};

        // Table where the info of books are display and save
        JTable infoTable = new JTable(donnees, entetes) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                comp.setBackground(row % 2 == 0 ? Color.DARK_GRAY : Color.GRAY);
                return comp;
            }
        };



        //create JTable
        infoTable.setPreferredSize(new Dimension(800, 400));    // Size of JTable
        infoTable.setBorder(roundedBorder);    // To have rounded border (style)
        infoTable.setOpaque(false);     // To set opacity of JTable
        infoTable.setForeground(Color.WHITE);       // Color of text
        infoTable.setSelectionForeground(Color.white); // Color while selected

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
        grid.insets = new Insets(20, 0, 0, 100);
        monPanel.add(buttonValidate, grid);

        // Button cancel the form
        grid.gridx = 3;
        grid.gridy = 6;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.insets = new Insets(20, 0, 0, 0);
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

        // Button add of the Table
        grid.gridx = 0;
        grid.gridy = 9;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.insets = new Insets(-70, -30, 0, 0);
        monPanel.add(addButton, grid);

        // Button delete of the table
        grid.gridx = 0;
        grid.gridy = 9;
        grid.gridheight = 1;
        grid.gridwidth = 1;
        grid.insets = new Insets(-70, 30, 0, 0);
        monPanel.add(deleteButton, grid);


        // Mise en place de la recherche de fichier .txt
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only txt files", "txt");
        fileChooser.setFileFilter(filter);


        // Effacer le label pour pouvoir écrire
        title.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (title.getText().equals("Titre")) {
                    title.setText("");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (title.getText().equals("")) {
                    title.setText("Titre");
                }
            }
        });
        author.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (author.getText().equals("Auteur")) {
                    author.setText("");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (author.getText().equals("")) {
                    author.setText("Auteur");
                }
            }
        });
        release.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (release.getText().equals("Date de sortie")) {
                    release.setText("");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (release.getText().equals("")) {
                    release.setText("Date de sortie");
                }
            }
        });
        column.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (column.getText().equals("Colonne")) {
                    column.setText("");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (column.getText().equals("")) {
                    column.setText("Colonne");
                }
            }
        });
        row.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (row.getText().equals("Rangée")) {
                    row.setText("");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (row.getText().equals("")) {
                    row.setText("Rangée");
                }
            }
        });
        pitch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (pitch.getText().equals("Résumé")) {
                    pitch.setText("");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (pitch.getText().equals("")) {
                    pitch.setText("Résumé");
                }
            }
        });

        // Button to reset fields
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

        // Button to add Element to the table
        buttonValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if((Integer.parseInt(release.getText()) > now.getYear()) || (Integer.parseInt(row.getText()) > 7) || (Integer.parseInt(column.getText()) > 5)){
                        JOptionPane.showMessageDialog(monPanel,"Année incorrecte, ou mauvaise position dans la bibliothèque");
                    } else {
                        for(int x = 1; x < donnees.length; x++){
                            int selRow = infoTable.getSelectedRow();
                            donnees[selRow][0] = title.getText();
                            donnees[selRow][1] = author.getText();
                            donnees[selRow][2] = release.getText();
                            donnees[selRow][3] = column.getText();
                            donnees[selRow][4] = row.getText();
                            donnees[selRow][5] = pitch.getText();
                            break;
                        }
                    }
                } catch (NumberFormatException er){
                    String errorMessage = er.toString();
                    String[] result = errorMessage.split(":");
                    // System.out.println(errorMessage); Print dans la console de l'erreur (pour test)
                    JOptionPane.showMessageDialog(monPanel,"Erreur :" + result[1] + ":" + result[2]);
                }
            }
        });

        // Pop up dev
        filePropos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String Message = "Developped by Walid.H | Allan.P | Baptiste.C | Maël.L\n Version: 1.0";
                try {
                    JOptionPane.showMessageDialog(monPanel, Message);
                    Desktop.getDesktop().browse(URI.create("https://github.com/ImMael"));
                    Desktop.getDesktop().browse(URI.create("https://github.com/Allan-Pe"));
                    Desktop.getDesktop().browse(URI.create("https://github.com/BaptisteChoquet"));
                    Desktop.getDesktop().browse(URI.create("https://github.com/walidhaddoury"));

                } catch (IOException ioException) {
                    ioException.printStackTrace();
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

        // Sous menu pour ouvrir un fichier .txt
        ouvrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Val = fileChooser.showOpenDialog(monPanel);
                if (Val == JFileChooser.APPROVE_OPTION) {
                    // System.out.println("You choose to open this: "+fileChooser.getSelectedFile());
                    File file = new File(String.valueOf(fileChooser.getSelectedFile()));
                    Desktop desktop = Desktop.getDesktop();
                    if (file.exists()) {
                        try {
                            desktop.open(file);
                        } catch (IOException exceptError) {
                            JOptionPane.showMessageDialog(monPanel, "Erreur, le fichier est incorrect.");
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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = infoTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(monPanel, "Erreur, Vous n'avez rien sélectionné.");
                } else if (selectedRow == 0){
                    infoTable.clearSelection();
                } else {
                    donnees[selectedRow][0] = "";
                    donnees[selectedRow][1] = "";
                    donnees[selectedRow][2] = "";
                    donnees[selectedRow][3] = "";
                    donnees[selectedRow][4] = "";
                    donnees[selectedRow][5] = "";


                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = infoTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(monPanel, "Erreur, Vous n'avez rien sélectionné.");
                } else if (selectedRow == 0){
                    infoTable.clearSelection();
                } else {
                    title.setText((String) donnees[selectedRow][0]);
                    author.setText((String) donnees[selectedRow][1]);
                    release.setText((String) donnees[selectedRow][2]);
                    column.setText((String) donnees[selectedRow][3]);
                    row.setText((String) donnees[selectedRow][4]);
                    pitch.setText((String) donnees[selectedRow][5]);
                }
            }
        });

        // Delete all files in the library
        raz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 1; i < donnees.length;i++){
                    donnees[i][0]= "";
                    donnees[i][1]= "";
                    donnees[i][2]= "";
                    donnees[i][3]= "";
                    donnees[i][4]= "";
                    donnees[i][5]= "";
                }
            }
        });

        // Display if a book were released after 2008
        nouveau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList recentBooks = new ArrayList<String>();
                try{
                    for(int k = 1; k < donnees.length;k++){
                        int isRecent = Integer.parseInt((String) donnees[k][2]);
                        // System.out.println(isRecent);
                        if(isRecent > 2008){
                            recentBooks.add("\n"+(String) donnees[k][0] + " de " + (String) donnees[k][1] + " ("+donnees[k][2]+") " + "\n");
                        }
                    }
                } catch (NumberFormatException ignored){

                }
                ArrayList news = recentBooks;
                JOptionPane.showMessageDialog(monPanel, "Les livres datant d'après 2008 sont : " + news);
            }
        });

        // Display if a book's name have an "a" as second letter
        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList bookWA = new ArrayList<String>();
                try{
                    for(int k = 1; k < donnees.length - 1;k++){
                        String containsA = (String) donnees[k][0];
                        // System.out.println(containsA);
                        char[] aWordArray = containsA.toCharArray();
                        if(aWordArray[1] == 'a') {
                            bookWA.add((String) donnees[k][0]);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored){

                }
                ArrayList booksA = bookWA ;
                JOptionPane.showMessageDialog(monPanel, "Les livres possédant un a en deuxième position : " + booksA);
            }
        });
    }
}
