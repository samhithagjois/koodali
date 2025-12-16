package koodali.repository;

import koodali.model.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class StudentRepository extends PersonRepository<Student> {

    private final HashMap<String, Student> students;


    public StudentRepository() {
        super();
        students = new HashMap<>();

    }

    public HashMap<String, Student> getStudents() {
        return students;
    }
}
