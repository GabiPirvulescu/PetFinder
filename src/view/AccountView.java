package view;

import controller.AccountController;
import model.AnimalModel;
import model.SignUpModel;
import repository.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountView extends JFrame {
     JButton backButton=new JButton();
     JButton update=new JButton("Change username");
     private JLabel username;
     public AccountView(AccountController controller, SignUpModel model) throws SQLException, IOException {

          JFrame frame = new JFrame("Your account");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(700, 700);
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);

          Font customFontName=new Font("Cambria",Font.BOLD,24);
          Font customFontLabel=new Font("Cambria", Font.PLAIN,20);

          Color colorBackground=new Color(192, 214, 223);
          Color colorBar=new Color(61,90,128);

          update.setBackground(Color.pink);
          update.setFont(customFontLabel);

          update.addActionListener(e->controller.updateListener(model));

          setLayout(new BorderLayout());
          JPanel mainPanel=new JPanel(new BorderLayout());
          mainPanel.setBackground(colorBackground);

          JPanel centerPanel=new JPanel(new GridLayout(1,2));
          centerPanel.setBackground(colorBackground);

          JPanel rightInfo=new JPanel(new GridBagLayout());
          rightInfo.setBackground(colorBackground);

          String imagePath="/images/personicon.png";
          URL imageURL=getClass().getResource(imagePath);
          ImageIcon originalIcon = new ImageIcon(imageURL);
          Image resizedImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
          ImageIcon resizedIcon = new ImageIcon(resizedImage);

          JLabel iconLabel = new JLabel(resizedIcon);

          this.username = new JLabel(model.getUsername());
          JLabel firstName=new JLabel(model.getFirstName());
          JLabel lastName=new JLabel(model.getLastName());
          this.username.setFont(customFontLabel);

          firstName.setFont(customFontName);
          lastName.setFont(customFontName);
          username.setFont(customFontLabel);

          JPanel namePanel = new JPanel();
          namePanel.setBackground(colorBackground);
          namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));

          firstName.setFont(customFontName);
          lastName.setFont(customFontName);

          namePanel.add(firstName);
          namePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing
          namePanel.add(lastName);

          GridBagConstraints gbc = new GridBagConstraints();
          gbc.gridx = 0;
          gbc.gridy = 0;
          gbc.insets = new Insets(0, 0, 0, 0); // Adjust top margin
          rightInfo.add(iconLabel, gbc);

          gbc.gridy++;
          gbc.insets = new Insets(0, 0, 0, 0); // Adjust top margin
          rightInfo.add(new JLabel(" "), gbc); // Add an empty label for spacing

          gbc.gridy++;
          rightInfo.add(namePanel, gbc);

          gbc.gridy++;
          rightInfo.add(username, gbc);
          gbc.gridy++;
          rightInfo.add(update,gbc);

          JPanel right=new JPanel(new GridLayout(3,1));
          right.add(rightInfo);
          right.setBackground(colorBackground);
          JPanel empty=new JPanel();
          empty.setBackground(colorBackground);
          right.add(empty);
          right.add(empty);

          JPanel leftInfo=new JPanel();
          leftInfo.setLayout(new BoxLayout(leftInfo, BoxLayout.Y_AXIS)); // Set Y_AXIS layout
          leftInfo.setBackground(colorBackground);
          leftInfo.setBorder(BorderFactory.createEmptyBorder(107, 0, 0, 0)); // Adjust the top margin

          JLabel email=new JLabel(model.getEmail());
          String gender;
          if(model.getGender()==1)
               gender="Male";
          else if (model.getGender()==2)
               gender="Female";
          else gender="Other";
          JLabel genderLabel = new JLabel("<html><b>Gender:</b> " + gender + "</html>");
          JLabel dateOfBirth = new JLabel("<html><b>Birthday:</b> " + String.valueOf(model.getBirth()) + "</html>");
          JLabel city = new JLabel("<html><b>City:</b> " + model.getCity() + "</html>");
          JLabel county = new JLabel("<html><b>County:</b> " + model.getCounty() + "</html>");
          JLabel phone = new JLabel("<html><b>Contact number:</b> " + model.getPhone() + "</html>");
          JLabel accountDetails= new JLabel("Information about you");
          JLabel animalsFound=new JLabel("<html><b>Animals found by you:<html><b> ");
          JLabel animalsAdopted=new JLabel("<html><b>Animals given for adoption:<html><b> ");

          genderLabel.setFont(customFontLabel);
          dateOfBirth.setFont(customFontLabel);
          city.setFont(customFontLabel);
          county.setFont(customFontLabel);
          phone.setFont(customFontLabel);
          accountDetails.setFont(customFontName);
          animalsAdopted.setFont(customFontLabel);
          animalsFound.setFont(customFontLabel);

          leftInfo.add(accountDetails);
          leftInfo.add(new JLabel(" "));
          leftInfo.add(new JLabel(" "));
          leftInfo.add(new JLabel(" "));
          leftInfo.add(genderLabel);
          leftInfo.add(dateOfBirth);
          leftInfo.add(city);
          leftInfo.add(county);
          leftInfo.add(phone);
          leftInfo.add(animalsFound);
          //leftInfo.add(animalsAdopted);

          JPanel navbar = new JPanel(new BorderLayout());
          navbar.setBackground(colorBackground);

          String imagePathButton="/images/leftarrow.png";
          URL imageURLButton=getClass().getResource(imagePathButton);
          ImageIcon originalIconButton = new ImageIcon(imageURLButton);
          Image resizedImageButton = originalIconButton.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
          ImageIcon resizedIconButton = new ImageIcon(resizedImageButton);

          backButton.addActionListener(e->controller.backButtonListener());
          backButton.setIcon(resizedIconButton);
          backButton.setText("<html><center>HomePage</center></html>");

          JPanel animalInfoPanel=new JPanel();
          animalInfoPanel.setLayout(new BoxLayout(animalInfoPanel,BoxLayout.Y_AXIS));
          animalInfoPanel.setBackground(colorBackground);

          List<AnimalModel> animalListModel = Animal.getAnimalDetails(model.getUsername());

          for (AnimalModel animal : animalListModel) {
               JPanel animalPanel = new JPanel();
               animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS)); // Set layout to vertical

               JLabel animalName = new JLabel("Name: " + animal.getName());
               JLabel animalBreed = new JLabel("Breed: " + animal.getBreed());
               JLabel animalAge = new JLabel("LifeStage: " + animal.getAge());
               JLabel foundDate = new JLabel("Found Date: " + animal.getFoundDate());
               JButton deleteButton=new JButton("Delete");
               deleteButton.setBackground(Color.PINK);

               deleteButton.setFont(customFontLabel);
               animalName.setFont(customFontLabel);
               animalBreed.setFont(customFontLabel);
               animalAge.setFont(customFontLabel);
               foundDate.setFont(customFontLabel);

//               BufferedImage myPicture = ImageIO.read(new File(animal.getUrl()));
//               JLabel animalLabel = new JLabel(new ImageIcon(myPicture));

               String animalPath = animal.getUrl();
               URL animalURL = getClass().getResource(animalPath);
               ImageIcon animalOriginalIcon = new ImageIcon(animalURL);
               Image animalResizedImage = animalOriginalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust image size
               ImageIcon animalResizedIcon = new ImageIcon(animalResizedImage);
               JLabel animalLabel = new JLabel(animalResizedIcon);
               animalPanel.setBackground(colorBackground);

               deleteButton.addActionListener(e->controller.deleteButtonListener(animal));

               animalPanel.add(new JLabel(" "));
               animalPanel.add(animalName);
               animalPanel.add(animalBreed);
               animalPanel.add(animalAge);
               animalPanel.add(foundDate);
               animalPanel.add(animalLabel);
               animalPanel.add(deleteButton);
               animalInfoPanel.add(animalPanel);

          }

          JScrollPane animalScrollPane = new JScrollPane(animalInfoPanel);
          animalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
          leftInfo.add(animalScrollPane);

          navbar.add(backButton, BorderLayout.WEST);
          centerPanel.add(right);
          centerPanel.add(leftInfo);
          mainPanel.add(navbar,BorderLayout.SOUTH);
          mainPanel.add(centerPanel,BorderLayout.CENTER);
          frame.add(mainPanel);

     }
     public void refresh(SignUpModel updatedModel) {
          this.username.setText(updatedModel.getUsername());
     }

}
