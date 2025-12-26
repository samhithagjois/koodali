package koodali.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;

@Entity
public class Section {
    private ClassNames name;
    @OneToMany
    @JoinColumn(name = "id")
    private HashMap<String, Student> students;
    @OneToMany
    @JoinColumn(name = "id")
    private HashMap<String, Teacher> teachers;
    @ElementCollection
    @CollectionTable(
            name = "classSchedule",
            joinColumns = @JoinColumn(name = "id")
    )
    @MapKeyColumn(name = "week")
    @Column(name = "class")
    private HashMap<LocalDate, Boolean> classSchedule;
    @Id
    @GeneratedValue
    private String id;

    public Section(ClassNames name) {
        this.name = name;
        this.students = new HashMap<>();
        this.teachers = new HashMap<>();
        this.classSchedule = new HashMap<LocalDate, Boolean>();
        //TODO : you should be able to add and change things in the class schedule
    }

    public Section() {

    }

    public String getID() {
        return this.name.toString();
    }

    public HashMap<LocalDate, Boolean> getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(HashMap<LocalDate, Boolean> classSchedule) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
