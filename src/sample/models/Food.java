package sample.models;

public class Food
{
    int id;
    int weight;
    double price;

    public Food(int id, int weight, double price) {
        this.id = id;
        this.weight = weight;
        this.price = price;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
