package RPRMovieApp.controllers;

import RPRMovieApp.models.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class FilmElementController
{
    private Film film;
    public Button moreBtn;
    public TextArea filmInfo;
    public ImageView filmImage;


    public FilmElementController (Film f) //Setting a film
    {
        film = f;
    }

    @FXML
    public void initialize()
    {
        filmInfo.setText(film.toString());
    }



}
