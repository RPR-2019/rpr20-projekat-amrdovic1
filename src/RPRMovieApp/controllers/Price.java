package RPRMovieApp.controllers;

public class Price
{
    public static double price;

    public static double getPrice() {
        return price;
    }

    public static void setPrice(double price) {
        Price.price = price;
    }

    public static void addToPrice (double pr)
    {
        price += pr;
    }
}
