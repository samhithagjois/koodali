package model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{

    private ClassNames section;
    private final List<Assignment> ungradedAssignments;
    private final List<Assignment> gradedAssignments;
    private final List<Student> studentOverview;
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



    public List<Student> getStudentOverview() {
        return studentOverview;
    }





    public Teacher(String personID, String firstName, String lastName, ClassNames section) {
        super(personID, firstName, lastName);
        ungradedAssignments = new ArrayList<>();
        gradedAssignments = new ArrayList<>();
        this.section = section;
        studentOverview =  new ArrayList<>();
    }
}
