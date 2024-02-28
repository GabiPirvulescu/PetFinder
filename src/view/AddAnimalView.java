package view;

import controller.AddAnimalController;
import model.SignUpModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;

public class AddAnimalView extends JFrame {

    JLabel addText=new JLabel("       Add a new animal");
    JLabel motto=new JLabel("Speak up for those who cannot");
    JLabel name=new JLabel("Animal Name");
    JLabel category=new JLabel("Category");
    JLabel gender=new JLabel("Gender");
    JLabel size=new JLabel("Size");
    JLabel age=new JLabel( "Age");
    JLabel breed= new JLabel("Breed");
    JLabel color=new JLabel("Color");
    JLabel description= new JLabel("Description");
    JLabel photo=new JLabel("Add a photo");
    JLabel city=new JLabel("City");
    JLabel county=new JLabel("County");
    JLabel street=new JLabel("Street");
    JLabel nr=new JLabel("Nr");


    JTextField nameBox=new JTextField();
    JComboBox<String> categoryBox=new JComboBox<>();
    JComboBox<String> genderBox=new JComboBox<>();
    JComboBox<String> sizeBox=new JComboBox<>();
    JComboBox<String> ageBox=new JComboBox<>();
    JComboBox<String> breedBox=new JComboBox<>();
    JComboBox<String> colorBox=new JComboBox<>();
    JTextField descriptionBox=new JTextField();
    JTextField photoBox=new JTextField();
    JButton addButton=new JButton("Add");
    JButton backButton=new JButton();
    JTextField cityBox=new JTextField();
    JComboBox<String> countyBox=new JComboBox<>();
    JTextField streetBox=new JTextField();
    JTextField nrBox=new JTextField();

    public JLabel getAddText() {
        return addText;
    }

    public JTextField getCityBox() {
        return cityBox;
    }

    public JComboBox<String> getCountyBox() {
        return countyBox;
    }

    public JTextField getStreetBox() {
        return streetBox;
    }

    public JTextField getNrBox() {
        return nrBox;
    }

    public JComboBox<String> getCategoryBox() {
        return categoryBox;
    }

    public JComboBox<String> getBreedBox() {
        return breedBox;
    }

    public JTextField getNameBox() {
        return nameBox;
    }

    public JComboBox<String> getGenderBox() {
        return genderBox;
    }

    public JComboBox<String> getSizeBox() {
        return sizeBox;
    }

    public JComboBox<String> getAgeBox() {
        return ageBox;
    }

    public JComboBox<String> getColorBox() {
        return colorBox;
    }

    public JTextField getDescriptionBox() {
        return descriptionBox;
    }

    public JTextField getPhotoBox() {
        return photoBox;
    }

    public AddAnimalView(AddAnimalController controller, SignUpModel model){
        JFrame frame = new JFrame("Add an animal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Font fontTitle=new Font("Cambria", Font.BOLD,25);
        Font fontSubtitle=new Font("Cambria", Font.BOLD,20);
        Font fontLabel=new Font("Cambria",Font.PLAIN,16);

        addText.setFont(fontTitle);
        motto.setFont(fontSubtitle);

        Color colorBackground=new Color(192, 214, 223);
        Dimension dimensionBox=new Dimension(250,30);
        Dimension dimensionDescrip=new Dimension(250,100);
        Dimension dimensionLittleBox=new Dimension(100,30);

        nameBox.setPreferredSize(dimensionBox);
        categoryBox.setPreferredSize(dimensionLittleBox);
        genderBox.setPreferredSize(dimensionLittleBox);
        sizeBox.setPreferredSize(dimensionLittleBox);
        ageBox.setPreferredSize(dimensionLittleBox);
        breedBox.setPreferredSize(dimensionBox);
        colorBox.setPreferredSize(dimensionBox);
        descriptionBox.setPreferredSize(dimensionDescrip);
        photoBox.setPreferredSize(dimensionBox);
        cityBox.setPreferredSize(dimensionLittleBox);
        countyBox.setPreferredSize(dimensionLittleBox);
        streetBox.setPreferredSize(dimensionLittleBox);
        nrBox.setPreferredSize(dimensionLittleBox);

        genderBox.setFont(fontLabel);
        categoryBox.setFont(fontLabel);
        sizeBox.setFont(fontLabel);
        ageBox.setFont(fontLabel);
        breedBox.setFont(fontLabel);
        colorBox.setFont(fontLabel);
        countyBox.setFont(fontLabel);

        categoryBox.setBackground(Color.WHITE);
        genderBox.setBackground(Color.WHITE);
        sizeBox.setBackground(Color.WHITE);
        ageBox.setBackground(Color.WHITE);
        breedBox.setBackground(Color.WHITE);
        colorBox.setBackground(Color.WHITE);
        countyBox.setBackground(Color.WHITE);

        setGender(genderBox);
        setAge(ageBox);
        setSize(sizeBox);
        setCategory(categoryBox);
        setColor(colorBox);
        setCounty(countyBox);

        name.setFont(fontLabel);
        gender.setFont(fontLabel);
        category.setFont(fontLabel);
        size.setFont(fontLabel);
        age.setFont(fontLabel);
        breed.setFont(fontLabel);
        color.setFont(fontLabel);
        description.setFont(fontLabel);
        photo.setFont(fontLabel);
        city.setFont(fontLabel);
        county.setFont(fontLabel);
        street.setFont(fontLabel);
        nr.setFont(fontLabel);

        categoryBox.addActionListener(e-> {
            try {
                controller.categoryBoxListener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JPanel mainPanel=new JPanel(new BorderLayout());
        mainPanel.setBackground(colorBackground);

        JPanel genderPanel=new JPanel(new FlowLayout());
        genderPanel.add(gender);
        genderPanel.add(genderBox);
        genderPanel.setBackground(colorBackground);

        JPanel agePanel=new JPanel(new FlowLayout());
        agePanel.add(age);
        agePanel.add(ageBox);
        agePanel.setBackground(colorBackground);

        JPanel cityPanel=new JPanel(new FlowLayout());
        cityPanel.add(city);
        cityPanel.add(cityBox);
        cityPanel.setBackground(colorBackground);

        JPanel nrPanel=new JPanel(new FlowLayout());
        nrPanel.add(nr);
        nrPanel.add(nrBox);
        nrPanel.setBackground(colorBackground);

        JPanel categoryPanel=new JPanel(new FlowLayout());
        categoryPanel.add(categoryBox);
        categoryPanel.add(genderPanel);
        categoryPanel.setBackground(colorBackground);

        JPanel sizePanel=new JPanel(new FlowLayout());
        sizePanel.add(sizeBox);
        sizePanel.add(agePanel);
        sizePanel.setBackground(colorBackground);

        JPanel countyPanel=new JPanel(new FlowLayout());
        countyPanel.add(countyBox);
        countyPanel.add(cityPanel);
        countyPanel.setBackground(colorBackground);

        JPanel streetPanel=new JPanel(new FlowLayout());
        streetPanel.add(streetBox);
        streetPanel.add(nrPanel);
        streetPanel.setBackground(colorBackground);

        JLabel imageLabel = new JLabel();
        String imagePath = "/images/petlogo.png";
        URL imageURL = getClass().getResource(imagePath);
        ImageIcon originalIcon = new ImageIcon(imageURL);
        Image resizedImage = originalIcon.getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        imageLabel.setIcon(resizedIcon);

        JPanel textPanel=new JPanel(new GridLayout(3,1));
        textPanel.setBackground(colorBackground);
        textPanel.add(new JLabel(" "));
        textPanel.add(addText);
        textPanel.add(motto);

        setLayout(new GridLayout());
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(colorBackground);
        titlePanel.add(textPanel);
        titlePanel.add(imageLabel);

        mainPanel.add(titlePanel,BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(colorBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // Align labels to the left
        gbc.insets = new Insets(5, 5, 5, 5); // Add space between labels and text fields

        centerPanel.add(name, gbc);
        gbc.gridy++;
        centerPanel.add(category,gbc);
        gbc.gridy++;
        centerPanel.add(size,gbc);
        gbc.gridy++;
        centerPanel.add(breed, gbc);
        gbc.gridy++;
        centerPanel.add(color,gbc);
        gbc.gridy++;
        centerPanel.add(description, gbc);
        gbc.gridy++;
        centerPanel.add(county,gbc);
        gbc.gridy++;
        centerPanel.add(street,gbc);
        gbc.gridy++;
        centerPanel.add(photo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align text fields to the right

        centerPanel.add(nameBox, gbc);
        gbc.gridy++;
        centerPanel.add(categoryPanel,gbc);
        gbc.gridy++;
        centerPanel.add(sizePanel,gbc);
        gbc.gridy++;
        centerPanel.add(breedBox, gbc);
        gbc.gridy++;
        centerPanel.add(colorBox,gbc);
        gbc.gridy++;
        centerPanel.add(descriptionBox, gbc);
        gbc.gridy++;
        centerPanel.add(countyPanel,gbc);
        gbc.gridy++;
        centerPanel.add(streetPanel,gbc);
        gbc.gridy++;
        centerPanel.add(photoBox, gbc);

        addButton.addActionListener(e-> {
            try {
                controller.addButtonListener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());  // Use BorderLayout for buttonPanel
        buttonPanel.setBackground(colorBackground);
        buttonPanel.setFont(fontLabel);
        Dimension buttonSize = new Dimension(100, 40);

        JPanel centerButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerButtonPanel.add(addButton);
        centerButtonPanel.setBackground(colorBackground);
        buttonPanel.add(centerButtonPanel, BorderLayout.CENTER);
        addButton.setPreferredSize(buttonSize);

        String imagePathButton = "/images/leftarrow.png";
        URL imageURLButton = getClass().getResource(imagePathButton);
        ImageIcon originalIconButton = new ImageIcon(imageURLButton);
        Image resizedImageButton = originalIconButton.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconButton = new ImageIcon(resizedImageButton);

        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton.addActionListener(e->controller.backButtonListener());
        backButton.setIcon(resizedIconButton);
        backButton.setText("<html><center>HomePage</center></html>");
        leftButtonPanel.add(backButton);
        leftButtonPanel.setBackground(colorBackground);
        buttonPanel.add(leftButtonPanel, BorderLayout.WEST);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        frame.add(mainPanel);
    }

    public void setCategory(JComboBox<String> category){
        category.addItem("Dog");
        category.addItem("Cat");
        category.addItem("Bird");
        category.addItem("Small and furry");
        category.addItem("Rabbit");
        category.addItem("Horse");
        category.addItem("Fish");

    }
    public void setSize(JComboBox<String> size){
        size.addItem("Small");
        size.addItem("Medium");
        size.addItem("Large");
        size.addItem("Extra large");
    }
    public void setAge(JComboBox<String> age){
        age.addItem("Baby");
        age.addItem("Young");
        age.addItem("Adult");
        age.addItem("Elderly");
    }

    public void setColor(JComboBox<String> color) {
        // Add the color options to the JComboBox
        color.addItem("Apricot / Beige");
        color.addItem("Bicolor");
        color.addItem("Black");
        color.addItem("Brindle");
        color.addItem("Brown / Chocolate");
        color.addItem("Golden");
        color.addItem("Gray / Blue / Silver");
        color.addItem("Harlequin");
        color.addItem("Merle (Blue)");
        color.addItem("Merle (Red)");
        color.addItem("Red / Chestnut / Orange");
        color.addItem("Sable");
        color.addItem("Tricolor (Brown, Black, & White)");
        color.addItem("White / Cream");
        color.addItem("Yellow / Tan / Blond / Fawn");
    }
    public void setGender(JComboBox<String> gender){
        gender.addItem("Male");
        gender.addItem("Female");
        gender.addItem("Other");
    }
    public void setCounty(JComboBox<String> county) {
        String[] counties = {"Alba", "Arad", "Arges", "Bacau", "Bihor", "Bistrita-Nasaud", "Botosani", "Braila",
                "Brasov", "Bucuresti", "Buzau", "Calarasi", "Caras-Severin", "Cluj", "Constanta", "Covasna", "Dambovita",
                "Dolj", "Galati", "Giurgiu", "Gorj", "Harghita", "Hunedoara", "Ialomita", "Iasi", "Ilfov", "Maramures",
                "Mehedinti", "Mures", "Neamt", "Olt", "Prahova", "Salaj", "Satu Mare", "Sibiu", "Suceava", "Teleorman",
                "Timis", "Tulcea", "Vaslui", "Vrancea"};

        for (String countyName : counties) {
            county.addItem(countyName);
        }
    }
}
