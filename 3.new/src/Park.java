public class Park {

    public static class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println(name + " - " + workingHours + " - " + price + " руб.");
        }
    }
}