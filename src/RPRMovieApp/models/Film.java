package RPRMovieApp.models;

import java.util.Date;
import java.util.List;

public class Film
{
    private int id;
    private String name;
    private int duration; //Minutes
    private List<Genre> genres;
    private List<Language> languages;
    private Date releasedate;

    public Film(int id, String name, int duration, List<Genre> genres, List<Language> languages, Date releasedate) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.genres = genres;
        this.languages = languages;
        this.releasedate = releasedate;
    }

    public Film() {
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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }
}
