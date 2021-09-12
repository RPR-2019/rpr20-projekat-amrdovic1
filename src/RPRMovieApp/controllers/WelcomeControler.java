package RPRMovieApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class WelcomeControler
{
    public Button login;
    public Button signup;

    @FXML
    public void initialize()
    {

    }

    public void loginClick(ActionEvent actionEvent) throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml")); //This path is temporary
        loginStage.setTitle("Log In");
        loginStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        loginStage.setResizable(false);
        loginStage.show();
    }

    public void signupClick(ActionEvent actionEvent) throws IOException {
        Stage registerStage = new Stage();
        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("/fxml/user/registration/register.fxml")); //This path is temporary
        Parent root = signUpLoader.load();
        registerStage.setTitle("Register as a new user");
        registerStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        registerStage.setResizable(false);
        registerStage.show();
    }
}
