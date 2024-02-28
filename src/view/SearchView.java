package view;

import controller.SearchController;
import controller.SignUpController;
import model.AnimalModel;
import model.SignUpModel;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class SearchView extends JFrame {

    JComboBox<String> categoryBox = new JComboBox<>();
    JComboBox<String> breedBox = new JComboBox<>();
    JTextField cityBox = new JTextField();
    JButton backButton = new JButton();
    JButton searchButton = new JButton("Search");
    JPanel animalPanel;
    JLabel breed = new JLabel("   BREED");
    JLabel category = new JLabel("   CATEGORY");
    JLabel city = new JLabel("   CITY");


    public JComboBox<String> getCategoryBox() {
        return categoryBox;
    }

    public JComboBox<String> getBreedBox() {
        return breedBox;
    }

    public JTextField getCityBox() {
        return cityBox;
    }

    public SearchView(SearchController controller, SignUpModel model) {
        JFrame frame = new JFrame("Search a pet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        animalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPane = new JScrollPane(animalPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        Color colorBackground = new Color(192, 214, 223);
        Color colorBar = new Color(61, 90, 128);
        animalPanel.setBackground(colorBackground);
        Font fontLabel = new Font("Cambria", Font.PLAIN, 18);
        breedBox.setFont(fontLabel);
        categoryBox.setFont(fontLabel);
        cityBox.setFont(fontLabel);
        category.setFont(fontLabel);
        city.setFont(fontLabel);
        breed.setFont(fontLabel);
        searchButton.setFont(fontLabel);

        category.setForeground(Color.BLACK);
        city.setForeground(Color.BLACK);
        breed.setForeground(Color.BLACK);

        Dimension dimensionBox = new Dimension(100, 25);
        breedBox.setPreferredSize(dimensionBox);
        categoryBox.setPreferredSize(dimensionBox);
        cityBox.setPreferredSize(dimensionBox);
        searchButton.setPreferredSize(dimensionBox);

        String imagePathButton = "/images/leftarrow.png";
        URL imageURLButton = getClass().getResource(imagePathButton);
        ImageIcon originalIconButton = new ImageIcon(imageURLButton);
        Image resizedImageButton = originalIconButton.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconButton = new ImageIcon(resizedImageButton);

        backButton.addActionListener(e -> controller.backButtonListener());
        backButton.setIcon(resizedIconButton);
        backButton.setText("<html><center>HomePage</center></html>");

        setCategory(categoryBox);
        categoryBox.addActionListener(e -> {
            try {
                controller.categoryBoxListener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        searchButton.addActionListener(e-> {
            try {
                controller.searchButtonListener(model);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(colorBackground);

        JPanel navbar = new JPanel(new GridLayout(2, 4));
        navbar.setBackground(colorBar);

        JPanel empty=new JPanel();
        empty.setBackground(colorBackground);

        navbar.add(backButton);
        navbar.add(category);
        navbar.add(breed);
        navbar.add(city);
        navbar.add(searchButton);

        navbar.add(categoryBox);
        navbar.add(breedBox);
        navbar.add(cityBox);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(navbar, BorderLayout.NORTH);
        frame.add(mainPanel);
    }


    public void updateAnimalBoxes(List<AnimalModel> animals, SearchController controller,SignUpModel model) {
        animalPanel.removeAll();

        for (AnimalModel animal : animals) {
            JPanel animalBox = createAnimalBox(animal,controller,model);
            animalPanel.add(animalBox);
        }

        animalPanel.revalidate();
        animalPanel.repaint();
    }
    private JPanel createAnimalBox(AnimalModel animal,SearchController controller,SignUpModel model) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JButton favoriteButton=new JButton("Add to favorites");
        JButton requestButton=new JButton("Request");
        Font fontLabel = new Font("Cambria", Font.PLAIN, 18);
        favoriteButton.setFont(fontLabel);
        requestButton.setFont(fontLabel);

        Dimension dimensionButton=new Dimension(30,30);
        favoriteButton.setPreferredSize(dimensionButton);
        requestButton.setPreferredSize(dimensionButton);

        favoriteButton.setBackground(Color.PINK);
        Color colorBackground = new Color(192, 214, 223);

        JLabel photoLabel = createPhotoLabel(animal.getUrl());
        box.add(photoLabel);

        JLabel nameLabel = new JLabel("Name: " + animal.getName());
        nameLabel.setFont(fontLabel);
        JLabel typeLabel = new JLabel("Type: " + animal.getAnimalType());
        typeLabel.setFont(fontLabel);
        JLabel breedLabel = new JLabel("Breed: " + animal.getBreed());
        breedLabel.setFont(fontLabel);
        JLabel descriptionLabel = new JLabel("Description: " + animal.getDescription());
        descriptionLabel.setFont(fontLabel);

        favoriteButton.addActionListener(e->controller.favoriteButtonListener(animal,model));
        requestButton.addActionListener(e->controller.requestButtonListener(animal,model));

        box.add(nameLabel);
        box.add(typeLabel);
        box.add(breedLabel);
        box.add(descriptionLabel);
        box.add(favoriteButton);
        //box.add(requestButton);

        box.add(Box.createRigidArea(new Dimension(0, 10)));
        box.setBackground(colorBackground);
        return box;
    }

    private JLabel createPhotoLabel(String animalPath) {
        JLabel photoLabel = new JLabel();
        URL animalURL = getClass().getResource(animalPath);
        ImageIcon imageIcon = new ImageIcon(animalURL);
        Image image = imageIcon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        photoLabel.setIcon(scaledImageIcon);
        return photoLabel;
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
