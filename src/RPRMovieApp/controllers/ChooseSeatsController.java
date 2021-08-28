package RPRMovieApp.controllers;

import javafx.fxml.FXML;

import java.sql.*;

public class ChooseSeatsController
{
    private PreparedStatement getCinemaForProjection;
    private PreparedStatement getSeatsForProjection;
    private Connection conn;

    private int no_rows;
    private int no_cols;

    private int no_seats;

    private Boolean[][] seats;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
//        int n = 0; //FIX THIS (SHOULD HOLD PROJECTION NUMBER OR SOME STUFF...
//        Class.forName("org.sqlite.JDBC");
//        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
//        conn = DriverManager.getConnection(url, "username", "password");
//        getSeatsForProjection = conn.prepareStatement("SELECT ps.* FROM ProjectionSeat ps, Projection p WHERE" +
//                " p.id=? AND ps.projectionid=p.id");
//        getSeatsForProjection.setInt(1, n);
//        ResultSet rs_seats = getSeatsForProjection.executeQuery();
//        getCinemaForProjection = conn.prepareStatement("SELECT DISTINCT h.* FROM Hall h, Seat s, ProjectionSeat ps, Projection p WHERE " +
//                "p.id=? AND ps.projectionid=p.id AND ps.seatid = s.id AND s.hallid = h.id");
//        getCinemaForProjection.setInt(1, n);
//        ResultSet rs_cinema = getCinemaForProjection.executeQuery();
//        no_rows = rs_cinema.getInt(2);
//        no_cols = rs_cinema.getInt(3);
//        no_seats = no_rows*no_cols;
//        seats = new Boolean[no_rows][no_cols];
    }

    private String getSeatNameForNumber (int n) //As will be shown in the selection window
    {
        int offset = n/no_rows + 65;
        char c = (char) offset;
        Character first_part = c;
        String fp = first_part.toString();
        Integer second_part = n%no_cols;
        String sp = second_part.toString();
        return fp + sp;
    }
}
