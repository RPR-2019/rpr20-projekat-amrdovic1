package RPRMovieApp.models;

import java.util.ArrayList;
import java.util.Collections;

public class Screening
{
    private int id;
    private int filmid;
    private int dayid;
    private int hour;
    private int minute;
    private int cinemaid;

    public Screening(int id, int filmid, int dayid, int hour, int minute) {
        this.id = id;
        this.filmid = filmid;
        this.dayid = dayid;
        this.hour = hour;
        this.minute = minute;
    }

    public Screening(int id, int filmid, int dayid, int hour, int minute, int cinemaid) {
        this.id = id;
        this.filmid = filmid;
        this.dayid = dayid;
        this.hour = hour;
        this.minute = minute;
        this.cinemaid = cinemaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    public int getDayid() {
        return dayid;
    }

    public void setDayid(int dayid) {
        this.dayid = dayid;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(int cinemaid) {
        this.cinemaid = cinemaid;
    }

    public String getDayName ()
    {
        return Day.values()[dayid - 1].toString();
    }

    public String getHourInfo()
    {
        String h = "";
        String min = "";
        if (hour < 10)
        {
            h += "0";
        }
        h += hour;
        if (minute < 10)
        {
            min += "0";
        }
        min += minute;
        return h + ":" + min;
    }


}
