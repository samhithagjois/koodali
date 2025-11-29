package model;

import java.io.File;
import java.util.Date;

public class Assignment {

    private String name;
   private String description;
   private Date submissionDeadline;
   private File attachment;
   private Section forClass;
   private boolean graded;
   private String feedback;
   private boolean read;

   public Assignment(String name, Section forClass, Date submissionDeadline){
       this.name = name;
       this.forClass = forClass;
       this.submissionDeadline = submissionDeadline;
       this.description = "";
       this.attachment = null;
       this.graded = false;
       this.read = false;
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

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }





}
