package controller;

import model.BreedModel;
import model.SignUpModel;
import view.AddBreed;
import view.ShelterView;

public class ShelterController {
    ShelterView viewAdmin;
    SignUpModel model;
    BreedModel breedModel;
    AddBreed breedView;

    public ShelterController(SignUpModel model) {
        this.model = model;
    }

    public void initializeAdminPage(){
        viewAdmin=new ShelterView(this,model);
        //view.setVisible(true);
    }

    public ShelterView getViewAdmin() {
        return viewAdmin;
    }

    public void backButtonListener(SignUpModel model){
        viewAdmin.setVisible(true);
    }


    public void addBreedListener(SignUpModel model){
        AddBreedController controller=new AddBreedController(model);
        viewAdmin.setVisible(false);
        controller.initializeBreedPage();
    }
    public void setViewAdmin(ShelterView viewAdmin) {
        this.viewAdmin = viewAdmin;
    }

}
