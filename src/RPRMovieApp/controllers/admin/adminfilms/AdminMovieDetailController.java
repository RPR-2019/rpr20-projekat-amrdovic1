package RPRMovieApp.controllers.admin.adminfilms;

import RPRMovieApp.CurrentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminMovieDetailController
{
    public Label selectedMovie;
    public TableView screeningsTable;
    public Button nextWeekScrBtn;

    @FXML
    public void initialize()
    {
        selectedMovie.setText(CurrentData.getCurrentFilm().getName());
    }

    public void nextWeekScrBtnClick(ActionEvent actionEvent) throws IOException {
        Stage nextWeekStage = new Stage();
        FXMLLoader nextWeekLoader = new FXMLLoader(getClass().getResource("/fxml/admin/adminfilms/adminAddScreenings.fxml")); //This path is temporary
        Parent root = nextWeekLoader.load();
        nextWeekStage.setTitle("Screenings for next week - " + CurrentData.getCurrentFilm().getName());
        nextWeekStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        nextWeekStage.setResizable(false);
        nextWeekStage.show();
    }
}
