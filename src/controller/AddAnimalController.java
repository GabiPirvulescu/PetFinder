package controller;

import java.sql.SQLException;
import java.util.List;

import model.AddAnimalModel;
import model.SignUpModel;
import repository.Animal;
import repository.Breed;
import view.AddAnimalView;

import javax.swing.*;

public class AddAnimalController {
    AddAnimalView viewAdd;
    SignUpModel model;
    AddAnimalModel modelAdd;
    Breed breed = new Breed();

    public AddAnimalController(SignUpModel model) {
        this.model = model;
    }

    public void setModel(SignUpModel model) {
        this.model = model;
    }

    public AddAnimalView getViewAdd() {
        return viewAdd;
    }

    public void setViewAdd(AddAnimalView viewAdd) {
        this.viewAdd = viewAdd;
    }

    public void categoryBoxListener() throws SQLException {
        updateBreedOptions();
    }

    private void updateBreedOptions() throws SQLException {
        String selectedCategory = (String) viewAdd.getCategoryBox().getSelectedItem();
        List<String> breedOptions = breed.getBreedOptionsFromDatabase(selectedCategory);

        viewAdd.getBreedBox().removeAllItems();

        for (String breedOption : breedOptions) {
            viewAdd.getBreedBox().addItem(breedOption);
        }
    }

    public void backButtonListener() {
        HomePageController controller = new HomePageController(model);
        controller.initializeHomePage();
        viewAdd.setVisible(false);
    }

    public void addButtonListener() throws SQLException {
        modelAdd = new AddAnimalModel();
        modelAdd.setName(viewAdd.getNameBox().getText());
        modelAdd.setAnimalType(viewAdd.getCategoryBox().getSelectedIndex() + 1);
        modelAdd.setGender(viewAdd.getGenderBox().getSelectedIndex() + 1);
        modelAdd.setSize(viewAdd.getSizeBox().getSelectedIndex() + 1);
        modelAdd.setColor(viewAdd.getColorBox().getSelectedIndex() + 1);
        modelAdd.setDescription(viewAdd.getDescriptionBox().getText());
        modelAdd.setPhotoURL(viewAdd.getPhotoBox().getText());

//        int breedIndex= viewAdd.getBreedBox().getSelectedIndex()+1;
//        String selectedCategory = (String) viewAdd.getCategoryBox().getSelectedItem();
//
//        if (selectedCategory.equals("Dog")) {
//            breedIndex = viewAdd.getBreedBox().getSelectedIndex()+1;
//        } else if (selectedCategory.equals("Cat")) {
//            breedIndex += breed.getTotalDogBreeds();
//        } else if (selectedCategory.equals("Bird")) {
//            breedIndex += breed.getTotalCatBreeds()+breed.getTotalDogBreeds();
//        } else if (selectedCategory.equals("Small and furry pet")) {
//            breedIndex += breed.getTotalBirdBreeds()+breed.getTotalCatBreeds()+breed.getTotalDogBreeds();
//        } else if (selectedCategory.equals("Rabbit")) {
//            breedIndex += breed.getTotalFurryBreeds()+breed.getTotalBirdBreeds()+breed.getTotalCatBreeds()+breed.getTotalDogBreeds();
//        } else if (selectedCategory.equals("Horse")) {
//            breedIndex += breed.getTotalRabbitBreeds()+breed.getTotalFurryBreeds()+breed.getTotalBirdBreeds()+breed.getTotalCatBreeds()+breed.getTotalDogBreeds();
//        } else if (selectedCategory.equals("Fish")) {
//            breedIndex += breed.getTotalHorseBreeds()+breed.getTotalRabbitBreeds()+breed.getTotalFurryBreeds()+breed.getTotalBirdBreeds()+breed.getTotalCatBreeds()+breed.getTotalDogBreeds();
//        }

        String breed=viewAdd.getBreedBox().getSelectedItem().toString();
        int breedIndex=Breed.getBreedIndexByName(breed);

        modelAdd.setBreed(breedIndex);
        modelAdd.setAge(viewAdd.getAgeBox().getSelectedIndex() + 1);
        modelAdd.setShelter(0);
        modelAdd.setCity(viewAdd.getCityBox().getText());
        modelAdd.setCounty(viewAdd.getCountyBox().getSelectedItem().toString());
        modelAdd.setStreet(viewAdd.getStreetBox().getText());
        modelAdd.setNr(Integer.parseInt(viewAdd.getNrBox().getText()));

        String resultMessage = Animal.addAnimal(modelAdd, model);
        JLabel messageLabel = new JLabel(resultMessage);
        JOptionPane.showMessageDialog(this.getViewAdd(), messageLabel, "Add animal result", JOptionPane.INFORMATION_MESSAGE);
    }
}
