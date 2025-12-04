import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.AdminRepository;
import repository.SectionRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import static model.ClassNames.MU_ADV;
import static model.ClassNames.MU_BEG;

public class BasicFunctionalityTest {

    /**
     * This test class will only test the model + repository
     * Service + Repository to be tested in a seperate class, controller + view to be tested with mock
     * */

    Student sushruth = new Student("NKS_M_001", "Sushruth", "", MU_ADV);
    Student riddhi = new Student("NKS_M_002", "Riddhi", "", MU_BEG);
    Student dhriti = new Student("NKS_M_003", "Dhriti", "", MU_BEG);
    Student parineeta = new Student("NKS_M_004", "Parineeta", "", MU_ADV);

    Section mu_adv = new Section(MU_ADV);
    Section mu_beg = new Section(MU_BEG);

    Teacher savitri = new Teacher("NKS_T_001", "Savitri", "", mu_beg);
    Teacher anita = new Teacher("NKS_T_002", "Anita", "", mu_adv);

    static Administrator samhitha = new Administrator("NKS_AD_001", "Samhitha", "Jois");

    StudentRepository studentRepo = new StudentRepository();
    TeacherRepository teacherRepo = new TeacherRepository();
    AdminRepository adminRepo = new AdminRepository();
    SectionRepository sectionRepo = new SectionRepository();

    @BeforeAll
    public static void adminRights() {
        samhitha.addPermission(AdminPermissions.MU);
    }


    @Test
    public void addStudents() {
       studentRepo.add(sushruth);
       studentRepo.add(riddhi);
       studentRepo.add(dhriti);
       studentRepo.add(parineeta);

    }
}
