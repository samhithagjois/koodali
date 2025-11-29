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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected String firstName, lastName,personID;

    private String password;
    public Person( String personID, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personID = personID;
    }



}
