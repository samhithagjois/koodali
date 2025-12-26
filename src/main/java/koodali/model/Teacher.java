package koodali.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Teacher extends Person {


    @ManyToMany

    private final List<Student> studentOverview = new ArrayList<>();
    private ClassNames section = ClassNames.UNASSIGNED;
    private int amountOfTextbooks = 0;


    private LocalDate joinDate;

    public Teacher(String personID, String firstName, String lastName, ClassNames section) {
        super(personID, firstName, lastName);
        this.section = section;
    }

    public Teacher(){
        super();
        this.personID = "NKS"+section.toString().substring(0,1).toUpperCase()+"_T_"+ UUID.randomUUID();

    }

    public ClassNames getSection() {
        return section;
    }

    public void setSection(ClassNames section) {
        this.section = section;
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
