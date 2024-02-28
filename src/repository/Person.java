package repository;

import controller.AccountController;
import model.SignUpModel;
import view.AccountView;

import javax.swing.*;
import java.sql.*;

import static repository.Animal.closeResources;

public class Person {

    public static boolean updateUsername(String oldUsername, String newUsername, AccountController controller,SignUpModel model) {
        PreparedStatement updateUserStatement=null;
        PreparedStatement updatePersonStatement=null;
        Connection connection = null;


        try {
            connection = getConnection();
            String updateUserQuery = "UPDATE \"User\" SET Username = ? WHERE Username = ?";
            updateUserStatement = connection.prepareStatement(updateUserQuery);
                updateUserStatement.setString(1, newUsername);
                updateUserStatement.setString(2, oldUsername);
                updateUserStatement.executeUpdate();

            String updatePersonQuery = "UPDATE Person SET Username = ? WHERE Username = ?";
           updatePersonStatement = connection.prepareStatement(updatePersonQuery);
           updatePersonStatement.setString(1, newUsername);
           updatePersonStatement.setString(2, oldUsername);
           updatePersonStatement.executeUpdate();

            model.setUsername(newUsername);
            controller.refreshView();
            JOptionPane.showMessageDialog(controller.getView(), "Username updated successfully!");
            return true;
        } catch (SQLException e) {
            // Handle SQL exception, rollback the transaction if needed
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, null, null);
        }
    }

    public String addAccount(SignUpModel model) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            connection = getConnection();
            String query = "INSERT INTO Person (First_Name, Last_Name, GenderID, DateOfBirth, Phone, Email, City, County, \"Password\", RoleID,Username) VALUES (?,?,?,?,?,?,?,?,?,?,?);";


            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setInt(3, model.getGender());
            preparedStatement.setDate(4, java.sql.Date.valueOf(model.getBirth()));
            preparedStatement.setString(5, model.getPhone());
            preparedStatement.setString(6, model.getEmail());
            preparedStatement.setString(7,model.getCity());
            preparedStatement.setString(8, model.getCounty());
            preparedStatement.setString(9, model.getPassword());
            preparedStatement.setInt(10,model.getRoleID());
            preparedStatement.setString(11, model.getUsername());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            if ("23505".equals(sqlException.getSQLState())) {
                // Unique constraint violation (duplicate key)
                if (sqlException.getMessage().contains("unique constraint \"person_email_key\"")) {
                    return "Error: Email already exists. Please choose a different email.";
                } else if (sqlException.getMessage().contains("unique constraint \"person_phone_key\"")) {
                    return "Error: Phone already exists. Please choose a different phone number.";
                } else if (sqlException.getMessage().contains("unique constraint \"person_username_key\"")) {
                    return "Error: Username already exists. Please choose a different username.";
                } else {
                    return "Error: Duplicate key violation. Please check your data.";
                }
            } else {
                sqlException.printStackTrace();
                return "Error at DB level";
            }
        }
        {
            return "Account created successfully.";

        }
    }
    private static Connection getConnection()throws SQLException{
        String url= "jdbc:postgresql://localhost:5432/PetFinder";
        String dbUsername="postgres";
        String dbPassword="Pcg14102003";

        return DriverManager.getConnection(url,dbUsername,dbPassword);
    }
}
