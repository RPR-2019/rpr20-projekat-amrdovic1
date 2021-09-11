package RPRMovieApp.controllers.admin.adminusers;

import RPRMovieApp.controllers.ChosenUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserDeleteSuccessController
{
    public Label userField;

    @FXML
    public void initialize()
    {
        userField.setText(userField.getText() + " " + ChosenUser.getChosen() + " ");
        ChosenUser.setChosen(null);
    }
}
