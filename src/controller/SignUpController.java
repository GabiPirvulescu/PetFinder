package controller;

import model.LoginModel;
import model.SignUpModel;
import repository.Person;
import repository.User;
import view.LoginView;
import view.SignUpView;

import java.util.regex.*;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class SignUpController {

    public SignUpView view;
    public SignUpModel model;
    public LoginModel loginModel;
    Person person = new Person();
    User user=new User();

    public SignUpView getView() {
        return view;
    }

    public void setView(SignUpView view) {
        this.view = view;
    }

    public SignUpModel getModel() {
        return model;
    }

    public void setModel(SignUpModel model) {
        this.model = model;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void signUpButtonListener() throws SQLException {
        loginModel=new LoginModel();
        model = new SignUpModel();
        model.setFirstName(view.getFirstNameBox().getText());
        model.setLastName(view.getLastNameBox().getText());

        model.setUsername(view.getUsernameBox().getText());
        loginModel.setUsername(view.getUsernameBox().getText());

        //verify gender
        int genderID;
        String gender = view.getGenderBox().getSelectedItem().toString();
        if (gender.equals("Male")) {
            genderID = 1;
        } else if (gender.equals("Female")) {
            genderID = 2;
        } else {
            genderID = 3;
        }

        model.setGender(genderID);

        //verify date of birth
        int selectedDay = (int) view.getDay().getSelectedItem();
        int selectedMonth = view.getMonth().getSelectedIndex();
        int selectedYear = (int) view.getYear().getSelectedItem();

        LocalDate dateofBirth = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay);
        model.setBirth(dateofBirth);

        String selectedCounty = view.getCountyBox().getSelectedItem().toString();
        String selectedCity = view.getCityBox().getText();

        model.setCity(selectedCity);
        model.setCounty(selectedCounty);

        model.setPhone(view.getPhoneBox().getText());

        String email = view.getEmailBox().getText();
        while (!emailValidator(email)) {
            int option = JOptionPane.showOptionDialog(this.getView(), "Invalid email address. Retry?", "Retry", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    null
            );

            if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
                return;
            }
            email = JOptionPane.showInputDialog(this.getView(), "Enter a valid email:", "Retry", JOptionPane.PLAIN_MESSAGE);
            if (email == null) {
                return;
            }
        }

        // Now, the 'email' variable contains a valid email
        model.setEmail(email);
        char[] passwordChars = view.getPasswordBox().getPassword();
        String password = new String(passwordChars);

        model.setPassword(password);
        loginModel.setPassword(password);

        int roleID = 0;
        String role = view.getRoleBox().getSelectedItem().toString();
        if (role.equals("User")) {
            roleID = 1;
        } else if (role.equals("Shelter")) {
            roleID = 2;
        }

        model.setRoleID(roleID);
        loginModel.setRoleID(roleID);

        String resultMessage = person.addAccount(model);
        user.addUser(loginModel);
        JLabel messageLabel = new JLabel(resultMessage);
        JOptionPane.showMessageDialog(this.getView(), messageLabel, "Sign Up Result", JOptionPane.INFORMATION_MESSAGE);
        if (resultMessage.equals("Account created successfully.")) {
            LoginController controller=new LoginController();
            LoginView loginView=new LoginView(controller);
            controller.setLoginView(loginView);
            view.setVisible(false);
        }

    }

    public boolean emailValidator(String email) {
        String regex = "^[a-zA-Z]+(\\.[a-zA-Z]+)?@(gmail|yahoo|)\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

}