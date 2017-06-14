package finals;

/**
 * Created by Alex on 6/14/17.
 */
public class Student {
    private double GPA;
    private int gradeLevel;
    private String name;

    public Student(String name, int gradeLevel, double GPA){
        this.GPA = GPA;
        this.gradeLevel = gradeLevel;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getGradeLevel(){
        return this.gradeLevel;
    }

    public double getGPA(){
        return this.GPA;
    }
}
