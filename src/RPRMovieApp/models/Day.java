package RPRMovieApp.models;

public enum Day
{
    MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY("Sunday");
    private final String day;

    private Day (String d)
    {
        this.day = d;
    }

    @Override
    public String toString()
    {
        return day;
    }

}
