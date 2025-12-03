import model.*;
import model.Student;
import org.junit.jupiter.api.BeforeAll;

import static model.ClassNames.*;

public class BasicFunctionalityTest {

    @BeforeAll
    public static void adminEnrollStudent(){
        Student sushruth = new Student("NKS_M_001","Sushruth","",MU_ADV);
        Student riddhi = new Student("NKS_M_002","Riddhi","",MU_BEG);
        Student dhriti = new Student("NKS_M_003","Dhriti","",MU_BEG);
        Student parineeta = new Student("NKS_M_004","Parineeta","",MU_ADV);

        Section mu_adv = new Section(MU_ADV);
        Section mu_beg = new Section(MU_BEG);

        Teacher savitri = new Teacher("NKS_T_001","Savitri","",MU_BEG);
        Teacher anita = new Teacher("NKS_T_002","Anita","",MU_ADV);

        Administrator samhitha = new Administrator("NKS_AD_001","Samhitha","Jois");

        samhitha.addPermission(AdminPermissions.MU);
    }
}
