package RPRMovieApp.controllers.user.registration;

import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.User;
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
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegisterController
{
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
    private CinemaDAO cDAO;
    private int days[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String mandatory = "This is a mandatory field!";

    public RegisterController() throws SQLException, ClassNotFoundException {
    }

    private List<Integer> getThresholdDate ()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        int year  = now.getYear() - 15;
        int month = now.getMonthValue();
        int day   = now.getDayOfMonth();
        if (month == 2 && day == 29) //We're nice to kids who had this bad luck
        {
            month = 2;
            day = 28;
        }
        return List.of(day, month, year);
    }

    private Boolean isValidDate (Integer day, Integer month, Integer year)
    {
        List<Integer> today = getThresholdDate();
        if ((Integer)selectYear.getValue() > today.get(2))
        {
            return false;
        }
        else if ((Integer)selectYear.getValue() < today.get(2))
        {
            return true;
        }
        else
        {
            if ((Integer)selectMonth.getValue() > today.get(1))
            {
                return false;
            }
            else if ((Integer)selectMonth.getValue() < today.get(1))
            {
                return true;
            }
            else
            {
                if ((Integer)selectDay.getValue() > today.get(0))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }
    }

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
        cDAO = CinemaDAO.getInstance();
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
            EmailValidator ev = EmailValidator.getInstance();
            if (!ev.isValid(email.getText()))
            {
                error = true;
                emailError.setText("Invalid email!\n");
            }
            else
            {
                emailError.setText("");
            }
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
        if (cDAO.checkExistingUsername(username.getText()))
        {
            usernameError.setText("User with username " + username.getText() + "already exists!");
            error = true;
        }
        if (cDAO.checkExistingMail(email.getText()))
        {
            emailError.setText("User with email " + email.getText() + " already exists!");
            error = true;
        }
        if (!isValidDate((Integer)selectDay.getValue(),(Integer)selectMonth.getValue(),(Integer)selectYear.getValue()))
        {
            dateError.setText("User must be at least 15 years old to register!");
            error = true;
        }
        if (!error)
        {
            int new_id = cDAO.getMaxUserID() + 1;
            User u = new User(new_id, username.getText(), email.getText(), password.getText());
            cDAO.addNewUser(u);
            CinemaDAO.removeInstance();
            Node n = (Node) actionEvent.getSource();
            Stage registerStage = (Stage) n.getScene().getWindow();
            registerStage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration successful!");
            alert.setHeaderText(null);
            alert.setContentText(u.getUsername() + ", you have successfully registered into the system!" +
                    "\nLog in with your credentials to proceed.");

            alert.showAndWait();
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
