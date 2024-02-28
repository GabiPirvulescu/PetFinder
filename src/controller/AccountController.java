package controller;

import model.AnimalModel;
import model.SignUpModel;
import repository.Animal;
import repository.Person;
import view.AccountView;
import view.HomePageView;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class AccountController {
    AccountView view;
    private SignUpModel model;
    Animal animal=new Animal();
    Person person=new Person();

    public AccountController(SignUpModel model) {
        this.model = model;
    }

    public void initializeAccountView() throws SQLException, IOException {
        view=new AccountView(this,model);
        //view.setVisible(true);
    }

    public void backButtonListener(){
        HomePageController controller=new HomePageController(model);
        controller.initializeHomePage();
        view.setVisible(false);
    }

    public AccountView getView() {
        return view;
    }

    public void setView(AccountView view) {
        this.view = view;
    }

    public void deleteButtonListener(AnimalModel animalModel) {
       String result= animal.deleteAnimal(animalModel.getName());
       JLabel messageLabel = new JLabel(result);
       JOptionPane.showMessageDialog(this.getView(), messageLabel, "Delete animal", JOptionPane.INFORMATION_MESSAGE);

    }

    public void updateListener(SignUpModel model) {
        String newUsername = JOptionPane.showInputDialog(view, "Enter new username:");

        if (newUsername != null) {
            boolean success = Person.updateUsername(model.getUsername(), newUsername,this,model);

            if (success) {
                model.setUsername(newUsername);
                refreshView();
                JOptionPane.showMessageDialog(view, "Username updated successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update username. Please try again.");
            }
        }
    }

    public void refreshView() {
        try {
            view.refresh(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
