package RPRMovieApp.controllers;

import RPRMovieApp.models.Film;

public class ChosenFilm
{
    private static Film chosen;

    public static Film getChosen() {
        return chosen;
    }

    public static void setChosen(Film chosen) {
        ChosenFilm.chosen = chosen;
    }
}