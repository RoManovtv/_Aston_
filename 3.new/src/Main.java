public class Main {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16", "15.01.2025",
                "Apple Inc.", "USA", 4999, false);
        productsArray[2] = new Product("Xiaomi Mi 14", "10.12.2024",
                "Xiaomi", "China", 3299, true);
        productsArray[3] = new Product("Google Pixel 8", "20.11.2024",
                "Google", "USA", 4199, false);
        productsArray[4] = new Product("OnePlus 12", "05.01.2025",
                "OnePlus", "China", 3899, true);

        System.out.println(" ТОВАРЫ ");
        for (Product product : productsArray) {
            product.printInfo();
        }

        System.out.println("\n АТТРАКЦИОНЫ ");
        Park.Attraction attraction1 = new Park.Attraction("Американские горки", "10:00-20:00", 500);
        Park.Attraction attraction2 = new Park.Attraction("Колесо обозрения", "09:00-22:00", 300);

        attraction1.printInfo();
        attraction2.printInfo();
    }
}