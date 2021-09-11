package RPRMovieApp.DAO;

import RPRMovieApp.beans.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CinemaDAO
{
    private static CinemaDAO cinema;
    private Connection conn;

    //AddFilmController statements
    private PreparedStatement getDirectors, addNewFilm, addDirector, getMaxDirectorID, getExistingDirector, getMaxFilmID;
    //AdminAddScreeningsController statements
    private PreparedStatement getMaxScreeningID, addScreening;
    //AdminFindFilmsController statements
    private PreparedStatement findAllFilms;
    //AdminViewUsersController statements
    private PreparedStatement findAllUsers;
    //UserDeleteWarningController statements
    private PreparedStatement deleteUserStatement;
    //RegisterController statements
    private PreparedStatement sameUsernameCheck, sameEMailCheck, registerNewUser, getMaxUserID;
    //Seat selection statements
    private PreparedStatement getTickets;
    //ReservationPaymentController statements
    private PreparedStatement insertTickets, getMaxTicketID;
    //LatestMovieDetailController statements
    private PreparedStatement getAllScreenings, checkExistingTicket, getSelectedScreening;
    //LatestMovieController statements
    private PreparedStatement getDays;
    //LoginController statements
    private PreparedStatement checkPassword, getUserByName;

    public static CinemaDAO getInstance() throws SQLException, ClassNotFoundException {
        if (cinema == null)
        {
            cinema = new CinemaDAO();
        }
        return cinema;
    }

    private CinemaDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        getDirectors = conn.prepareStatement("SELECT * FROM director");
        addNewFilm = conn.prepareStatement("INSERT INTO film VALUES(?,?,?,?,?,?,?,?,?,?)");
        addDirector = conn.prepareStatement("INSERT INTO director VALUES(?,?)");
        getMaxDirectorID = conn.prepareStatement("SELECT MAX(id) FROM director");
        getExistingDirector = conn.prepareStatement("SELECT * FROM director WHERE name=?");
        getMaxFilmID = conn.prepareStatement("SELECT MAX(id) FROM film");

        getMaxScreeningID = conn.prepareStatement("SELECT MAX(id) FROM projection");
        addScreening = conn.prepareStatement("INSERT INTO projection VALUES(?,?,?,?,?,?)");

        findAllFilms = conn.prepareStatement("SELECT * FROM film");

        findAllUsers = conn.prepareStatement("SELECT * FROM user WHERE username != 'admin' ORDER BY username");

        deleteUserStatement = conn.prepareStatement("DELETE FROM user WHERE username=?");

        sameUsernameCheck = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE username=?");
        sameEMailCheck = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE email=?");
        registerNewUser = conn.prepareStatement("INSERT INTO user VALUES(?,?,?,?)");
        getMaxUserID = conn.prepareStatement("SELECT MAX(id) FROM user");

        getTickets = conn.prepareStatement("SELECT * FROM ticket WHERE projectionid=?");

        getMaxTicketID = conn.prepareStatement("SELECT MAX(id) FROM ticket");
        insertTickets = conn.prepareStatement("INSERT INTO ticket VALUES(?,?,?,?)");

        getAllScreenings = conn.prepareStatement("SELECT p.* FROM projection p, film f WHERE f.id=? AND p.filmid = f.id");
        getSelectedScreening = conn.prepareStatement("SELECT * FROM projection WHERE filmid=? AND dayid=? AND hour=? AND minute=? AND hallid=?");
        checkExistingTicket = conn.prepareStatement("SELECT * FROM ticket WHERE userid=? AND projectionid=?");

        getDays = conn.prepareStatement("SELECT name FROM day");

        checkPassword = conn.prepareStatement("SELECT password FROM user WHERE username=?");
        getUserByName = conn.prepareStatement("SELECT * FROM user WHERE username=?");
    }

    public static void removeInstance()
    {
        if (cinema == null) return;
        cinema.close();
        cinema = null;
    }

    public void close()
    {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int getMaxID(PreparedStatement ps)
    {
        int n = 0;
        try {
            ResultSet gmfid = ps.executeQuery();
            n = gmfid.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return n;
    }

    public int getMaxFilmID()
    {
        return getMaxID(getMaxFilmID);
    }

    public int getMaxDirectorID()
    {
        return getMaxID(getMaxDirectorID);
    }

    public int getMaxScreeningID()
    {
        return getMaxID(getMaxScreeningID);
    }

    public int getMaxUserID()
    {
        return getMaxID(getMaxUserID);
    }

    public int getMaxTicketID()
    {
        return getMaxID(getMaxTicketID);
    }

    public boolean checkExistingUsername(String username)
    {
        boolean res = false;
        try {
            sameUsernameCheck.setString(1, username);
            ResultSet sucrs = sameUsernameCheck.executeQuery();
            res = (sucrs.getInt(1) != 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public boolean checkExistingMail(String email)
    {
        boolean res = false;
        try {
            sameEMailCheck.setString(1, email);
            ResultSet semcrs = sameEMailCheck.executeQuery();
            res = (semcrs.getInt(1) != 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public boolean checkExistingTicketForUser (User u, Screening s)
    {
        boolean res = false;
        try {
            checkExistingTicket.setInt(1, u.getId());
            checkExistingTicket.setInt(2, s.getId());
            ResultSet cetrs = checkExistingTicket.executeQuery();
            res = cetrs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public boolean checkPasswordForUser (String u, String password)
    {
        boolean ok = false;
        try {
            checkPassword.setString(1, u);
            ResultSet rscp = checkPassword.executeQuery();
            if (rscp.getString(1).equals(password))
            {
                ok = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ok;
    }

    public ArrayList<String> getDirectors()
    {
        ArrayList<String> directors = new ArrayList<>();
        try {
            ResultSet rsdir = getDirectors.executeQuery();
            while (rsdir.next())
            {
                directors.add(rsdir.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return directors;
    }

    public ArrayList<Film> getAllFilms()
    {
        ArrayList<Film> films = new ArrayList<>();
        try {
            ResultSet rsfilms = findAllFilms.executeQuery();
            while (rsfilms.next())
            {
                Film f = new Film(rsfilms.getInt(1), rsfilms.getString(2), rsfilms.getInt(3), rsfilms.getInt(4), rsfilms.getInt(5), rsfilms.getInt(6), rsfilms.getString(7));
                LocalDate d = LocalDate.of(rsfilms.getInt(10), rsfilms.getInt(9), rsfilms.getInt(8));
                f.setReleasedate(d);
                films.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return films;
    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet rsusers = findAllUsers.executeQuery();
            while (rsusers.next())
            {
                User u = new User(rsusers.getInt(1), rsusers.getString(2), rsusers.getString(3));
                users.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public ArrayList<Integer> getTakenSeatsForScreening (int id)
    {
        ArrayList<Integer> tickets = new ArrayList<>();
        try {
            getTickets.setInt(1, id);
            ResultSet rs = getTickets.executeQuery();
            while (rs.next())
            {
                tickets.add(rs.getInt(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tickets;
    }

    public ArrayList<Screening> getAllScreeningsForFilm (Film f)
    {
        ArrayList<Screening> s = new ArrayList<>();
        try {
            getAllScreenings.setInt(1, f.getId());
            ResultSet gasrs = getAllScreenings.executeQuery();
            while (gasrs.next())
            {
                Screening scr = new Screening(gasrs.getInt(1), gasrs.getInt(2), gasrs.getInt(3), gasrs.getInt(4), gasrs.getInt(5), gasrs.getInt(6));
                s.add(scr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return s;
    }

    public Screening getScreening (int film_id, int day_id, int h, int min, int cinema)
    {
        Screening s = null;
        try {
            getSelectedScreening.setInt(1, film_id);
            getSelectedScreening.setInt(2, day_id);
            getSelectedScreening.setInt(3, h);
            getSelectedScreening.setInt(4, min);
            getSelectedScreening.setInt(5, cinema);
            ResultSet gssrs = getSelectedScreening.executeQuery();
            if (gssrs.next())
            {
                s = new Screening(gssrs.getInt(1), gssrs.getInt(2), gssrs.getInt(3), gssrs.getInt(4), gssrs.getInt(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return s;
    }

    public ArrayList<String> getDayNames() throws SQLException {
        ArrayList<String> days = new ArrayList<>();
        ResultSet rsgd = getDays.executeQuery();
        while (rsgd.next())
        {
            days.add(rsgd.getString(1));
        }
        return days;
    }

    public User getUser (String name)
    {
        User u = null;
        try {
            getUserByName.setString(1, name);
            ResultSet gubnrs = getUserByName.executeQuery();
            u = new User(gubnrs.getInt(1), gubnrs.getString(2), gubnrs.getString(3), gubnrs.getString(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }

    public void addFilm (Film f)
    {
        try {
            addNewFilm.setInt(1, f.getId());
            addNewFilm.setString(2, f.getName());
            addNewFilm.setInt(3, f.getDuration());
            addNewFilm.setInt(4, f.getDirectorid());
            addNewFilm.setInt(5, f.getGenreNumber());
            addNewFilm.setInt(6, f.getLanguageNumber());
            addNewFilm.setString(7, f.getSynopsis());
            LocalDate d = f.getReleasedate();
            addNewFilm.setInt(8, d.getDayOfMonth());
            addNewFilm.setInt(9, d.getMonthValue());
            addNewFilm.setInt(10, d.getYear());
            addNewFilm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDirector (Director d)
    {
        try {
            addDirector.setInt(1, d.getId());
            addDirector.setString(2, d.getName());
            addDirector.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addScreening(Screening s)
    {
        try {
            addScreening.setInt(1, s.getId());
            addScreening.setInt(2, s.getFilmid());
            addScreening.setInt(3, s.getDayid());
            addScreening.setInt(4, s.getHour());
            addScreening.setInt(5, s.getMinute());
            addScreening.setInt(6, s.getMinute());
            addScreening.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addNewUser(User u)
    {
        try {
            registerNewUser.setInt(1, u.getId());
            registerNewUser.setString(2, u.getUsername());
            registerNewUser.setString(3, u.getEmail());
            registerNewUser.setString(4, u.getPassword());
            registerNewUser.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addTicket (Ticket t)
    {
        try {
            insertTickets.setInt(1, t.getId());
            insertTickets.setInt(2, t.getSeatid());
            insertTickets.setInt(3, t.getCinemaid());
            insertTickets.setInt(4, t.getUserid());
            insertTickets.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteUser(String u)
    {
        try {
            deleteUserStatement.setString(1, u);
            deleteUserStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Director getDirector(String name)
    {
        Director d = null;
        try {
            getExistingDirector.setString(1, name);
            ResultSet rsged = getExistingDirector.executeQuery();
            d = new Director(rsged.getInt(1), rsged.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return d;
    }
}
