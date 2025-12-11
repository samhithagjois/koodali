package model;

import java.util.Date;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String personID;
    protected String city;
    protected String pinCode;
    protected String country;
    protected String fullPostalAdress;
    //new attributes
    protected Date dateOfRegistration, dateOfClassStart;


    protected boolean activeStatus;

    public Person(String firstName, String lastName,
                  String personID, String city, String pinCode,
                  String country, String fullPostalAdress,
                  Date dateOfRegistration, Date dateOfClassStart, boolean activeStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personID = personID;
        this.city = city;
        this.pinCode = pinCode;
        this.country = country;
        this.fullPostalAdress = fullPostalAdress;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfClassStart = dateOfClassStart;
        this.activeStatus = activeStatus;
    }

    public Person(String personID, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return personID;
    }

    public void setID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullPostalAdress() {
        return fullPostalAdress;
    }

    public void setFullPostalAdress(String fullPostalAdress) {
        this.fullPostalAdress = fullPostalAdress;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Date getDateOfClassStart() {
        return dateOfClassStart;
    }

    public void setDateOfClassStart(Date dateOfClassStart) {
        this.dateOfClassStart = dateOfClassStart;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

  /*
    STA
      id : String
      first name : String
      last name : String

      email id : String
      active status : bool
      date of registration : Date
      class start date : Date
      city : String
      Pin Code : String
      country : String
      full postal adress : String

      ST
      section : ClassNames
      amount of textbooks : int

      S
       date of birth : Date
      fathers name : String
      fathers email id : String
      fathers phone number : float
      mothers name : String
      mothers email id : String
      mothers phone number : float

      fees to pay : int
      feed paid : int

      attendance percentage : double
      homework leaderboard score : int
      assessment test result : seperate page
      photo of child : Multipart file
      consent form  : Multipart file

      */


}
