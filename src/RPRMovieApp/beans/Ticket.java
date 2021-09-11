package RPRMovieApp.beans;

public class Ticket
{
    private int id;
    private int seatid;
    private int cinemaid;
    private int userid;

    public Ticket(int id, int seatid, int cinemaid, int userid, double price) {
        this.id = id;
        this.seatid = seatid;
        this.cinemaid = cinemaid;
        this.userid = userid;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatid() {
        return seatid;
    }

    public void setSeatid(int seatid) {
        this.seatid = seatid;
    }

    public int getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(int cinemaid) {
        this.cinemaid = cinemaid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

}
