package repository;

public class TeacherNotFoundException extends Exception{

    public TeacherNotFoundException(){
        super("The teacher was not found.\n" +
                "please check the id and if they are registered");
    }
}
