import java.util.ArrayList;
import java.util.Arrays;

public class Student {

    private String name;
    private String grup;
    private int course;
    private int[] grades;

    public Student(String name, String grup, int course, int[] grades) {
        this.name = name;
        this.grup = grup;
        this.course = course;
        this.grades = grades;
    }

    public int[] getGrades() {
        return grades;
    }

    public static boolean CheckGrades(int[] grades){
        float average_mark = ((Arrays.stream(grades).sum()) / (float)grades.length);
        return average_mark >= 3;
    }

    public static void deleteStudents(ArrayList<Student> students){
        int i = 0;
        while (i < students.size()){
            if (!Student.CheckGrades(students.get(i).grades)){
                students.remove(i);
            }
            else {
                i++;
            }
        }
    }

    public static void promoteStudents(ArrayList<Student> students){
        for (Student student : students) {
            if (Student.CheckGrades(student.grades)) {
                student.course++;
            }
        }
    }

    public static void printStudents(ArrayList<Student> students, int course){
        for (Student student : students) {
            if (student.course == course) {
                System.out.println(student.name);
            }
        }
    }

    public static void print(ArrayList<Student> students){
        for (Student student : students) {
            System.out.print(student.name + " " + student.course + " ");
        }
        System.out.println();
    }
}