package RPRMovieApp.controllers.admin.adminusers;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class UserDeleteWarningController
{
    private User user;

    public Label warningMessage;
    public Button cancelBtn;
    public Button deleteUser;

    private CinemaDAO cDAO;

//    private Connection conn;
//    private PreparedStatement deleteUserStatement;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        cDAO = CinemaDAO.getInstance();
        user = CurrentData.getCurrentUser();
        warningMessage.setText(warningMessage.getText() + " " + user.getUsername());
    }

    public void deleteUserClick(ActionEvent actionEvent) throws SQLException, IOException {

        cDAO.deleteUser(user.getUsername());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User successfully deleted");
        alert.setHeaderText(null);
        alert.setContentText("User " + user.getUsername() + " successfully deleted!");

        alert.showAndWait();
    }
}
