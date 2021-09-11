package RPRMovieApp.controllers.admin.adminusers;

import RPRMovieApp.controllers.ChosenUser;
import RPRMovieApp.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserDetailController
{
    public Label mainMessage;
    public Button deleteUserBtn;
    public TextArea userInfo;

    private User user;

    @FXML
    public void initialize()
    {
        user = ChosenUser.getChosen();
        user.setMode(true);
        mainMessage.setText(mainMessage.getText() + " " + user.getUsername());
        userInfo.setText(user.toString());
    }

    public void deleteUser(ActionEvent actionEvent) throws IOException {
        Stage deleteUserStage = new Stage();
        FXMLLoader deleteUserLoader = new FXMLLoader(getClass().getResource("/fxml/userDeleteWarning.fxml")); //This path is temporary
        Parent root = deleteUserLoader.load();
        deleteUserStage.setTitle("Warning!");
        deleteUserStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        deleteUserStage.setResizable(false);
        deleteUserStage.show();
    }
}
