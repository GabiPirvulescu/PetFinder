package controller;

import model.BreedModel;
import model.SignUpModel;
import repository.Breed;
import view.AddBreed;
import view.InfoBreedsView;

import javax.swing.*;
import java.sql.SQLException;
import controller.CustomInfoBreedsController;

public class AddBreedController {
    AddBreed breedView;
    BreedModel breedModel;
    SignUpModel model;

    public AddBreedController(SignUpModel model) {
        this.model=model;
    }

    public void initializeBreedPage() {
        breedView = new AddBreed(this);
        //view.setVisible(true);
    }

    public void addButtonListener() throws SQLException {
        breedModel = new BreedModel();
        breedModel.setAnimalTypeID(breedView.getAnimalCategoryBox().getSelectedIndex() + 1);
        breedModel.setBreedName(breedView.getBreedNameBox().getText());
        breedModel.setDescription(breedView.getDescriptionBox().getText());

        String result = Breed.addBreed(breedModel);
        JLabel messageLabel;
        if ("Breed inserted.".equals(result)) {
            messageLabel = new JLabel("Breed inserted.");
        } else {
            messageLabel = new JLabel("Breed not inserted.");
        }
        JOptionPane.showMessageDialog(this.getBreedView(), messageLabel, "Add breed result", JOptionPane.INFORMATION_MESSAGE);
    }

    public AddBreed getBreedView() {
        return breedView;
    }

    public void setBreedView(AddBreed breedView) {
        this.breedView = breedView;
    }

    public void seeBreedListener() {
        CustomInfoBreedsController customInfoBreedsController = new CustomInfoBreedsController();
        customInfoBreedsController.backButtonListener(model);
        InfoBreedsView infoBreedsView = new InfoBreedsView(customInfoBreedsController,model);
        infoBreedsView.updateBreedsTable((String) breedView.getAnimalCategoryBox().getSelectedItem());
    };
}
