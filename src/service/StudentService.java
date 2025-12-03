package service;

import org.springframework.stereotype.Service;
import repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;


    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    //createStudent
    //createStudentsFromList
    //updateStudent

   //findStudentByID
   //listStudentsInSection
    //markAsRead(studentID, assignmentID)
    //getPendingAssignments
    //submitAssignment(id, uploadedFile)


    //-> find existing student, remove from existing section, add to new section (in service!), StudentRepository.save



}
