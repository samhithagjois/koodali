package service;

public class StudentNotFoundException extends Exception{

    public StudentNotFoundException(){
        super("The student was not found.\n" +
                "please check the student id or if said student is enrolled");
    }
}
