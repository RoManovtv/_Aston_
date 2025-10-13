import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Иван", "ГР-101", 1, new int[]{4, 5, 3}));
        students.add(new Student("Петр", "ГР-102", 1, new int[]{2, 2, 2}));
        students.add(new Student("Мария", "ГР-101", 2, new int[]{5, 5, 4}));
        students.add(new Student("Анна", "ГР-103", 2, new int[]{3, 2, 2}));

        System.out.println("ДО УДАЛЕНИЯ");
        Student.print(students);

        System.out.println(" СТУДЕНТЫ 1 КУРСА");
        Student.printStudents(students, 1);

        Student.deleteStudents(students);
        System.out.println("ПОСЛЕ УДАЛЕНИЯ");
        Student.print(students);

        Student.promoteStudents(students);
        System.out.println("ПОСЛЕ ПЕРЕВОДА");
        Student.print(students);

        System.out.println("СТУДЕНТЫ 2 КУРСА");
        Student.printStudents(students, 2);
    }
}