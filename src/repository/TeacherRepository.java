package repository;

import model.Teacher;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TeacherRepository extends PersonRepository<Teacher> {


    private static HashMap<String, Teacher> teachers;

    public TeacherRepository() {
        super();
        teachers = new HashMap<>();
    }

    public static HashMap<String, Teacher> getTeachers() {
        return teachers;
    }

    public static void setTeachers(HashMap<String, Teacher> teachers) {
        TeacherRepository.teachers = teachers;
    }
}
