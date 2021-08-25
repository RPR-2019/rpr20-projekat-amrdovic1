package RPRMovieApp.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegisterController
{
    private Connection conn;
    public TextField email;
    public TextField username;
    public ButtonBar genders;
    public RadioButton male;
    public RadioButton female;
    public ChoiceBox selectDay;
    public ChoiceBox selectMonth;
    public ChoiceBox selectYear;
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
    private PreparedStatement sameEMailCheck;
    private PreparedStatement registerNewUser;
    private PreparedStatement getMaxID;
    private int days[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String mandatory = "This is a mandatory field!";

    private int isLeap (int year)
    {
        if (year%400 == 0)
        {
            return 1;
        }
        if (year%100 == 0)
        {
            return 0;
        }
        if (year%4 == 0)
        {
            return 1;
        }
        return 0;
    }

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        sameUsernameCheck = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE username=?");
        sameEMailCheck = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE email=?");
        registerNewUser = conn.prepareStatement("INSERT INTO user VALUES(?,?,?,?)");
        getMaxID = conn.prepareStatement("SELECT MAX(id) FROM user");
        for (int i = 1; i <= 31; i++)
        {
            selectDay.getItems().add(i);
        }
        selectDay.setValue(1);
        for (int i = 1; i <= 12; i++)
        {
            selectMonth.getItems().add(i);
        }
        selectMonth.setValue(1);
        for (int i = 2021; i >= 1900; i--)
        {
            selectYear.getItems().add(i);
        }
        selectYear.setValue(1900);
        selectMonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                int no_of_days = days[(Integer) selectMonth.getValue()] + isLeap((Integer) selectYear.getValue());
                selectDay.setItems(FXCollections.observableArrayList());
                for (int i = 1; i <= no_of_days; i++)
                {
                    selectDay.getItems().add(i);
                }
                selectDay.setValue(1);
            }
        });
        selectYear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isLeap((Integer) selectYear.getValue()) == 1)
                {
                    if (!selectDay.getItems().contains(29))
                    {
                        selectDay.getItems().add(29);
                    }
                }
                else if (isLeap((Integer) selectYear.getValue()) == 0)
                {
                    if (selectDay.getItems().contains(29))
                    {
                        selectDay.getItems().remove(28);
                    }
                }
            }
        });
    }

    public void cancelClick(ActionEvent actionEvent)
    {
        Node n = (Node) actionEvent.getSource();
        Stage registerStage = (Stage) n.getScene().getWindow();
        registerStage.close();
    }

    public void proceedClick(ActionEvent actionEvent) throws IOException, SQLException {
        boolean error = false;
        if (email.getText().isBlank())
        {
            emailError.setText(mandatory);
            error = true;
        }
        else
        {
            emailError.setText("");
        }
        if (username.getText().isBlank())
        {
            usernameError.setText(mandatory);
            error = true;
        }
        else
        {
            usernameError.setText("");
        }
        if (!(male.isSelected() || female.isSelected()))
        {
            gendersError.setText(mandatory);
            error = true;
        }
        else
        {
            gendersError.setText("");
        }
        if (selectDay.getValue() == null || selectMonth.getValue() == null || selectYear.getValue() == null)
        {
            dateError.setText(mandatory);
            error = true;
        }
        else
        {
            dateError.setText("");
        }
        if (password.getText().trim().length() < 6)
        {
            passwordError.setText("Password must contain at least 6 non-blank characters!");
            error = true;
        }
        else
        {
            passwordError.setText("");
        }
        if (!(password.getText().equals(repeatPassword.getText())))
        {
            repeatPasswordError.setText("Passwords do not match!");
            error = true;
        }
        else
        {
            repeatPasswordError.setText("");
        }
        sameUsernameCheck.setString(1, username.getText());
        sameEMailCheck.setString(1, email.getText());
        ResultSet surs = sameUsernameCheck.executeQuery();
        ResultSet semrs = sameEMailCheck.executeQuery();
        if (surs.getInt(1) != 0)
        {
            usernameError.setText("User with username " + username.getText() + "already exists!");
            error = true;
        }
        if (semrs.getInt(1) != 0)
        {
            emailError.setText("User with email " + email.getText() + " already exists!");
            error = true;
        }
        if (!error)
        {
            ResultSet getmaxidrs = getMaxID.executeQuery();
            int new_id = getmaxidrs.getInt(1) + 1;
            registerNewUser.setInt(1, new_id);
            registerNewUser.setString(2, username.getText());
            registerNewUser.setString(3, email.getText());
            registerNewUser.setString(4, password.getText());
            registerNewUser.execute();
            Node n = (Node) actionEvent.getSource();
            Stage registerStage = (Stage) n.getScene().getWindow();
            registerStage.close();
            conn.close();

            Stage regSuccessStage = new Stage();
            FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("/fxml/registerSuccess.fxml")); //This path is temporary
            Parent root = signUpLoader.load();
            RegisterController rc = signUpLoader.getController();
            regSuccessStage.setTitle("Registration successful!");
            regSuccessStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            regSuccessStage.setResizable(false);
            regSuccessStage.show();
        }
    }

    public void selectMale(ActionEvent actionEvent)
    {
        male.setSelected(true);
        female.setSelected(false);
    }

    public void selectFemale(ActionEvent actionEvent)
    {
        male.setSelected(false);
        female.setSelected(true);
    }

}
