package model;

import java.util.LinkedHashMap;

public class Section {
    private ClassNames name;
    private LinkedHashMap<String,Student> students;
    private LinkedHashMap<String,Teacher> teachers;

    public void addStudent(String studentID, Student student){
        if(!students.containsKey(studentID)){
            students.put(studentID,student);
        }else{

        }
        // TO DO!
    }


}
