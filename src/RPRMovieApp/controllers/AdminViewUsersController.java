package RPRMovieApp.controllers;

import RPRMovieApp.models.User;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminViewUsersController
{
    public Button findUserBtn;
    public ListView<User> userList;

    private Connection conn;
    private PreparedStatement getAllUsers;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        getAllUsers = conn.prepareStatement("SELECT * FROM user WHERE username != 'admin' ORDER BY username");
        ResultSet gaurs = getAllUsers.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (gaurs.next())
        {
            users.add(new User(gaurs.getInt(1), gaurs.getString(2), gaurs.getString(3)));
        }
        userList.setItems(FXCollections.observableArrayList(users));
        userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                ChosenUser.setChosen(userList.getSelectionModel().getSelectedItem());
                Stage viewUserDetailsStage = new Stage();
                FXMLLoader userDetailLoader = new FXMLLoader(getClass().getResource("/fxml/userDetail.fxml")); //This path is temporary
                Parent root;
                try
                {
                    root = userDetailLoader.load();
                    viewUserDetailsStage.setTitle("View user details");
                    viewUserDetailsStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    viewUserDetailsStage.setResizable(false);
                    viewUserDetailsStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
