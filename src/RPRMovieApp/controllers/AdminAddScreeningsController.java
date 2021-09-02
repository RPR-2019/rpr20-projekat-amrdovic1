package RPRMovieApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminAddScreeningsController
{
    public Label movieTitle;
    public Button addScreeningBtn;
    public DatePicker newDate;
    public Spinner newTimeHour;
    public Spinner newTimeMinute;
    public ChoiceBox cinemaChoiceBox;

    @FXML
    public void initialize()
    {
        movieTitle.setText(ChosenFilm.getChosen().getName() + "- screenings for week ");
        for (int i = 1; i <= 5; i++)
        {
            cinemaChoiceBox.getItems().add(i);
        }
    }

    public void addScreeningBtnClick(ActionEvent actionEvent) throws IOException
    {
        
    }
}
