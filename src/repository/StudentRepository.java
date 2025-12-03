package repository;

import model.ClassNames;
import model.Section;
import model.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;


@Component
public class StudentRepository extends PersonRepository<Student>{

    private HashMap<String, Student> students;


    public StudentRepository(){
        super();
        students = new HashMap<>();

    }

    public HashMap<String, Student> getStudents() {
        return students;
    }

    public void setStudents(HashMap<String, Student> students) {
        this.students = students;
    }
}
