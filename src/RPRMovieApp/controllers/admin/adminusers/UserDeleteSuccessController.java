package RPRMovieApp.controllers.admin.adminusers;

import RPRMovieApp.CurrentData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserDeleteSuccessController
{
    public Label userField;

    @FXML
    public void initialize()
    {
        userField.setText(userField.getText() + " " + CurrentData.getCurrentUser().getUsername() + " ");
        CurrentData.setCurrentSelectedUser(null);
    }
}
