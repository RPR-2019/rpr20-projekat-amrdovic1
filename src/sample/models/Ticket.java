package sample.models;

public class Ticket
{
    private int id;
    private double price;

    public Ticket(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
