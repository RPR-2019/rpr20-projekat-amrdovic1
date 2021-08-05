package sample.models;

public enum Gender
{
    MALE("Male"), FEMALE("Female"), UNDISCLOSED("Undisclosed");

    private final String gendername;

    private Gender(String name)
    {
        this.gendername = name;
    }

    @Override
    public String toString()
    {
        return this.gendername;
    }
}
