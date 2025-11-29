package model;

import java.util.LinkedHashMap;

public class Section {
    private ClassNames name;
    private LinkedHashMap<String,Student> students;
    private LinkedHashMap<String,Teacher> teachers;

    private Section section;
    public Section(ClassNames name) {
        this.name = name;
    }

    public ClassNames getName() {
        return name;
    }

    public void setName(ClassNames name) {
        this.name = name;
    }

    public LinkedHashMap<String, Student> getStudents() {
        return students;
    }

    public void setStudents(LinkedHashMap<String, Student> students) {
        this.students = students;
    }

    public LinkedHashMap<String, Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(LinkedHashMap<String, Teacher> teachers) {
        this.teachers = teachers;
    }
}
