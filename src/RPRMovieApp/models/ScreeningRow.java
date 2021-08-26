package RPRMovieApp.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ScreeningRow
{
    private SimpleStringProperty day;
    private SimpleStringProperty hour;
    private SimpleIntegerProperty cinema;
    private SimpleStringProperty sel;

    public ScreeningRow(SimpleStringProperty day, SimpleStringProperty hour, SimpleIntegerProperty cinema, SimpleStringProperty sel) {
        this.day = day;
        this.hour = hour;
        this.cinema = cinema;
        this.sel = sel;
    }

    public ScreeningRow(String day, String hour, Integer cinema, String sel) {
        this.day = new SimpleStringProperty(day);
        this.hour = new SimpleStringProperty(hour);
        this.cinema = new SimpleIntegerProperty(cinema);
        this.sel = new SimpleStringProperty(sel);
    }

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getHour() {
        return hour.get();
    }

    public SimpleStringProperty hourProperty() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour.set(hour);
    }

    public String getSel() {
        return sel.get();
    }

    public SimpleStringProperty selProperty() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel.set(sel);
    }

    public int getCinema() {
        return cinema.get();
    }

    public SimpleIntegerProperty cinemaProperty() {
        return cinema;
    }

    public void setCinema(int cinema) {
        this.cinema.set(cinema);
    }
}
