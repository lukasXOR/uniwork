package Week2;
import helpers.InputReader;
public class task4 {
    public static String userContinue;
    static void timesTable(int multiplier) {
        for (int i = 1; i < 13; i++) {
            System.out.println(Integer.toString(i) + 'x' + Integer.toString(multiplier) + '=' + (i * multiplier));
        }
    }
    public static void main(String[] args) {
        timesTable(7);
        do {
            int userMultiplier = InputReader.getInt("Multiplier: ");
            timesTable(userMultiplier);
            userContinue = InputReader.getString("Would you like to print a timestable (yes/no): ");
        } while (userContinue.equals("yes"));
    }
}
