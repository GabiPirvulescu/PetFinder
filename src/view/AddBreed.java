package view;

import controller.AddBreedController;
import view.Colors;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddBreed extends JFrame {
    JLabel title = new JLabel("Add a new breed");
    JLabel animalCategory = new JLabel("Animal category");
    JLabel breedName = new JLabel("Breed name");
    JLabel description = new JLabel("Description");

    JComboBox<String> animalCategoryBox = new JComboBox<>();
    JTextField breedNameBox = new JTextField();
    JTextField descriptionBox = new JTextField();
    JButton addButton=new JButton("Add");

    public JComboBox<String> getAnimalCategoryBox() {
        return animalCategoryBox;
    }

    public void setAnimalCategoryBox(JComboBox<String> animalCategoryBox) {
        this.animalCategoryBox = animalCategoryBox;
    }

    public JTextField getBreedNameBox() {
        return breedNameBox;
    }

    public void setBreedNameBox(JTextField breedNameBox) {
        this.breedNameBox = breedNameBox;
    }

    public JTextField getDescriptionBox() {
        return descriptionBox;
    }

    public void setDescriptionBox(JTextField descriptionBox) {
        this.descriptionBox = descriptionBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public AddBreed(AddBreedController controller) {
        JFrame frame = new JFrame("Add breed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Font titleFont = new Font("Cambria", Font.BOLD, 22);
        Font labelFont=new Font("Cambria",Font.PLAIN,18);
        Dimension dimensionBox=new Dimension(200,30);
        breedNameBox.setPreferredSize(dimensionBox);
        descriptionBox.setPreferredSize(new Dimension(200,70));
        animalCategoryBox.setPreferredSize(dimensionBox);
        addButton.setPreferredSize(new Dimension(100,30));

        breedName.setFont(labelFont);
        description.setFont(labelFont);
        animalCategory.setFont(labelFont);
        addButton.setFont(labelFont);

        setCategory(animalCategoryBox);
        animalCategoryBox.setBackground(Colors.WHITE.getColor());
        animalCategoryBox.setFont(labelFont);

        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        title.setFont(titleFont);
        titlePanel.add(title);
        titlePanel.setBackground(Colors.BACKGROUND.getColor());

        setLayout(new BorderLayout());
        JPanel mainPanel=new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Colors.BACKGROUND.getColor());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        centerPanel.add(animalCategory, gbc);

        gbc.gridy++;
        centerPanel.add(breedName, gbc);

        gbc.gridy++;
        centerPanel.add(description, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;

        centerPanel.add(animalCategoryBox, gbc);

        gbc.gridy++;
        centerPanel.add(breedNameBox, gbc);

        gbc.gridy++;
        centerPanel.add(descriptionBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        addButton.addActionListener(e-> {
            try {
                controller.addButtonListener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton seeBreeds=new JButton("See breeds");
        seeBreeds.addActionListener(e->controller.seeBreedListener());
        centerPanel.add(addButton, gbc);
        gbc.gridy++;
        centerPanel.add(seeBreeds,gbc);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        frame.add(mainPanel);
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
