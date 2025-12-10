package model;

import java.io.File;
import java.util.Date;

public class Assignment {

    private String name;
    private String description;
    private Date submissionDeadline;


    private Date assignedOn;
    private File attachment;
    private Section forClass;
    private boolean graded;
    private String feedback;


    private AssignmentStatus assignmentStatus;

    public Assignment(String name, Section forClass, Date submissionDeadline, Date assignedOn) {
        this.name = name;
        this.forClass = forClass;
        this.submissionDeadline = submissionDeadline;
        this.assignedOn = assignedOn;
        this.description = "";
        this.attachment = null;
        this.graded = false;
        this.assignmentStatus = AssignmentStatus.NOT_READ;
        this.feedback = "";

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSubmissionDeadline() {
        return submissionDeadline;
    }

    public void setSubmissionDeadline(Date submissionDeadline) {
        this.submissionDeadline = submissionDeadline;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public Section getForClass() {
        return forClass;
    }

    public void setForClass(Section forClass) {
        this.forClass = forClass;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public Date getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(Date assignedOn) {
        this.assignedOn = assignedOn;
    }


    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }


}
