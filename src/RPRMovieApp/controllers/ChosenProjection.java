package RPRMovieApp.controllers;

//This class serves as an auxiliary class so that the same controller can be used for different cinema halls

import RPRMovieApp.beans.Screening;

public class ChosenProjection
{
    private static Screening chosenProjection;

    public static Screening getChosenProjection() {
        return chosenProjection;
    }

    public static void setChosenProjection(Screening chosenProjection) {
        ChosenProjection.chosenProjection = chosenProjection;
    }
}
