package model;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends Person {


    private ClassNames section;
    //new variables
    //      date of birth : Date
    //      fathers name : String
    //      fathers email id : String
    //      fathers phone number : float
    //      mothers name : String
    //      mothers email id : String
    //      mothers phone number : float
    //
    //      fees to pay : int
    //      feed paid : int
    //
    //      attendance percentage : double
    //      homework leaderboard score : int
    //      assessment test result : seperate page
    //      photo of child : Multipart file
    //      consent form  : Multipart file
    private int amountOfTextbooks, feesPaid,pendingFees,homeworkLeaderBoardScore;
    private MultipartFile photo, consentForm;
    private Date dateOfBirth, dateOfRegistration,dateOfFirstClass;

    private String mothersName, fathersName,fathersEmailID,mothersEmailID;
    private double attendancePercentage;
    private float phoneNumber,whatsappNumber;


    public Student(String personID, String firstName, String lastName, ClassNames section) {
        super(personID, firstName, lastName);

        this.section = section;
    }

    public Student(String firstName, String lastName, String personID,
                   String city, String pinCode, String country,
                   String fullPostalAdress, Date dateOfRegistration,
                   Date dateOfClassStart, boolean activeStatus, ClassNames section,
                   int amountOfTextbooks, int feesPaid, int pendingFees, int homeworkLeaderBoardScore,
                   MultipartFile photo, MultipartFile consentForm, Date dateOfBirth, Date dateOfRegistration1,
                   Date dateOfFirstClass, String mothersName, String fathersName,
                   String fathersEmailID, String mothersEmailID, double attendancePercentage,
                   float phoneNumber, float whatsappNumber) {

        super(firstName, lastName, personID,
                city, pinCode, country, fullPostalAdress,
                dateOfRegistration, dateOfClassStart,
                activeStatus);
        this.section = section;
        this.amountOfTextbooks = amountOfTextbooks;
        this.feesPaid = feesPaid;
        this.pendingFees = pendingFees;
        this.homeworkLeaderBoardScore = homeworkLeaderBoardScore;
        this.photo = photo;
        this.consentForm = consentForm;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration1;
        this.dateOfFirstClass = dateOfFirstClass;
        this.mothersName = mothersName;
        this.fathersName = fathersName;
        this.fathersEmailID = fathersEmailID;
        this.mothersEmailID = mothersEmailID;
        this.attendancePercentage = attendancePercentage;
        this.phoneNumber = phoneNumber;
        this.whatsappNumber = whatsappNumber;
    }


    public ClassNames getSection() {
        return section;
    }

    public void setSection(ClassNames section) {
        this.section = section;
    }



}
