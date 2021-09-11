package RPRMovieApp.beans;

import java.util.Date;

public class Director
{
    private int id;
    private String name;
    private String lastname;
    private Gender gender;
    private Date dateofbirth;

    public Director(int id, String name, String lastname, Gender gender, Date dateofbirth) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.dateofbirth = dateofbirth;
    }

    public Director() {
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
}
