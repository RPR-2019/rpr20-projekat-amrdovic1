package RPRMovieApp.models;

public enum Language
{
    ENGLISH("English"), SPANISH("Spanish"), FRENCH("French"), ITALIAN("Italian");
    private final String languagename;

    private Language (String name)
    {
        this.languagename = name;
    }

    @Override
    public String toString()
    {
        return languagename;
    }
}
