package controller;

import model.AnimalModel;
import model.BreedModel;
import model.SignUpModel;
import repository.Animal;
import repository.Breed;
import view.SearchView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class SearchController {
    SearchView view;
    Animal animal;
    SignUpModel model;
    Breed breed;
    AnimalModel animalModel;

    public SearchController(SignUpModel model, Breed breed) {
        this.model = model;
        this.breed = new Breed();
    }

    public SignUpModel getModel() {
        return model;
    }

    public void setModel(SignUpModel model) {
        this.model = model;
    }

    public SearchView getView() {
        return view;
    }

    public void setView(SearchView view) {
        this.view = view;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void backButtonListener(){
        HomePageController controller=new HomePageController(model);
        controller.initializeHomePage();
        view.setVisible(false);
    }

    public void categoryBoxListener() throws SQLException {
        updateBreedOptions();
    }

    private void updateBreedOptions() throws SQLException {

        String selectedCategoryAnimal = (String) view.getCategoryBox().getSelectedItem();
        List<String> breedOptions = breed.getBreedOptionsFromDatabase(selectedCategoryAnimal);

        view.getBreedBox().removeAllItems();
        for (String breedOption : breedOptions) {
            view.getBreedBox().addItem(breedOption);
        }
    }
    public void searchButtonListener(SignUpModel model) throws SQLException {
        String category=(String) view.getCategoryBox().getSelectedItem();
        String city= view.getCityBox().getText();
        String breedSearch= view.getBreedBox().getSelectedItem().toString();

        List<AnimalModel> animals=Animal.getAnimalsFromDataBase(category,breedSearch,city);

        view.updateAnimalBoxes(animals,this,model);
    }

    public void favoriteButtonListener(AnimalModel animalModel,SignUpModel model ) {
        String result= Animal.addToFavorite(animalModel,model);
        JLabel messageLabel = new JLabel(result);
        JOptionPane.showMessageDialog(this.getView(), messageLabel, "Add animal to favorites result", JOptionPane.INFORMATION_MESSAGE);

    }

    public void requestButtonListener(AnimalModel animalModel,SignUpModel model ) {
    }
}
