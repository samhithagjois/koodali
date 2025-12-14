package koodali.model;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Section {
    private ClassNames name;
    private HashMap<String, Student> students;
    private HashMap<String, Teacher> teachers;

    public Section(ClassNames name) {
        this.name = name;
    }

    public ClassNames getName() {
        return name;
    }

    public void setName(ClassNames name) {
        this.name = name;
    }

    public HashMap<String, Student> getStudents() {
        return students;
    }

    public void setStudents(LinkedHashMap<String, Student> students) {
        this.students = students;
    }

    public HashMap<String, Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(LinkedHashMap<String, Teacher> teachers) {
        this.teachers = teachers;
    }
}
