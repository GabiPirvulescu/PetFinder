package view;

import controller.HomePageController;
import model.LoginModel;
import model.SignUpModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class HomePageView extends JFrame {

    LoginModel model=new LoginModel();

    JLabel welcome = new JLabel("<html>Welcome to<br>PetFinder Platform</html>");
    JButton account=new JButton("Your account");
    JButton addAnimal=new JButton("Add an animal");
    JButton breeds =new JButton("Info about breeds");
    JButton favorite=new JButton(" ");
    JButton searchPet=new JButton("Search for a pet");
    JButton shelter=new JButton("");

    public HomePageView(HomePageController controller, SignUpModel model) {

        Color colorBackground=new Color(192, 214, 223);
        Color colorBar=new Color(61,90,128);
        Color colorButton=new Color(224,236,243);

        JLabel user=new JLabel(model.getUsername());
        user.setForeground(Color.white);
        account.setBackground(colorButton);
        addAnimal.setBackground(colorButton);
        breeds.setBackground(colorButton);
        favorite.setBackground(colorButton);
        searchPet.setBackground(colorButton);
        shelter.setBackground(colorButton);

        Dimension buttonSize = new Dimension(60, 60); // Adjust the size based on your preferences

        account.setPreferredSize(buttonSize);
        addAnimal.setPreferredSize(buttonSize);
        breeds.setPreferredSize(buttonSize);
        favorite.setPreferredSize(buttonSize);
        searchPet.setPreferredSize(buttonSize);
        shelter.setPreferredSize(buttonSize);

        Font welcomeFont=new Font("Cambria",Font.BOLD,24);
        Font userFont=new Font("Cambria",Font.PLAIN,18);
        Font buttonFont=new Font("Cambria",Font.PLAIN,20);
        welcome.setFont(welcomeFont);
        user.setFont(userFont);
        account.setFont(buttonFont);
        addAnimal.setFont(buttonFont);
        breeds.setFont(buttonFont);
        favorite.setFont(buttonFont);
        searchPet.setFont(buttonFont);
        shelter.setFont(buttonFont);

        String imagePath="/images/petlogo2.png";
        URL imageURL=getClass().getResource(imagePath);
        ImageIcon originalIcon = new ImageIcon(imageURL);
        Image resizedImage = originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel iconLabel=new JLabel(resizedIcon);

        JFrame frame = new JFrame("HomePage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setLayout(new BorderLayout());
        JPanel mainPanel=new JPanel(new BorderLayout());
        mainPanel.setBackground(colorBackground);

        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bar.setBackground(colorBar);
        bar.add(user);

        JPanel welcomePanel=new JPanel(new GridLayout(3,1));
        welcomePanel.add(new JLabel(" "));
        welcomePanel.add(welcome);
        welcomePanel.add(iconLabel);
        welcomePanel.setBackground(colorBackground);

        account.addActionListener(e-> {
            try {
                controller.accountListener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        addAnimal.addActionListener(e->controller.addAnimalListener());
        searchPet.addActionListener(e->controller.searchPetListener());
        breeds.addActionListener(e->controller.breedsListener(model));
        JPanel buttonPanel=new JPanel(new GridLayout(3,2));

        String imagePathButton="/images/home.png";
        URL imageURLButton=getClass().getResource(imagePathButton);
        ImageIcon originalIconButton = new ImageIcon(imageURLButton);
        Image resizedImageButton = originalIconButton.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIconButton = new ImageIcon(resizedImageButton);

        favorite.setIcon(resizedIconButton);
        buttonPanel.add(account);
        buttonPanel.add(favorite);
        buttonPanel.add(addAnimal);
        buttonPanel.add(searchPet);
        buttonPanel.add(breeds);
        //buttonPanel.add(shelter);
        buttonPanel.setBackground(colorBackground);

        mainPanel.add(bar,BorderLayout.NORTH);
        mainPanel.add(welcomePanel,BorderLayout.WEST);
        mainPanel.add(buttonPanel,BorderLayout.CENTER);

        frame.add(mainPanel);

    }

}
