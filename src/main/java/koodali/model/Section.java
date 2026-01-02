package koodali.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Section {
    private String name;
    @OneToMany
    @JoinColumn(name = "id")
    private HashMap<String, Student> students;
    @OneToMany
    @JoinColumn(name = "id")
    private HashMap<String, Teacher> teachers;

    public Section(String name, HashMap<String, Student> students, HashMap<String, Teacher> teachers, List<LocalDateTime> classSchedule) {
        this.name = name;
        this.students = students;
        this.teachers = teachers;
        this.classSchedule = classSchedule;
    }

    @ElementCollection
    @CollectionTable(
            name = "classSchedule",
            joinColumns = @JoinColumn(name = "id")
    )
    @Column(name = "class")
      private List<LocalDateTime> classSchedule;
    @Id
    @GeneratedValue
    private String id;

    public Section(String name) {
        this.name = name;
        this.students = new HashMap<>();
        this.teachers = new HashMap<>();
        this.classSchedule = new ArrayList<>();
    }

    public Section() {

    }

    public List<LocalDateTime> getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(List<LocalDateTime> classSchedule) {
        this.classSchedule = classSchedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
