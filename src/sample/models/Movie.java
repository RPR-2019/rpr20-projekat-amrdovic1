package sample.models;

import java.util.Date;
import java.util.List;

public class Movie
{
    private int id;
    private String name;
    private int duration; //Minutes
    private List<Genre> genres;
    private List<Language> languages;
    private Date releasedate;
}
