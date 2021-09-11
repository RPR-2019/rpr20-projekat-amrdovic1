package RPRMovieApp.beans;

public class Beverage
{
    private int id;
    private int volume;
    private double price;

    public Beverage(int id, int volume, double price) {
        this.id = id;
        this.volume = volume;
        this.price = price;
    }

    public Beverage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
