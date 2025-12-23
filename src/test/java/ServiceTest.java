import koodali.model.*;
import koodali.service.AdminOperationService;
import koodali.service.StudentService;
import koodali.service.TeacherService;
import koodali.service.exceptions.IllegalAdminActionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static koodali.model.ClassNames.*;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    private final AdminOperationService adminOperationService = new AdminOperationService();

    static Map<LocalDateTime,Boolean> schedule = new HashMap<>();

    Student sushruth = new Student("NKS_M_001", "Sushruth", "", MU_ADV);
    Student riddhi = new Student("NKS_M_002", "Riddhi", "", MU_BEG);
    Student dhriti = new Student("NKS_M_003", "Dhriti", "", MU_BEG);
    Student parineeta = new Student("NKS_M_004", "Parineeta", "", MU_ADV);

    Student shravya = new Student("NKS_ER_004", "Shravya", "", IN_BEG);

    Section mu_adv = new Section(MU_ADV);
    Section mu_beg = new Section(MU_BEG);
    Section in_beg = new Section(IN_BEG);

    Teacher savitri = new Teacher("NKS_T_001", "Savitri", "", MU_BEG);
    Teacher anita = new Teacher("NKS_T_002", "Anita", "", MU_ADV);

    Teacher manasi = new Teacher("NKS_T_003","Manasi","",UNASSIGNED);

    static Administrator samhitha = new Administrator("NKS_AD_001", "Samhitha", "Jois");
    static Administrator vinayak = new Administrator("NKS_AD_002","Vinayak","Belavadi");

    @BeforeEach
    public void fillUpClassAndStudentSchedule(){
        IntStream.range(1, 12).forEach(i -> schedule.put(LocalDateTime.of(2025, i, 1, 8, 30), true));

        samhitha.addPermission(AdminPermissions.MU);
        vinayak.addPermission(AdminPermissions.DE);
        vinayak.addPermission(AdminPermissions.IN);

    }

    @Test
    public void adminPermissions(){
        assertEquals(AdminPermissions.MU,samhitha.getPermissions().get(0));
        assertTrue(adminOperationService.checkPermissionToModify(samhitha,mu_adv));
        assertDoesNotThrow(IllegalAdminActionException::new);

    }

    @Test
    public void addPersonToSystemTest(){
        Person s = adminOperationService.addPersonToSystem(sushruth);

        assertEquals(sushruth.getID(),s.getID());

    }
}
