package repository;

import model.BreedModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Breed {

    public List<String> getBreedOptionsFromDatabase(String selectedCategory) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        ResultSet resultSet=null;
        List<String> breedOptions = new ArrayList<>();
        try {
            connection = getConnection();
            String query = "SELECT BreedName FROM Breed INNER JOIN AnimalType ON Breed.AnimalTypeID = AnimalType.AnimalTypeID WHERE AnimalType.AnimalType = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, selectedCategory);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String breedName = resultSet.getString("BreedName");
                breedOptions.add(breedName);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return breedOptions;
    }
    public int getTotalDogBreeds() throws SQLException {
        return getTotalBreedsForCategory("Dog");
    }

    public int getTotalCatBreeds() throws SQLException {
        return getTotalBreedsForCategory("Cat");
    }

    public int getTotalBirdBreeds() throws SQLException {
        return getTotalBreedsForCategory("Bird");
    }

    public int getTotalHorseBreeds() throws SQLException {
        return getTotalBreedsForCategory("Horse");
    }

    public int getTotalFishBreeds() throws SQLException {
        return getTotalBreedsForCategory("Fish");
    }

    public int getTotalRabbitBreeds() throws SQLException {
        return getTotalBreedsForCategory("Rabbit");
    }

    public int getTotalFurryBreeds() throws SQLException {
        return getTotalBreedsForCategory("Guinea Pig");
    }

    private int getTotalBreedsForCategory(String category) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalBreeds = 0;

        try {
            connection = getConnection();
            String query = "SELECT COUNT(*) as totalBreeds FROM Breed INNER JOIN AnimalType ON Breed.AnimalTypeID = AnimalType.AnimalTypeID WHERE AnimalType.AnimalType = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalBreeds = resultSet.getInt("totalBreeds");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return totalBreeds;
    }

    public String getBreedNameById(int breedId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String breedName = null;

        try {
            connection = getConnection();
            String query = "SELECT BreedName FROM Breed WHERE BreedID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, breedId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                breedName = resultSet.getString("BreedName");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return breedName;
    }

    public static List<BreedModel> getBreedsFromDatabase(int categoryID, String sortOrder) throws SQLException {
        List<BreedModel> breeds = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM Breed WHERE animaltypeid = ? ORDER BY BreedName " + sortOrder;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, categoryID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BreedModel breedModel = new BreedModel();
                breedModel.setBreedName(resultSet.getString("BreedName"));
                breedModel.setAnimalTypeID(resultSet.getInt("AnimalTypeID"));
                breedModel.setDescription(resultSet.getString("Description"));

                breeds.add(breedModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return breeds;
    }

    public static String addBreed(BreedModel model) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO Breed (BreedName, AnimalTypeID, Description) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, model.getBreedName());
            preparedStatement.setInt(2, model.getAnimalTypeID());
            preparedStatement.setString(3, model.getDescription());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "Breed inserted.";
            } else {
                return "Breed not inserted.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    public static int getBreedIndexByName(String breedName) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int breedIndex = -1; // Default value if not found

        try {
            connection = getConnection();
            String query = "SELECT BreedID FROM Breed WHERE BreedName = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, breedName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                breedIndex = resultSet.getInt("BreedID");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return breedIndex;
    }
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/PetFinder";
        String dbUsername = "postgres";
        String dbPassword = "Pcg14102003";

        return DriverManager.getConnection(url, dbUsername, dbPassword);
    }

}