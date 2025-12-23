package koodali.model;

import java.util.Date;
import java.util.HashMap;

public class Section {
    private ClassNames name;
    private HashMap<String, Student> students;
    private HashMap<String, Teacher> teachers;
    private HashMap<Date, Boolean> classSchedule;

    public Section(ClassNames name) {
        this.name = name;
        this.students = new HashMap<>();
        this.teachers = new HashMap<>();
        this.classSchedule = new HashMap<>();
        //TODO : you should be able to add and change things in the class schedule
    }

    public String getID(){
        return this.name.toString();
    }

    public HashMap<Date, Boolean> getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(HashMap<Date, Boolean> classSchedule) {
        this.classSchedule = classSchedule;
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

    public void setStudents(HashMap<String, Student> students) {
        this.students = students;
    }

    public HashMap<String, Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(HashMap<String, Teacher> teachers) {
        this.teachers = teachers;
    }
}
