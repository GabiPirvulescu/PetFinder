package view;

import controller.ShelterController;
import model.SignUpModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ShelterView extends JFrame{

    JButton addBreeds=new JButton("Add a new breed");
    public ShelterView(ShelterController controller, SignUpModel model)
    {
        JFrame frame = new JFrame("Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Font welcomeFont=new Font("Cambria",Font.BOLD,24);
        Font subtitleFont=new Font("Cambria",Font.BOLD,22);
        Font userFont=new Font("Cambria",Font.PLAIN,18);
        Font buttonFont=new Font("Cambria",Font.BOLD,20);

        Dimension dimension=new Dimension(300,300);

        JPanel titlePanel=new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Colors.BAR.getColor());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JLabel welcomeLabel=new JLabel("Welcome, " + model.getUsername()+ "!");
        JLabel subtitleLabel=new JLabel(model.getFirstName() + " "+ model.getLastName() +", you are the manager!");

        welcomeLabel.setFont(welcomeFont);
        subtitleLabel.setFont(subtitleFont);

        welcomeLabel.setForeground(Colors.WHITE.getColor());
        subtitleLabel.setForeground(Colors.WHITE.getColor());
        welcomeLabel.setForeground(Color.WHITE);

        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        titlePanel.add(welcomeLabel);
        titlePanel.add(subtitleLabel);

        addBreeds.addActionListener(e->controller.addBreedListener(model));

        JPanel centerPanel=new JPanel(new FlowLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0));
        centerPanel.setBackground(Colors.BACKGROUND.getColor());
        addBreeds.setPreferredSize(dimension);
        addBreeds.setBackground(Colors.BUTTON.getColor());
        addBreeds.setFont(buttonFont);
        centerPanel.add(addBreeds);

        getContentPane().setLayout(new BorderLayout());
        JPanel mainPanel=new JPanel(new BorderLayout());
        mainPanel.setBackground(Colors.BACKGROUND.getColor());

        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.setBackground(Colors.BACKGROUND.getColor());
        frame.add(mainPanel);


    }
}
