public class Bowl {
    private int foodAmount;
    private final int capacity;

    public Bowl(int capacity) {
        this.capacity = capacity;
        this.foodAmount = 0;
    }

    public void addFood(int amount) {
        if (amount < 0) {
            System.out.println("Нельзя добавить отрицательное количество еды");
            return;
        }
        int newAmount = foodAmount + amount;
        if (newAmount > capacity) {
            foodAmount = capacity;
            System.out.println("Миска заполнена! Осталось " + (newAmount - capacity) + " еды не поместилось.");
        } else {
            foodAmount = newAmount;
            System.out.println("Теперь в миске " + foodAmount);
        }
    }

    public boolean decreaseFood(int amount) {
        if (amount < 0) {
            System.out.println("Нельзя взять отрицательное количество еды");
            return false;
        }
        if (foodAmount >= amount) {
            foodAmount -= amount;
            System.out.println("Из миски взято: " + amount + "/Осталось еды: " + foodAmount);
            return true;
        } else {
            System.out.println("В миске недостаточно еды. Нужно: " + amount + " Доступно: " + foodAmount);
            return false;
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public int getCapacity() {
        return capacity;
    }
}