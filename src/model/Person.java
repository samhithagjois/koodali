package model;

public abstract class Person {
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


    protected String firstName, lastName,personID;

    public Person( String personID, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personID = personID;
    }
  /*
    STA
      id : String
      first name : String
      last name : String
      date of birth : String
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
