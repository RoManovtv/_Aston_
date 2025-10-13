public class Main {

    // 1. Метод для вывода трех слов в столбец
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Метод для проверки знака суммы
    public static void checkSumSign() {
        int a = 5;
        int b = -3;
        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Метод для определения цвета по значению
    public static void printColor() {
        int value = 75;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Метод для сравнения двух чисел
    public static void compareNumbers() {
        int a = 8;
        int b = 12;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Метод проверки суммы в диапазоне 10-20
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Метод проверки знака числа (0 - положительное)
    public static void checkNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // 7. Метод проверки отрицательности числа (0 - положительное)
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Метод для печати строки n раз
    public static void printStringMultipleTimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    // 9. Метод проверки високосного года
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 10. Метод для инвертирования массива из 0 и 1
    public static void invertArray() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.print("Исходный массив: ");
        printArray(array);

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }

        System.out.print("Инвертированный массив: ");
        printArray(array);
    }

    // 11. Метод для заполнения массива числами от 1 до 100
    public static void fillArray() {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        System.out.print("Массив от 1 до 100: ");
        printArray(array);
    }

    // 12. Метод для умножения чисел меньше 6 на 2
    public static void multiplySmallNumbers() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.print("Исходный массив: ");
        printArray(array);

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }

        System.out.print("Преобразованный массив: ");
        printArray(array);
    }

    // 13. Метод для заполнения диагоналей единицами
    public static void fillDiagonals() {
        int size = 5;
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) {
                    matrix[i][j] = 1;
                }
            }
        }

        System.out.println("Матрица с диагоналями:");
        printMatrix(matrix);
    }

    // 14. Метод для создания массива с одинаковыми значениями
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    // Вспомогательные методы для вывода массивов
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Главный метод для тестирования всех методов
    public static void main(String[] args) {
        System.out.println("=== Задание 1 ===");
        printThreeWords();

        System.out.println("\n=== Задание 2 ===");
        checkSumSign();

        System.out.println("\n=== Задание 3 ===");
        printColor();

        System.out.println("\n=== Задание 4 ===");
        compareNumbers();

        System.out.println("\n=== Задание 5 ===");
        System.out.println("Сумма 5 и 7 в диапазоне 10-20: " + isSumInRange(5, 7));
        System.out.println("Сумма 10 и 15 в диапазоне 10-20: " + isSumInRange(10, 15));

        System.out.println("\n=== Задание 6 ===");
        checkNumberSign(0);
        checkNumberSign(5);
        checkNumberSign(-3);

        System.out.println("\n=== Задание 7 ===");
        System.out.println("Число -5 отрицательное: " + isNegative(-5));
        System.out.println("Число 0 отрицательное: " + isNegative(0));
        System.out.println("Число 10 отрицательное: " + isNegative(10));

        System.out.println("\n=== Задание 8 ===");
        printStringMultipleTimes("Hello Java!", 3);

        System.out.println("\n=== Задание 9 ===");
        System.out.println("2024 год високосный: " + isLeapYear(2024));
        System.out.println("1900 год високосный: " + isLeapYear(1900));
        System.out.println("2000 год високосный: " + isLeapYear(2000));

        System.out.println("\n=== Задание 10 ===");
        invertArray();

        System.out.println("\n=== Задание 11 ===");
        fillArray();

        System.out.println("\n=== Задание 12 ===");
        multiplySmallNumbers();

        System.out.println("\n=== Задание 13 ===");
        fillDiagonals();

        System.out.println("\n=== Задание 14 ===");
        int[] newArray = createArray(5, 42);
        System.out.print("Массив с одинаковыми значениями: ");
        printArray(newArray);
    }
}