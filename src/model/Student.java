package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {


    private final List<Assignment> pendingAssignments;
    private final List<Assignment> gradedAssignments;
    private ClassNames section;

    public Student(String personID, String firstName, String lastName, ClassNames section) {
        super(personID, firstName, lastName);
        pendingAssignments = new ArrayList<>();
        gradedAssignments = new ArrayList<>();
        this.section = section;
    }

    public ClassNames getSection() {
        return section;
    }

    public void setSection(ClassNames section) {
        this.section = section;
    }

    public List<Assignment> getPendingAssignments() {
        return pendingAssignments;
    }

    public List<Assignment> getGradedAssignments() {
        return gradedAssignments;
    }


}
