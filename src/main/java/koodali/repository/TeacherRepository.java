package koodali.repository;

import koodali.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherRepository extends PersonRepository<Teacher> {


    public TeacherRepository() {
        super();
    }


}
