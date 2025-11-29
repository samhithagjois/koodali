package model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{
    public List<Assignment> getUngradedAssignments() {
        return ungradedAssignments;
    }

    public List<Assignment> getGradedAssignments() {
        return gradedAssignments;
    }

    public ClassNames getSection() {
        return section;
    }

    public void setSection(ClassNames section) {
        this.section = section;
    }

    private ClassNames section;
    private final List<Assignment> ungradedAssignments;
    private final List<Assignment> gradedAssignments;

    public List<Student> getStudentOverview() {
        return studentOverview;
    }

    private final List<Student> studentOverview;



    public Teacher(String personID, String firstName, String lastName, ClassNames section) {
        super(personID, firstName, lastName);
        ungradedAssignments = new ArrayList<>();
        gradedAssignments = new ArrayList<>();
        this.section = section;
        studentOverview =  new ArrayList<>();
    }
}
