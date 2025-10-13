import java.util.*;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> book = new HashMap<>();

    public void add(String name, String phone) {
        if (!book.containsKey(name)) {
            book.put(name, new ArrayList<>());
        }
        book.get(name).add(phone);
    }

    public ArrayList<String> get(String name) {
        return book.getOrDefault(name, new ArrayList<>());
    }

    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();

        pb.add("Иванов", "111-11-11");
        pb.add("Петров", "222-22-22");
        pb.add("Иванов", "333-33-33");

        System.out.println("Иванов: " + pb.get("Иванов"));
        System.out.println("Петров: " + pb.get("Петров"));
        System.out.println("Сидоров: " + pb.get("Сидоров"));
    }
}