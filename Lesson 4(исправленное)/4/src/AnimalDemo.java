public class AnimalDemo {
    public static void main(String[] args) {
        System.out.println("Тест класса животных");

        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Мухтар");
        Cat cat1 = new Cat("Барсик", 10);
        Cat cat2 = new Cat("Мурка", 20);
        Cat cat3 = new Cat("Умка", 15);

        System.out.println(" Тест бега ");
        System.out.println("Собаки:");
        dog1.run(150);
        dog1.run(350);
        dog1.run(600);
        dog2.run(500);
        dog2.run(501);

        System.out.println("\nКоты:");
        cat1.run(100);
        cat2.run(200);
        cat3.run(201);
        cat1.run(50);
        cat2.run(300);

        System.out.println("\n Плавание ");
        System.out.println("Собаки:");
        dog1.swim(10);
        dog1.swim(5);
        dog2.swim(30);
        dog2.swim(11);

        System.out.println("\nКоты:");
        cat1.swim(5);
        cat2.swim(2);
        cat1.swim(0);

        System.out.println("\n Статистика ");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего кошек: " + Cat.getCatCount());

        System.out.println("\n Кормление котов ");
        Bowl bowl = new Bowl(50);
        bowl.addFood(30);
        bowl.addFood(25);

        Cat[] cats = {
                new Cat("Барсик", 10),
                new Cat("Умка", 15),
                new Cat("Мурка", 20),
                new Cat("Васька", 12)
        };

        System.out.println("\nПроцесс кормления:");
        for (Cat cat : cats) {
            System.out.println("\n" + cat.getName());
            cat.eat(bowl);
        }
    }
}