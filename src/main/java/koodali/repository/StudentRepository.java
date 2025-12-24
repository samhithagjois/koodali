package koodali.repository;

import koodali.model.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentRepository extends PersonRepository<Student> {

    //private final HashMap<String, Student> students;


    public StudentRepository() {
        super();
       }

}
