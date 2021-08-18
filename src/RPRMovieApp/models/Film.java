package RPRMovieApp.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Film
{
    //Change attributes to properties
    private int id;
    private String name;
    private int duration; //Minutes
    private int genres; //Conversion logic follows in the code below
    private int languages; //Conversion logic follows in the code below
    private int directorid; //Every film is assumed to have a single director
    //Actors should somehow be stored here (ActorFilm table should probably be used)
    private Date releasedate;

    public Film(String name, int duration, int genres, int languages) {
        this.name = name;
        this.duration = duration;
        this.genres = genres;
        this.languages = languages;
    }

    public Film(int id, String name, int duration, int genres, int languages, int directorid) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.genres = genres;
        this.languages = languages;
        this.directorid = directorid;
    }

    private String getDirectorName () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url, "username", "password");
        PreparedStatement psfd = conn.prepareStatement("SELECT d.name FROM film f, director d WHERE f.id=? AND d.id = f.directorid");
        psfd.setInt(1, id);
        ResultSet rsfd = psfd.executeQuery();
        String res = rsfd.getString(1);
        conn.close();
        return res;
    }

    private ArrayList<Boolean> convertGenres()
    {
        int n = Genre.values().length;
        ArrayList<Boolean> genreList = new ArrayList();
        //Code below should be a function (same code appears in convertLanguage)
        int gtemp = genres;
        int i;
        for (i = 0; i < n && gtemp > 0; i++)
        {
            genreList.add(gtemp%2 != 0);
            gtemp /= 2;
        }
        for (int j = i; j < n; j++)
        {
            genreList.add(false);
        }
        Collections.reverse(genreList);
        return genreList;
    }

    private ArrayList<Boolean> convertLanguages()
    {
        int n = Language.values().length;
        ArrayList<Boolean> languageList = new ArrayList();
        //Code below should be a function (same code appears in convertLanguage)
        int ltemp = languages;
        int i;
        for (i = 0; i < n && ltemp > 0; i++)
        {
            languageList.add(ltemp%2 != 0);
            ltemp /= 2;
        }
        for (int j = i; j < n; j++)
        {
            languageList.add(false);
        }
        Collections.reverse(languageList);
        return languageList;
    }

    private ArrayList<Genre> getGenres ()
    {
        ArrayList<Boolean> whichGenres = convertGenres();
        ArrayList<Genre> g = new ArrayList();
        for (Genre gen : Genre.values())
        {
            if (whichGenres.get(gen.ordinal()))
            {
                g.add(gen);
            }
        }
        return g;
    }

    private ArrayList<Language> getLanguages()
    {
        ArrayList<Boolean> whichLanguages = convertLanguages();
        ArrayList<Language> l = new ArrayList();
        for (Language lang : Language.values())
        {
            if (whichLanguages.get(lang.ordinal()))
            {
                l.add(lang);
            }
        }
        return l;
    }

    public Film()
    {
    }

    public Film(String name, int duration) //For now
    {
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenres(int genres) {
        this.genres = genres;
    }

    public void setLanguages(int languages) {
        this.languages = languages;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public int getDirectorid() {
        return directorid;
    }

    public void setDirectorid(int directorid) {
        this.directorid = directorid;
    }

    @Override
    public String toString()
    {
        String result = "";
        try {
            result = name.toUpperCase() + "\n" + "Director: " + getDirectorName() + "\n" +
                        "Duration: " + duration + "min\n" +
                        "Genres: " + getGenres().stream().map(Genre::toString).collect(Collectors.joining(", ")) + "\n" +
                        "Languages: " + getLanguages().stream().map(Language::toString).collect(Collectors.joining(", "));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
