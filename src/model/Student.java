package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    private Section section;

    public List<Assignment> getPendingAssignments() {
        return pendingAssignments;
    }

    private final List<Assignment> pendingAssignments;

    public List<Assignment> getGradedAssignments() {
        return gradedAssignments;
    }

    private final List<Assignment> gradedAssignments;

    public Student(String personID, String firstName, String lastName) {
        super(personID, firstName, lastName);
        pendingAssignments = new ArrayList<>();
        gradedAssignments = new ArrayList<>();
    }

    public void submitAssignment(Assignment assignment){
        //TO DO!
    }

    public void viewPendingAssignments(){
        //TO DO !!
    }

    public void viewGradedAssignments(){
        //TO DO
    }

    public void addAssignmentToPendingAssignments(Assignment assignment){
        pendingAssignments.add(assignment);
    }

    public void removeAssignmentFromPendingAssignments(Assignment assignment){
        pendingAssignments.remove(assignment);
    }

    public void addAssignmentToGradedAssignments(Assignment assignment){
        gradedAssignments.add(assignment);
    }

    public void removeAssignmentToGradedAssignments(Assignment assignment){
        gradedAssignments.add(assignment);
    }
}
