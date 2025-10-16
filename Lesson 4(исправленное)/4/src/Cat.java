public class Cat extends Animal {
    private boolean isFull;
    private final int appetite;
    private static int catCount = 0;
    private final int maxRunDistance = 200;

    public Cat(String name, int appetite) {
        super(name);
        this.appetite = appetite;
        this.isFull = false;
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(getName() + " пробежал " + distance + " м.");
        } else {
            System.out.println(getName() + " не может пробежать " + distance + " м. Максимум " + maxRunDistance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(getName() + " не умеет плавать.");
    }

    public void eat(Bowl bowl) {
        if (isFull) {
            System.out.println(getName() + " уже сыт.");
            return;
        }
        System.out.println(getName() + " подошел к миске. Ему нужно " + appetite + " еды.");
        if (bowl.decreaseFood(appetite)) {
            isFull = true;
            System.out.println(getName() + " покушал и теперь сыт.");
        } else {
            System.out.println(getName() + " не стал есть, недостаточно еды");
        }
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean isFull() {
        return isFull;
    }
}