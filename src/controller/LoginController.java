package controller;

import model.SignUpModel;
import repository.User;
import view.LoginView;
import view.SignUpView;

import javax.swing.*;
import java.sql.SQLException;

public class LoginController {

    LoginView loginView;
    User user=new User();
    public void signUpListener(){
        SignUpController signUpController=new SignUpController();
        SignUpView signUpView=new SignUpView(signUpController);
        signUpController.setView(signUpView);
        loginView.setVisible(false);
    }
    public void signInListener() throws SQLException {

        User user=new User();

        String username=loginView.getUsernameBox().getText();
        char[] passwordChars = loginView.getPasswordBox().getPassword();
        String password = new String(passwordChars);

        String result=user.verifyUser(username,password);
        SignUpModel model=user.getPersonbyUsername(username);

        if(result.equals("User verified successfully.")) {
            if(model.getRoleID()==2){
                ShelterController controller=new ShelterController(model);
                controller.initializeAdminPage();
                loginView.setVisible(false);
            }else {
                HomePageController controllerHome = new HomePageController(model);
                controllerHome.initializeHomePage();
                loginView.setVisible(false);
            }
        }else {
        // Handle the case where the user is not verified (show an error message, etc.)
        JOptionPane.showMessageDialog(loginView, result, "Login Error", JOptionPane.ERROR_MESSAGE);
    }}

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }
}
