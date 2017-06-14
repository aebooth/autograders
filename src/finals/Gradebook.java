package finals;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 6/14/17.
 */
public class Gradebook {
    private Map<String,Student> students;

    public Gradebook(List<Student> students){
        //TODO
    }

    public List<Student> sortName(){
        //TODO
    }

    public List<Student> sortGPA(){
        //TODO
    }

    public List<Student> sortGradeLevel(){
        //TODO
    }

    public Set<Student> findName(String name){
        //TODO
    }

    public Set<Student> findGPA(double gpa){
        //TODO
    }

    public Set<Student> findGradeLevel(int gradeLevel){
        //TODO
    }

    private static class StudentNode{
        public final StudentNode left;
        public final StudentNode right;
        public Student data;

        public StudentNode(Student data){
            this.data = new Student(data.getName(),data.getGradeLevel(),data.getGPA());
            this.left = null;
            this.right = null;
        }
    }
}
