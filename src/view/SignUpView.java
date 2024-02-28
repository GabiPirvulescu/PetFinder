package view;

import controller.SignUpController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SignUpView extends JFrame {

    JLabel title = new JLabel("Create your account");
    JLabel subtitle = new JLabel("Sign up and shape your experience");

    JLabel firstName = new JLabel("First name");
    JLabel lastName = new JLabel("Last name");
    JLabel gender = new JLabel("Gender");
    JLabel county = new JLabel("County");
    JLabel city = new JLabel("City");
    JLabel phone = new JLabel("Phone");
    JLabel email = new JLabel("Email");
    JLabel password = new JLabel("Passsword");
    JLabel role = new JLabel("Role");
    JLabel dateOfBirth = new JLabel("Date of Birth");
    JLabel username=new JLabel("Username");

    JTextField firstNameBox = new JTextField();
    JTextField lastNameBox = new JTextField();
    JTextField phoneBox = new JTextField();
    JTextField emailBox = new JTextField();
    JTextField cityBox = new JTextField();
    JPasswordField passwordBox = new JPasswordField();
    JComboBox<String> genderBox = new JComboBox<>();
    JComboBox<String> countyBox = new JComboBox<>();
    JComboBox<String> roleBox = new JComboBox<>();
    JButton signUp = new JButton("Sign Up");
    JComboBox<String> month = new JComboBox<>();
    JComboBox<Integer> year = new JComboBox<>();
    JComboBox<Integer> day = new JComboBox<>();
    JTextField usernameBox=new JTextField();

    public JButton getSignUp() {
        return signUp;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JTextField getFirstNameBox() {
        return firstNameBox;
    }

    public JTextField getLastNameBox() {
        return lastNameBox;
    }


    public JTextField getPhoneBox() {
        return phoneBox;
    }


    public JTextField getEmailBox() {
        return emailBox;
    }

    public JTextField getCityBox() {
        return cityBox;
    }

    public JPasswordField getPasswordBox() {
        return passwordBox;
    }

    public JComboBox<String> getGenderBox() {
        return genderBox;
    }

    public JComboBox<String> getCountyBox() {
        return countyBox;
    }

    public JComboBox<String> getRoleBox() {
        return roleBox;
    }


    public JComboBox<String> getMonth() {
        return month;
    }


    public JComboBox<Integer> getYear() {
        return year;
    }

    public JComboBox<Integer> getDay() {
        return day;
    }


    public JTextField getUsernameBox() {
        return usernameBox;
    }


    public SignUpView(SignUpController signUpController) {
        Color colorBackground=new Color(224,236,243);

        Dimension textFieldSize = new Dimension(150, 24);
        Dimension smallTextField = new Dimension(100, 20);
        //  Dimensio
        Font customFontTitle = new Font("Cambria", Font.BOLD, 24);
        Font customFontSubtitle = new Font("Cambria", Font.BOLD, 20);
        Font customFontLabel = new Font("Cambria", Font.TRUETYPE_FONT, 17);
        Font customFontSign = new Font("Cambria", Font.BOLD, 15);
        Font customFontSmall= new Font("Cambria",Font.TRUETYPE_FONT,14);

        title.setFont(customFontTitle);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(customFontSubtitle);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setFont(customFontLabel);
        lastName.setFont(customFontLabel);
        phone.setFont(customFontLabel);
        city.setFont(customFontLabel);
        county.setFont(customFontLabel);
        email.setFont(customFontLabel);
        gender.setFont(customFontLabel);
        password.setFont(customFontLabel);
        role.setFont(customFontLabel);
        signUp.setFont(customFontSign);
        dateOfBirth.setFont(customFontLabel);
        username.setFont(customFontLabel);

        firstNameBox.setPreferredSize(textFieldSize);
        lastNameBox.setPreferredSize(textFieldSize);
        phoneBox.setPreferredSize(textFieldSize);
        emailBox.setPreferredSize(textFieldSize);
        cityBox.setPreferredSize(textFieldSize);
        passwordBox.setPreferredSize(textFieldSize);
        genderBox.setPreferredSize(smallTextField);
        countyBox.setPreferredSize(smallTextField);
        roleBox.setPreferredSize(smallTextField);
        day.setPreferredSize(smallTextField);
        month.setPreferredSize(smallTextField);
        year.setPreferredSize(smallTextField);
        usernameBox.setPreferredSize(textFieldSize);

        genderBox.setFont(customFontSmall);
        countyBox.setFont(customFontSmall);
        roleBox.setFont(customFontSmall);
        day.setFont(customFontSmall);
        month.setFont(customFontSmall);
        year.setFont(customFontSmall);

        setDays(day);
        setMonths(month);
        setYear(year);
        setGender(genderBox);
        setRole(roleBox);
        setCounty(countyBox);

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(colorBackground);

        JPanel titlePanel = new JPanel(new GridLayout(5, 1));
        titlePanel.setBackground(colorBackground);
        titlePanel.add(new JLabel(" "));
        titlePanel.add(new JLabel(" "));
        titlePanel.add(new JLabel(" "));
        titlePanel.add(title);
        titlePanel.add(subtitle);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(colorBackground);

        JPanel birthPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        birthPanel.setBackground(colorBackground);
        birthPanel.add(day);
        birthPanel.add(month);
        birthPanel.add(year);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // Align labels to the left
        gbc.insets = new Insets(5, 5, 5, 5); // Add space between labels and text fields

        // Add labels and text fields with GridBagConstraints
        centerPanel.add(firstName, gbc);
        gbc.gridy++;
        centerPanel.add(lastName, gbc);
        gbc.gridy++;
        centerPanel.add(username,gbc);
        gbc.gridy++;
        centerPanel.add(email, gbc);
        gbc.gridy++;
        centerPanel.add(password, gbc);
        gbc.gridy++;
        centerPanel.add(role, gbc);
        gbc.gridy++;
        centerPanel.add(dateOfBirth,gbc);
        gbc.gridy++;
        centerPanel.add(gender, gbc);
        gbc.gridy++;
        centerPanel.add(county, gbc);
        gbc.gridy++;
        centerPanel.add(city, gbc);
        gbc.gridy++;
        centerPanel.add(phone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align text fields to the right

        centerPanel.add(firstNameBox, gbc);
        gbc.gridy++;
        centerPanel.add(lastNameBox, gbc);
        gbc.gridy++;
        centerPanel.add(usernameBox,gbc);
        gbc.gridy++;
        centerPanel.add(emailBox, gbc);
        gbc.gridy++;
        centerPanel.add(passwordBox, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(roleBox, gbc);
        gbc.gridy++;
        centerPanel.add(birthPanel,gbc);
        gbc.gridy++;
        centerPanel.add(genderBox, gbc);
        gbc.gridy++;
        centerPanel.add(countyBox, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        centerPanel.add(cityBox, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(phoneBox, gbc);

        signUp.addActionListener(e-> {
            try {
                signUpController.signUpButtonListener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(colorBackground);
        signUpPanel.setPreferredSize(new Dimension(100, 100));
        signUp.setPreferredSize(new Dimension(86, 30));
        signUpPanel.add(signUp);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(signUpPanel, BorderLayout.SOUTH);
        add(mainPanel);
        mainPanel.setVisible(true);
        JFrame frame = new JFrame("SignUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void setDays(JComboBox<Integer> days){
        for(int day=1;day<=31;day++){
            days.addItem(day);
        }
    }

    public void setMonths(JComboBox<String> months){
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        for (String month : monthNames) {
            months.addItem(month);
        }
    }

    public void setYear(JComboBox<Integer> year){

        for (int years = 1900; years <= 2024; years++) {
            year.addItem(years);
        }
    }

    public void setGender(JComboBox<String> gender){
        gender.addItem("Male");
        gender.addItem("Female");
        gender.addItem("Other");
    }

    public void setRole(JComboBox<String> role){
        role.addItem("User");
        role.addItem("Shelter");
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
