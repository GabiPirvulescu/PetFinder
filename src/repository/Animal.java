package repository;

import model.AddAnimalModel;
import model.AnimalModel;
import model.SignUpModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Animal {

    public static String addAnimal(AddAnimalModel model, SignUpModel modelSignUp) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        int userID = 0, animalID = 0;
        try {
            connection=getConnection();
            if (isAnimalNameExists(connection, model.getName())) {
                return "Animal with the same name already exists.";
            }
            connection = getConnection();
            String queryUser = "SELECT UserID, \"Password\" FROM \"User\"  WHERE Username = ?;";
            preparedStatement1 = connection.prepareStatement(queryUser);
            preparedStatement1.setString(1, modelSignUp.getUsername());
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (resultSet1.next()) {
                userID = resultSet1.getInt("UserID");
            } else {
                return "User not found";
            }

            String query = "INSERT INTO Animal (AnimalName, AnimalTypeID, GenderID, SizeID, AgeID, BreedID, ColorID, ShelterID, Description, PhotoURL) VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING AnimalID;";
            preparedStatement2 = connection.prepareStatement(query);
            preparedStatement2.setString(1, model.getName());
            preparedStatement2.setInt(2, model.getAnimalType());
            preparedStatement2.setInt(3, model.getGender());
            preparedStatement2.setInt(4, model.getSize());
            preparedStatement2.setInt(5, model.getAge());
            preparedStatement2.setInt(6, model.getBreed());
            preparedStatement2.setInt(7, model.getColor());
            if (model.getShelter() == 0)
                preparedStatement2.setNull(8, java.sql.Types.INTEGER);
            preparedStatement2.setString(9, model.getDescription());
            preparedStatement2.setString(10, model.getPhotoURL());

            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {
                animalID = resultSet2.getInt("AnimalID");
            } else {
                return "Animal not inserted";
            }

            String query3 = "INSERT INTO FoundAnimals (UserID, AnimalID, FoundDate, City, County, Street, Nr) VALUES (?, ?, current_date, ?, ?, ?, ?);";
            PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
            preparedStatement3.setInt(1, userID);
            preparedStatement3.setInt(2, animalID);
            preparedStatement3.setString(3, model.getCity());
            preparedStatement3.setString(4, model.getCounty());
            preparedStatement3.setString(5, model.getStreet());
            preparedStatement3.setInt(6, model.getNr());
            int rowsAffected3 = preparedStatement3.executeUpdate();

            if (rowsAffected3 > 0) {
                return "Animal added successfully.";
            } else {
                return "Error adding animal.";
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            closeResources(connection, preparedStatement1, preparedStatement2);
        }
    }
    private static boolean isAnimalNameExists(Connection connection, String animalName) throws SQLException {
        String query = "SELECT COUNT(*) FROM Animal WHERE AnimalName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, animalName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }


    public static String addToFavorite(AnimalModel animalModel, SignUpModel model) {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;

        int userID = 0, animalID = 0, shelterID = 0;
        try {
            connection = getConnection();
            String queryUser = "SELECT UserID, RoleID FROM \"User\"  WHERE Username = ?;";
            preparedStatement1 = connection.prepareStatement(queryUser);
            preparedStatement1.setString(1, model.getUsername());
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            if (resultSet1.next()) {
                userID = resultSet1.getInt("UserID");

            } else {
                return "User not found";
            }

            String queryAnimal = "SELECT AnimalID, shelterid from Animal Where AnimalName = ?;";
            preparedStatement2 = connection.prepareStatement(queryAnimal);
            preparedStatement2.setString(1, animalModel.getName());
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            if (resultSet2.next()) {
                animalID = resultSet2.getInt("AnimalID");
                shelterID = resultSet2.getInt("ShelterID");
            } else {
                return "Animal not found";
            }

            String query = "INSERT INTO Favorites (UserID, AnimalID, ShelterID) VALUES (?,?,?);";
            preparedStatement3 = connection.prepareStatement(query);
            preparedStatement3.setInt(1, userID);
            preparedStatement3.setInt(2, animalID);
            if (shelterID == 0)
                preparedStatement3.setNull(3, shelterID);
            else
                preparedStatement3.setInt(3, shelterID);

            int rowsAffected = preparedStatement3.executeUpdate();

            if (rowsAffected > 0) {
                return "Animal added to your favorites";
            } else {
                return "Error at DB Level. Animal not added.";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources(connection, preparedStatement1, preparedStatement2, preparedStatement3);
        }
    }

    public String deleteAnimal(String animalName){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection=getConnection();
            String deleteProcedureCall="CALL DeleteAnimalByName(?,?);";
            callableStatement=connection.prepareCall(deleteProcedureCall);
            callableStatement.setString(1,animalName);
            callableStatement.registerOutParameter(2, Types.BOOLEAN); // OUT parameter for success flag
            callableStatement.execute();

            boolean success = callableStatement.getBoolean(2);
            if(success==true) return "Animal deleted successfully";
             else return "Error when deleting.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void closeResources(Connection connection, PreparedStatement... statements) {
        try {
            for (PreparedStatement statement : statements) {
                if (statement != null) statement.close();
            }
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<AnimalModel> getAnimalDetails(String username) throws SQLException {
        List<AnimalModel> animalList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM AnimalDetails WHERE Username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnimalModel model = new AnimalModel();
                model.setAnimalID(resultSet.getInt("AnimalID"));
                model.setName(resultSet.getString("AnimalName"));
                model.setAnimalType(resultSet.getString("AnimalType"));
                model.setGender(resultSet.getString("Gender"));
                model.setSize(resultSet.getString("Size"));
                model.setAge(resultSet.getString("Age"));
                model.setBreed(resultSet.getString("Breed"));
                model.setColor(resultSet.getString("Color"));
                model.setDescription(resultSet.getString("Description"));
                model.setUrl(resultSet.getString("PhotoURL"));
                model.setFoundDate(resultSet.getDate("FoundDate").toLocalDate());

                animalList.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }

        return animalList;
    }

    public static List<AnimalModel> getAnimalsFromDataBase(String category, String breed, String city) throws SQLException{
        List<AnimalModel> animalList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM AnimalDetails WHERE AnimalType = ? and Breed = ? and City = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);
            preparedStatement.setString(2,breed);
            preparedStatement.setString(3,city);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnimalModel model = new AnimalModel();
                model.setAnimalID(resultSet.getInt("AnimalID"));
                model.setName(resultSet.getString("AnimalName"));
                model.setAnimalType(resultSet.getString("AnimalType"));
                model.setGender(resultSet.getString("Gender"));
                model.setSize(resultSet.getString("Size"));
                model.setAge(resultSet.getString("Age"));
                model.setBreed(resultSet.getString("Breed"));
                model.setColor(resultSet.getString("Color"));
                model.setDescription(resultSet.getString("Description"));
                model.setUrl(resultSet.getString("PhotoURL"));
                model.setFoundDate(resultSet.getDate("FoundDate").toLocalDate());

                animalList.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement);
        }

        return animalList;
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/PetFinder";
        String dbUsername = "postgres";
        String dbPassword = "Pcg14102003";

        return DriverManager.getConnection(url, dbUsername, dbPassword);
    }

}
