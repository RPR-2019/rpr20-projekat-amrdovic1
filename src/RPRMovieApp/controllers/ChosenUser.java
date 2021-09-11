package RPRMovieApp.controllers;

import RPRMovieApp.beans.User;

//Auxilliary class which stores chosen user when admin browses users

public class ChosenUser
{
    private static User chosen;

    public static User getChosen() {
        return chosen;
    }

    public static void setChosen(User chosen) {
        ChosenUser.chosen = chosen;
    }
}
