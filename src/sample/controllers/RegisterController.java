package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.PreparedStatement;

public class RegisterController
{
    public TextField email;
    public TextField username;
    public ButtonBar genders;
    public DatePicker dateofbirth;
    public PasswordField password;
    public PasswordField repeatpassword;
    public Button cancel;
    public Button proceed;
    private PreparedStatement sameUsernameCheck;

    @FXML
    public void initialize()
    {
        email = new TextField();
        email.textProperty().addListener((observableValue, o, n) -> {

        });
    }

    public void cancelClick(ActionEvent actionEvent)
    {

    }

    public void proceedClick(ActionEvent actionEvent)
    {
        if (!(password.getText().equals(repeatpassword.getText())))
        {
            //Do some stuff
        }
    }
}
