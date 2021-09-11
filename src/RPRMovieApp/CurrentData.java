package RPRMovieApp;

import RPRMovieApp.beans.Film;
import RPRMovieApp.beans.Screening;
import RPRMovieApp.beans.User;

import java.util.ArrayList;

public class CurrentData
{
    private static Film currentFilm;
    private static Screening currentScreening;
    private static ArrayList<Integer> currentSeats;
    private static User currentUser; //User currently using the system
    private static User currentSelectedUser; //User admin has currently selected

    public static Film getCurrentFilm() {
        return currentFilm;
    }

    public static void setCurrentFilm(Film currentFilm) {
        CurrentData.currentFilm = currentFilm;
    }

    public static Screening getCurrentScreening() {
        return currentScreening;
    }

    public static void setCurrentScreening(Screening currentScreening) {
        CurrentData.currentScreening = currentScreening;
    }

    public static ArrayList<Integer> getCurrentSeats() {
        return currentSeats;
    }

    public static void setCurrentSeats(ArrayList<Integer> currentSeats) {
        CurrentData.currentSeats = currentSeats;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentData.currentUser = currentUser;
    }

    public static User getCurrentSelectedUser() {
        return currentSelectedUser;
    }

    public static void setCurrentSelectedUser(User currentSelectedUser) {
        CurrentData.currentSelectedUser = currentSelectedUser;
    }
}
