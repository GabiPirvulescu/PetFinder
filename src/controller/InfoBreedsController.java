package controller;

import model.AnimalModel;
import model.SignUpModel;
import repository.Animal;
import view.InfoBreedsView;

public class InfoBreedsController {
    InfoBreedsView view;
    AnimalModel model;
    SignUpModel signUpModel;
    public InfoBreedsController() {
        view = new InfoBreedsView(this,signUpModel);
    }

    public InfoBreedsView getView() {
        return view;
    }
    public void backButtonListener(SignUpModel signUpModel) {
        HomePageController controller = new HomePageController(signUpModel);
        controller.initializeHomePage();
       view.dispose();
    }

    public void setView(InfoBreedsView view) {
        this.view = view;
    }

    public AnimalModel getModel() {
        return model;
    }

    public void setModel(AnimalModel model) {
        this.model = model;
    }
}
