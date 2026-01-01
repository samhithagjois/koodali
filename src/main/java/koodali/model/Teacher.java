package koodali.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Teacher extends Person {


    @ManyToMany

    private final List<Student> studentOverview = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "teacher_sections",
            joinColumns = @JoinColumn(name = "teacher_id")
    )
    @Column(name = "section")
    private List<ClassNames> sections;
    private int amountOfTextbooks = 0;

    private LocalDate joinDate;

    public Teacher(String personID, String firstName, String lastName, List<ClassNames> sections) {
        super(personID, firstName, lastName);
        this.sections = sections;
    }

    public Teacher (String firstName, String lastName, String city, String pinCode,
                    String country, String fullPostalAddress,
                    LocalDateTime dateOfRegistration, LocalDateTime dateOfClassStart, boolean activeStatus){

    }

    public Teacher(){
        super();
        this.personID = "NKS_T_"+ UUID.randomUUID();

    }

    public List<ClassNames> getSections() {
        return sections;
    }

    public void setSections(List<ClassNames> section) {
        this.sections = section;
    }

    public List<Student> getStudentOverview() {
        return studentOverview;
    }

    public int getAmountOfTextbooks() {
        return amountOfTextbooks;
    }

    public void setAmountOfTextbooks(int amountOfTextbooks) {
        this.amountOfTextbooks = amountOfTextbooks;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
}
