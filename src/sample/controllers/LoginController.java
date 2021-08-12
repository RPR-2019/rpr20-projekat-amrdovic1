package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController
{
    public TextField usernameOrEMail;
    public PasswordField password;
    public Label passwordError;
    private PreparedStatement checkExistsUsername;
    private PreparedStatement checkPassword;
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url, "username", "password");
        checkExistsUsername = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE username=?");
        checkPassword = conn.prepareStatement("SELECT password FROM user WHERE username=?");
    }

    public void goBackClick (ActionEvent actionEvent)
    {
        Node n = (Node) actionEvent.getSource();
        Stage loginStage = (Stage) n.getScene().getWindow();
        loginStage.close();
    }

    public void loginClick(ActionEvent actionEvent) throws SQLException, IOException {
        checkExistsUsername.setString(1, usernameOrEMail.getText());
        checkPassword.setString(1, usernameOrEMail.getText());
        ResultSet ceurs = checkExistsUsername.executeQuery();
        ResultSet cprs = checkPassword.executeQuery();
        if (ceurs.getInt(1) == 0 || !(cprs.getString(1).equals(password.getText())))
        {
            passwordError.setText("Login failed. Check your username and password!");
        }
        else
        {
            Node n = (Node) actionEvent.getSource();
            Stage loginStage = (Stage) n.getScene().getWindow();
            loginStage.close();

            Stage homeStage = new Stage();
            FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("../homepage.fxml")); //This path is temporary
            Parent root = homePageLoader.load();
            HomepageController rc = homePageLoader.getController();
            homeStage.setTitle("Homepage");
            homeStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            homeStage.setResizable(false);
            homeStage.show();
            rc.welcomeMessage.setText(rc.welcomeMessage.getText() + usernameOrEMail.getText()); //This is not how it will be done in the final project
        }
    }
}
