package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController
{
    public TextField email;
    public TextField username;
    public ButtonBar genders;
    public RadioButton male;
    public RadioButton female;
    public DatePicker dateOfBirth;
    public PasswordField password;
    public PasswordField repeatPassword;
    public Button cancel;
    public Button proceed;
    //General error text: This field is mandatory
    public Label emailError; //Texts: The email entered isn't of valid format; There already is a user with this email
    public Label usernameError; //Text: The username already exists
    public Label gendersError;
    public Label dateError; //Text: User should be at least 15 years old
    public Label passwordError; //Text: Password is too short; Password doesn't contain all types of characters required
    public Label repeatPasswordError; //Text: Passwords do not match
    private PreparedStatement sameUsernameCheck;
    private PreparedStatement sameEMail;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = ""; //Put path here
//        Connection conn = DriverManager.getConnection(url, "username", "password");
//        sameUsernameCheck = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE username=?");
//        sameEMail = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE email=?");
        email.textProperty().addListener((observableValue, o, n) -> {

        });
    }

    public void cancelClick(ActionEvent actionEvent)
    {

    }

    public void proceedClick(ActionEvent actionEvent) throws IOException
    {
        if (!(male.isSelected() || female.isSelected()))
        {
            gendersError.setText("You must select a gender!");
        }
        if (!(password.getText().equals(repeatPassword.getText())))
        {
            repeatPasswordError.setText("Passwords do not match!");
        }
        //If everything is ok, do this:
        //Close register.fxml
//        Stage hpstage = new Stage();
//        FXMLLoader hploader = new FXMLLoader(getClass().getResource("homepage.fxml"));
//        Parent hproot = hploader.load();
//        HomepageController hpc = hploader.getController();
//        hpstage.setScene(new Scene(hproot, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
//        hpstage.show();
        //To be continued...
    }
}
