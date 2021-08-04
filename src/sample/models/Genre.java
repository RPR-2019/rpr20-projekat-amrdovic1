package sample.models;

public enum Genre
{
    ACTION("Action"), ADVENTURE("Adventure"), ANIMATION("Animation"), BIOGRAPHY("Biography"),
    COMEDY("Comedy"), CRIME("Crime"), DOCUMENTARY("Documentary"), DRAMA("Drama"), FAMILY("Family"), FANTASY("Fantasy"),
    FILMNOIR("Film-noir"), HISTORY("History"), HORROR("Horror"), MUSIC("Music"), MUSICAL("Musical"),
    MYSTERY("Mystery"), ROMANCE("Romance"), SCIFI("Sci-fi"), SPORT("Sport"), THRILLER("Thriller"),
    WAR("War"), WESTERN("Western");
    private final String genrename;

    private Genre (String name)
    {
        this.genrename = name;
    }

    @Override
    public String toString()
    {
        return genrename;
    }
}
