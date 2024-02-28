package view;

import controller.InfoBreedsController;
import model.BreedModel;
import model.SignUpModel;
import repository.Breed;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class InfoBreedsView extends JFrame {
    private JTable breedsTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryBox = new JComboBox<>();
    private JLabel category = new JLabel("Category");
    private TableRowSorter<DefaultTableModel> sorter;

    public InfoBreedsView(InfoBreedsController controller, SignUpModel model) {
        JFrame frame = new JFrame("Info about breeds");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);

        Color colorBackground = new Color(192, 214, 223);
        frame.setBackground(colorBackground);

        Font buttonFont = new Font("Cambria", Font.PLAIN, 16);
        categoryBox.setFont(buttonFont);
        category.setFont(buttonFont);

        Dimension dimensionBox = new Dimension(140, 30);
        categoryBox.setPreferredSize(dimensionBox);
        setCategory(categoryBox);

        JPanel categoryPanel = new JPanel(new FlowLayout());
        categoryPanel.add(category);
        categoryPanel.add(categoryBox);
        categoryPanel.setBackground(colorBackground);

        tableModel = new DefaultTableModel(new Object[]{"Breed Name", "Category", "Description"}, 0);
        breedsTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        breedsTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(breedsTable);
        frame.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBackground(colorBackground);

        // Set background color of the table
        breedsTable.setBackground(Color.LIGHT_GRAY);

        // Set font for the table
        Font tableFont = new Font("Cambria", Font.PLAIN, 14);
        breedsTable.setFont(tableFont);

        // Set background color and font for the header
        JTableHeader header = breedsTable.getTableHeader();
        header.setBackground(colorBackground);
        header.setFont(buttonFont);

        JButton updateButton = new JButton("Update Table");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryBox.getSelectedItem();
                updateBreedsTable(selectedCategory);
            }
        });

        JButton sortButton = new JButton("Sort Alphabetically");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryBox.getSelectedItem();
                try {
                    List<BreedModel> breeds = Breed.getBreedsFromDatabase(getCategoryId(selectedCategory), sorter.getSortKeys().get(0).getSortOrder().toString());
                    updateTableData(breeds);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error fetching breeds from the database: " + ex.getMessage());
                }
            }
        });
        JButton backButton=new JButton();
        String imagePathButton="/images/leftarrow.png";
        URL imageURLButton=getClass().getResource(imagePathButton);
        ImageIcon originalIconButton = new ImageIcon(imageURLButton);
        Image resizedImageButton = originalIconButton.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconButton = new ImageIcon(resizedImageButton);

        backButton.addActionListener(e->controller.backButtonListener(model));
        backButton.setIcon(resizedIconButton);
        backButton.setText("<html><center>HomePage</center></html>");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(updateButton);
        buttonPanel.setBackground(colorBackground);

        frame.add(categoryPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void updateBreedsTable(String category) {
        try {
            List<BreedModel> breeds = Breed.getBreedsFromDatabase(getCategoryId(category), "");
            updateTableData(breeds);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error fetching breeds from the database: " + ex.getMessage());
        }
    }

    private void updateTableData(List<BreedModel> breeds) {
        tableModel.setRowCount(0);
        String breedSearch = null;
        for (BreedModel breedModel : breeds) {
            int category=breedModel.getAnimalTypeID();
            if(category==1)  breedSearch="Dog";
            else if(category==2) breedSearch="Cat";
            else if(category==3) breedSearch="Bird";
            else if(category==4) breedSearch="Small and furry pets";
            else if(category==5) breedSearch="Rabbit";
            else if(category==6) breedSearch="Horse";
            else if(category==7) breedSearch="Fish";
            tableModel.addRow(new Object[]{breedModel.getBreedName(), breedSearch, breedModel.getDescription()});
        }
    }

    private int getCategoryId(String category) {
        switch (category) {
            case "Dog": return 1;
            case "Cat": return 2;
            case "Bird": return 3;
            case "Small and furry": return 4;
            case "Rabbit": return 5;
            case "Horse": return 6;
            case "Fish": return 7;
            default: return -1; // Handle unknown category
        }
    }

    public void setCategory(JComboBox<String> category) {
        category.addItem("Dog");
        category.addItem("Cat");
        category.addItem("Bird");
        category.addItem("Small and furry");
        category.addItem("Rabbit");
        category.addItem("Horse");
        category.addItem("Fish");
    }
}
