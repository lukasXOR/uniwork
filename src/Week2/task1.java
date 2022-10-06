package Week2;
import helpers.InputReader;
public class task1 {
    public static void main(String[] args) {
        int age = InputReader.getInt("Enter your age > ");
        System.out.println((age < 18) ? "18 or over" : "Under 18");
    }
}
