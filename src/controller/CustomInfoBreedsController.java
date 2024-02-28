package controller;

import model.SignUpModel;

public class CustomInfoBreedsController extends InfoBreedsController {

    @Override
    public void backButtonListener(SignUpModel signUpModel) {
        ShelterController shelterController = new ShelterController(signUpModel);
        shelterController.initializeAdminPage();
    }
}