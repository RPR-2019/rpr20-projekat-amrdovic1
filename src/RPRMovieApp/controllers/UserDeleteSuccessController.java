package RPRMovieApp.controllers;

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
