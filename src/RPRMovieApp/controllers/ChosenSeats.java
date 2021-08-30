package RPRMovieApp.controllers;

import java.util.ArrayList;

public class ChosenSeats
{
    private static ArrayList<Integer> seats = new ArrayList<>();

    public static ArrayList<Integer> getSeats() {
        return seats;
    }

    public static void setSeats(ArrayList<Integer> seats) {
        ChosenSeats.seats = seats;
    }

    public static void addSeat (int seat)
    {
        seats.add(seat);
    }
}
