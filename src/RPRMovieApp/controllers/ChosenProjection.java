package RPRMovieApp.controllers;

//This class serves as an auxiliary class so that the same controller can be used for different cinema halls

public class ChosenProjection
{
    public static int chosenProjection;

    public static int getChosenProjection() {
        return chosenProjection;
    }

    public static void setChosenProjection(int chosenProjection) {
        ChosenProjection.chosenProjection = chosenProjection;
    }
}
