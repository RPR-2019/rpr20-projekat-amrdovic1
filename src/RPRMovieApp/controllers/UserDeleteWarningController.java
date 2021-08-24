package RPRMovieApp.controllers;

import RPRMovieApp.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class UserDeleteWarningController
{
    private User user;

    public Label warningMessage;
    public Button cancelBtn;
    public Button deleteUser;

    private Connection conn;
    private PreparedStatement deleteUserStatement;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        user = ChosenUser.getChosen();
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        deleteUserStatement = conn.prepareStatement("DELETE FROM user WHERE username=?");
        deleteUserStatement.setString(1, user.getUsername());
        warningMessage.setText(warningMessage.getText() + " " + user.getUsername());
    }

    public void deleteUserClick(ActionEvent actionEvent) throws SQLException, IOException {
        deleteUserStatement.execute();
        Stage userDeleteSuccessStage = new Stage();
        FXMLLoader userDeleteSuccessLoader = new FXMLLoader(getClass().getResource("/fxml/userDeleteSuccess.fxml")); //This path is temporary
        Parent root = userDeleteSuccessLoader.load();
        userDeleteSuccessStage.setTitle("User deletion successful!");
        userDeleteSuccessStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        userDeleteSuccessStage.setResizable(false);
        userDeleteSuccessStage.show();
    }
}
