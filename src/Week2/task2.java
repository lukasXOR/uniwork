package Week2;
import helpers.InputReader;
public class task2 {
    static String checkGrade(String grade) {
        String[] classification = {"1st 70%+", "2:1 60%+", "2:2 50%+", "3rd 40%+", "ordinary >40%", "fail >30%"};
        String[] grades = {"A", "B", "C", "D", "E", "F"};
        for (int i = 0; i < grades.length; i++) {
            if (grade.equals(grades[i])) { // cannot use == as it compares memory location instead use equals()
                return "You achieved :" + classification[i];
            }
        }
        return "Invalid grade given. Must fall between A-F";
    }
    public static void main(String[] args) {
        String userGrade = InputReader.getString("Enter your grade > (A, B, C, D, E or F)");
        System.out.println(checkGrade(userGrade.toUpperCase()));
    }
}
