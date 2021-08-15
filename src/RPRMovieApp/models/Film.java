package RPRMovieApp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Film
{
    //Change attributes to properties
    private int id;
    private String name;
    private int duration; //Minutes
    private int genres; //Conversion logic follows in the code below
    private int languages; //Conversion logic follows in the code below
    private Date releasedate;

    private ArrayList<Boolean> convertGenres()
    {
        ArrayList<Boolean> genreList = new ArrayList<>(Genre.values().length);
        //Code below should be a function (same code appears in convertLanguage)
        int gtemp = genres;
        for (int i = 22; i >= 0 && gtemp > 0; i++)
        {
            genreList.set(i, gtemp%2 != 0);
            gtemp /= 2;
        }
        return genreList;
    }

    private ArrayList<Boolean> convertLanguage ()
    {
        ArrayList<Boolean> genreList = new ArrayList<>(Language.values().length);
        int gtemp = genres;
        int i;
        for (i = Language.values().length - 1; i >= 0; i--)
        {
            genreList.set(i, gtemp%2 != 0);
            gtemp /= 2;
            if (gtemp == 0)
            {
                break;
            }
        }
        for (int j = i; j >= 0; j++)
        {
            genreList.set(j, false);
        }
        return genreList;
    }

    private ArrayList<Genre> getGenres ()
    {
        ArrayList<Boolean> whichGenres = convertGenres();
        ArrayList<Genre> g = new ArrayList();
        int n = whichGenres.size();
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
        ArrayList<Boolean> whichLanguages = convertGenres();
        ArrayList<Language> l = new ArrayList();
        int n = whichLanguages.size();
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
}
