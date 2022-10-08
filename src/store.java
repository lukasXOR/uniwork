import java.util.ArrayList;
class Student {
    String sName;
    int sID;
    Course sCourse;
    
    public Student(String name, int ID) { //constructor
        this.sName = name;
        this.sID = ID;
    }
    void enroll(Course course) {
        this.sCourse = course;
         this.display();
    }
    public void display() {
        System.out.println("Student Info:");
        System.out.println("  Name: "+ this.sName);
        System.out.println("  ID: "+ this.sID);
        System.out.println("  Course code: "+ this.sCourse.cCode);
        System.out.println("  Course name: "+ this.sCourse.cName);
    }
}
class Course {
    String cCode;
    String cName;
    
    public Course(String code, String name) { //constructor
        this.cCode = code;
        this.cName = name;
    }
    public void display() {
        System.out.println("Course Info:");
        System.out.println("  Code: "+ this.cCode);
        System.out.println("  Name: "+ this.cName);
    }
}
public class store {
    public static void main(String[] args) throws Exception{
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Lukas", 22211466));
        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(new Course("CO452-22S1", "Programming Concepts"));
        students.get(0).enroll(courses.get(0));
    }
}