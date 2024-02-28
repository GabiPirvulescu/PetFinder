package controller;

import model.SignUpModel;
import repository.Breed;
import view.*;

import java.io.IOException;
import java.sql.SQLException;

public class HomePageController {

    public HomePageView viewHome;
    SignUpModel model;
    Breed breed=new Breed();

    public HomePageController(SignUpModel model) {
        this.model = model;
    }

    public void initializeHomePage(){
        viewHome=new HomePageView(this,model);
        //view.setVisible(true);
    }
    public void accountListener() throws SQLException, IOException {
        AccountController controller=new AccountController(model);
        AccountView view=new AccountView(controller,model);
        controller.setView(view);
    }

    public void addAnimalListener(){
        AddAnimalController controller=new AddAnimalController(model);
        AddAnimalView view=new AddAnimalView(controller,model);
        controller.setViewAdd(view);
        viewHome.setVisible(false);
    }

    public void searchPetListener(){
        SearchController searchController=new SearchController(model,breed);
        SearchView searchView=new SearchView(searchController,model);
       searchController.setView(searchView);
        viewHome.setVisible(false);
    }

    public HomePageView getView() {
        return viewHome;
    }

    public void setView(HomePageView view) {
        this.viewHome = view;
    }

    public void breedsListener(SignUpModel model) {
        InfoBreedsController controller=new InfoBreedsController();
        InfoBreedsView view=new InfoBreedsView(controller,model);
        controller.setView(view);
        viewHome.setVisible(false);
    }

}
