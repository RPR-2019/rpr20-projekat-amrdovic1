package RPRMovieApp.controllers;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.User;
import RPRMovieApp.controllers.admin.home.AdminHomepageController;
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
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController
{
    public TextField username;
    public PasswordField password;
    public Label passwordError;
    private CinemaDAO cDAO;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        cDAO = CinemaDAO.getInstance();
    }

    public void goBackClick (ActionEvent actionEvent)
    {
        Node n = (Node) actionEvent.getSource();
        Stage loginStage = (Stage) n.getScene().getWindow();
        loginStage.close();
    }

    public void loginClick(ActionEvent actionEvent) throws SQLException, IOException {

        if (!cDAO.checkPasswordForUser(username.getText(), password.getText()))
        {
            passwordError.setText("Login failed!\nPlease check your username and password!");
        }
        else
        {
            User u = cDAO.getUser(username.getText());
            CurrentData.setCurrentUser(u);
            CinemaDAO.removeInstance();
            Node n = (Node) actionEvent.getSource();
            Stage loginStage = (Stage) n.getScene().getWindow();
            loginStage.close();

            Stage homeStage = new Stage();
            FXMLLoader homePageLoader;
            if (username.getText().equals("admin"))
            {
                homePageLoader = new FXMLLoader(getClass().getResource("/fxml/admin/adminHomepage.fxml"));
                Parent root = homePageLoader.load();
                AdminHomepageController rc = homePageLoader.getController();
                homeStage.setTitle("Homepage");
                homeStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                homeStage.setResizable(false);
                homeStage.show();
                rc.welcomeMessage.setText(rc.welcomeMessage.getText() + username.getText()); //This is not how it will be done in the final project
            }
            else
            {
                homePageLoader = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"));
                Parent root = homePageLoader.load();
                HomepageController rc = homePageLoader.getController();
                homeStage.setTitle("Homepage");
                homeStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                homeStage.setResizable(false);
                homeStage.show();
                rc.welcomeMessage.setText(rc.welcomeMessage.getText() + username.getText()); //This is not how it will be done in the final project
            }

        }
    }
}
