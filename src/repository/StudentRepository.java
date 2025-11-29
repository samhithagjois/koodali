package repository;

import jakarta.persistence.EntityManager;
import model.Student;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.NonNullApi;


public class StudentRepository extends SimpleJpaRepository<Student,String> {

    public StudentRepository(JpaEntityInformation<Student, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

 //findByStudentIdAndStatus(studentId, PENDING)

}
