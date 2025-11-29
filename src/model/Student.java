package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{

    private ClassNames section;

    public List<Assignment> getPendingAssignments() {
        return pendingAssignments;
    }

    private final List<Assignment> pendingAssignments;

    public List<Assignment> getGradedAssignments() {
        return gradedAssignments;
    }

    private final List<Assignment> gradedAssignments;

    public Student(String personID, String firstName, String lastName, ClassNames section) {
        super(personID, firstName, lastName);
        pendingAssignments = new ArrayList<>();
        gradedAssignments = new ArrayList<>();
        this.section = section;
    }

   }
