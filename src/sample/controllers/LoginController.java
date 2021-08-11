package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;

public class LoginController
{
    public TextField usernameOrEMail;
    public PasswordField password;
    private PreparedStatement checkExistsUsername;
    private PreparedStatement checkPassword;
    @FXML
    public void initialize()
    {

    }
}
