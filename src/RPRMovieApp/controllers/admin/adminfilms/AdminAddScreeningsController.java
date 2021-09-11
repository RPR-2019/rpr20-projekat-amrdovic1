package RPRMovieApp.controllers.admin.adminfilms;

import RPRMovieApp.controllers.ChosenFilm;
import javafx.event.ActionEvent;
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

public class AdminAddScreeningsController
{
    public Label movieTitle;
    public Button addScreeningBtn;
    public DatePicker newDate;
    public Spinner newTimeHour;
    public Spinner newTimeMinute;
    public ChoiceBox cinemaChoiceBox;
    public Label dateError;
    public Label screeningError;

    private Connection conn;
    private PreparedStatement getMaxScreeningID;
    private PreparedStatement addScreening;

    @FXML
    public void initialize()
    {
        movieTitle.setText(ChosenFilm.getChosen().getName() + "- screenings for week ");
        for (int i = 1; i <= 5; i++)
        {
            cinemaChoiceBox.getItems().add(i);
        }
    }

    public void addScreeningBtnClick(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        boolean error = false;
        if (newDate.getValue() == null)
        {
            dateError.setText("You must select a screening date!");
            error = true;
        }
        if (cinemaChoiceBox.getValue() == null)
        {
            screeningError.setText("You must select a cinema hall!");
            error = true;
        }
        if (!error)
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
            conn = DriverManager.getConnection(url, "username", "password");
            getMaxScreeningID = conn.prepareStatement("SELECT MAX(id) FROM projection");
            ResultSet gmsidrs = getMaxScreeningID.executeQuery();
            int new_id = gmsidrs.getInt(1) + 1;
            addScreening = conn.prepareStatement("INSERT INTO projection VALUES(?,?,?,?,?,?)");
            addScreening.setInt(1, new_id);
            addScreening.setInt(2, ChosenFilm.getChosen().getId());
            int day = newDate.getValue().getDayOfWeek().getValue();
            addScreening.setInt(3, day);
            addScreening.setInt(4, (Integer)newTimeHour.getValue());
            addScreening.setInt(5, (Integer)newTimeMinute.getValue());
            addScreening.setInt(6, (Integer)cinemaChoiceBox.getValue());
            addScreening.execute();

            Node n = (Node) actionEvent.getSource();
            Stage addScreeningStage = (Stage) n.getScene().getWindow();
            addScreeningStage.close();
            conn.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Adding new screening successful!");
            alert.setContentText("You successfully added the screening for " + ChosenFilm.getChosen().getName());
            alert.showAndWait();
        }
    }
}
