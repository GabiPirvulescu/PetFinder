package repository;

import model.AnimalModel;
import model.BreedModel;
import model.LoginModel;
import model.SignUpModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String addUser(LoginModel model) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer rowsAffected = 0;
        SignUpModel signUpModel = new SignUpModel();
        try {
            connection = getConnection();
            String personIdQuery = "SELECT PersonID FROM Person WHERE Username = ?";


            preparedStatement = connection.prepareStatement(personIdQuery);
            preparedStatement.setString(1, model.getUsername());
            resultSet = preparedStatement.executeQuery();

            Integer personID = null;
            if (resultSet.next()) {
                personID = resultSet.getInt("PersonID");
            } else {
                return "Error: Person ID not found for the provided username.";
            }

            String userInsertQuery = "INSERT INTO \"User\" (Username, \"Password\", PersonID, RoleID) VALUES (?,?,?,?);";
            preparedStatement = connection.prepareStatement(userInsertQuery);

            preparedStatement.setString(1, model.getUsername());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setInt(3, personID);
            preparedStatement.setInt(4, model.getRoleID());

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        }finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "User successfully.";
    }

    public String verifyUser(String username, String password) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer rowsAffected = 0;
        try{
            connection=getConnection();
            String verifyQuery="SELECT Username, \"Password\" FROM \"User\"  WHERE Username = ?";

            preparedStatement=connection.prepareStatement(verifyQuery);
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                String storedPassword=resultSet.getString("Password");
                if (storedPassword.equals(password)) {
                    return "User verified successfully.";
                } else {
                    return "Error: Incorrect password.";
                }
            } else {
                return "Error: User not found.";
            }

        } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        return "Error at DB level";
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

    public SignUpModel getPersonbyUsername(String username) throws SQLException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        SignUpModel modelSignUp=new SignUpModel();
        try {
            connection = getConnection();
            String query = "SELECT * FROM Person p JOIN \"User\" u ON p.PersonID = u.PersonID WHERE u.Username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                modelSignUp.setFirstName(resultSet.getString("First_Name"));
                modelSignUp.setLastName(resultSet.getString("Last_Name"));
                modelSignUp.setEmail(resultSet.getString("Email"));
                modelSignUp.setPhone(resultSet.getString("Phone"));
                modelSignUp.setCity(resultSet.getString("City"));
                modelSignUp.setCounty(resultSet.getString("County"));
                modelSignUp.setBirth(resultSet.getDate("DateOfBirth").toLocalDate());
                modelSignUp.setGender(resultSet.getInt("GenderID"));
                modelSignUp.setRoleID(resultSet.getInt("RoleID"));
                modelSignUp.setUsername(resultSet.getString("Username"));
                modelSignUp.setPassword(resultSet.getString("Password"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return modelSignUp;
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/PetFinder";
        String dbUsername = "postgres";
        String dbPassword = "Pcg14102003";

        return DriverManager.getConnection(url, dbUsername, dbPassword);
    }
}
