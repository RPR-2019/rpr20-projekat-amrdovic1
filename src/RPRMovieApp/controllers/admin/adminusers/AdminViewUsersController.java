package RPRMovieApp.controllers.admin.adminusers;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.User;
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
    public ArrayList<User> users;
    public ListView<User> userList;

    private CinemaDAO cDAO;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        cDAO = CinemaDAO.getInstance();
        users = cDAO.getAllUsers();
        userList.setItems(FXCollections.observableArrayList(users));
        userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                CurrentData.setCurrentSelectedUser(userList.getSelectionModel().getSelectedItem());
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
        CinemaDAO.removeInstance();
    }
}
