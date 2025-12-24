package koodali.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


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
    private int amountOfTextbooks, feesPaid, pendingFees, homeworkLeaderBoardScore;
    private MultipartFile photo, consentForm;
    private LocalDate dateOfBirth;
    private LocalDateTime dateOfRegistration;
    private LocalDate dateOfFirstClass;

    private String mothersName, fathersName, fathersEmailID, mothersEmailID;
    private Map<LocalDate, Boolean> attendance;
    private String phoneNumber;
    private String whatsappNumber;

    private Map<LocalDate, Integer> homeworkPointsPerWeek;


    public Student(String personID, String firstName, String lastName, ClassNames section) {
        super(personID, firstName, lastName);

        this.section = section;
    }

    public Student(String firstName, String lastName, String personID,
                   String city, String pinCode, String country,
                   String fullPostalAdress, LocalDateTime dateOfRegistration,
                   LocalDateTime dateOfClassStart, boolean activeStatus, ClassNames section,
                   int amountOfTextbooks, int feesPaid, int pendingFees, int homeworkLeaderBoardScore,
                   LocalDate dateOfBirth,
                   LocalDate dateOfFirstClass, String mothersName, String fathersName,
                   String fathersEmailID, String mothersEmailID, Map<LocalDate, Boolean> attendancePercentage,
                   String phoneNumber, String whatsappNumber) {

        super(firstName, lastName, personID,
                city, pinCode, country, fullPostalAdress,
                dateOfRegistration, dateOfClassStart,
                activeStatus);
        this.section = section;
        this.amountOfTextbooks = amountOfTextbooks;
        this.feesPaid = feesPaid;
        this.pendingFees = pendingFees;
        this.homeworkLeaderBoardScore = homeworkLeaderBoardScore;
        this.photo = null;
        this.consentForm = null;
        this.dateOfBirth = dateOfBirth;
        this.dateOfFirstClass = dateOfFirstClass;
        this.mothersName = mothersName;
        this.fathersName = fathersName;
        this.fathersEmailID = fathersEmailID;
        this.mothersEmailID = mothersEmailID;
        this.attendance = attendancePercentage;
        this.phoneNumber = phoneNumber;
        this.whatsappNumber = whatsappNumber;
        this.homeworkPointsPerWeek = new HashMap<LocalDate, Integer>();
    }

    public Student() {
        super();
    }


    public ClassNames getSection() {
        return section;
    }

    public void setSection(ClassNames section) {
        this.section = section;
    }


    public int getAmountOfTextbooks() {
        return amountOfTextbooks;
    }

    public void setAmountOfTextbooks(int amountOfTextbooks) {
        this.amountOfTextbooks = amountOfTextbooks;
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(int feesPaid) {
        this.feesPaid = feesPaid;
    }

    public int getPendingFees() {
        return pendingFees;
    }

    public void setPendingFees(int pendingFees) {
        this.pendingFees = pendingFees;
    }

    public int getHomeworkLeaderBoardScore() {
        return homeworkLeaderBoardScore;
    }

    public void setHomeworkLeaderBoardScore(int homeworkLeaderBoardScore) {
        this.homeworkLeaderBoardScore = homeworkLeaderBoardScore;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public MultipartFile getConsentForm() {
        return consentForm;
    }

    public void setConsentForm(MultipartFile consentForm) {
        this.consentForm = consentForm;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public LocalDate getDateOfFirstClass() {
        return dateOfFirstClass;
    }

    public void setDateOfFirstClass(LocalDate dateOfFirstClass) {
        this.dateOfFirstClass = dateOfFirstClass;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getFathersEmailID() {
        return fathersEmailID;
    }

    public void setFathersEmailID(String fathersEmailID) {
        this.fathersEmailID = fathersEmailID;
    }

    public String getMothersEmailID() {
        return mothersEmailID;
    }

    public void setMothersEmailID(String mothersEmailID) {
        this.mothersEmailID = mothersEmailID;
    }

    public Map<LocalDate, Boolean> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<LocalDate, Boolean> attendance) {
        this.attendance = attendance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public Map<LocalDate, Integer> getHomeworkPointsPerWeek() {
        return homeworkPointsPerWeek;
    }

    public void setHomeworkPointsPerWeek(Map<LocalDate, Integer> homeworkPointsPerWeek) {
        this.homeworkPointsPerWeek = homeworkPointsPerWeek;
    }
}
