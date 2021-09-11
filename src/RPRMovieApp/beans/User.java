package RPRMovieApp.beans;

public class User
{
    private int id;
    private String username;
    private String email;
    private String password;
    private Boolean mode = false; //This attribute is used for whether all user info (true) or only username (false) will be shown in toString() (see below)

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getMode() {
        return mode;
    }

    public void setMode(Boolean mode) {
        this.mode = mode;
    }

    @Override
    public String toString()
    {
        if (mode)
        {
            return "Username: " + getUsername() + "\n" + "Email: " + getEmail();
        }
        return getUsername();
    }
}
