import java.util.ArrayList;
class Student {
    String sName;
    int sID;
    Course sCourse;
    int[] sScore = {0, 0, 0, 0};
    public Student(String name, int ID) { //constructor
        this.sName = name;
        this.sID = ID;
        for (int i = 0; i < sScore.length; i++) {
            this.sScore[i] = this.random(0, 100);
        }
    }
    void enroll(Course course) {
        this.sCourse = course;
        this.display();
        this.sCourse.display();
    }
    public void display() {
        System.out.println("Student Info:");
        System.out.println("  Name: "+ this.sName);
    }
    int random(int max, int min) {
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
class Module {
    String mName;
    String mCode;
    public Module(String name, String code) {
        this.mName = name;
        this.mCode = code;
    }
}
class Course {
    String cCode;
    String cName;
    ArrayList<Module> modules = new ArrayList<Module>();

    public Course(String code, String name) { //constructor
        this.cCode = code;
        this.cName = name;
        for (int i = 0; i < 4; i++) {
            this.modules.add(new Module("Module name" + (i + 1), "Module code " + code));
        }
    }
    public void display() {
        System.out.println("Course Info:");
        System.out.println("  Code: "+ this.cCode);
        System.out.println("  Name: "+ this.cName);
        for (Module m: modules) {
            System.out.println(m.mName);
            System.out.println(m.mCode);
        }
    }
}
public class store {
    public static void main(String[] args) throws Exception{
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Lukas", 22211466));
        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(new Course("474747", "Software Engineering"));
        students.get(0).enroll(courses.get(0));
    }
}