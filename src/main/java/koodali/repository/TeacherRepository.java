package koodali.repository;

import koodali.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TeacherRepository extends PersonRepository<Teacher> {


    private final  HashMap<String, Teacher> teachers;

    public TeacherRepository() {
        super();
        teachers = new HashMap<>();
    }

    public HashMap<String, Teacher> getTeachers() {
        return teachers;
    }

    }
