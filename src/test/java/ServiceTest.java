import koodali.model.*;
import koodali.service.AdminOperationService;
import koodali.service.exceptions.DuplicatePersonException;
import koodali.service.exceptions.IllegalAdminActionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static koodali.model.ClassNames.*;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    static Map<LocalDateTime, Boolean> schedule = new HashMap<>();
    static Administrator samhitha = new Administrator("NKS_AD_001", "Samhitha", "Jois");
    static Administrator vinayak = new Administrator("NKS_AD_002", "Vinayak", "Belavadi");
    private final AdminOperationService adminOperationService = new AdminOperationService();
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
    Teacher manasi = new Teacher("NKS_T_003", "Manasi", "", UNASSIGNED);

    @BeforeEach
    public void fillUpClassAndStudentSchedule() {

        int[] days = {1, 7, 14, 21, 28};

        for (int month = 1; month <= 12; month++) {
            for (int day : days) {
                schedule.put(LocalDateTime.of(2025, month, day, 8, 30), true);
            }
        }


        samhitha.addPermission(AdminPermissions.MU);
        vinayak.addPermission(AdminPermissions.DE);
        vinayak.addPermission(AdminPermissions.IN);


    }

    @Test
    public void adminPermissions() {
        assertEquals(AdminPermissions.MU, samhitha.getPermissions().get(0));
        assertTrue(adminOperationService.checkPermissionToModify(samhitha, mu_adv));
        assertDoesNotThrow(IllegalAdminActionException::new);

    }

    @Test
    public void addPeopleListToSystem() {

        Person s = adminOperationService.addPersonToSystem(sushruth);
        assertEquals("Student", s.getClass().getSimpleName());
        assertEquals(sushruth.getID(), s.getID());

        List<Person> people = List.of(riddhi, dhriti, parineeta, shravya, manasi, savitri, anita, vinayak, samhitha);
        adminOperationService.addListOfPersonsToSystem(people);
        assertArrayEquals(people.toArray(), adminOperationService.addListOfPersonsToSystem(people).toArray());
        assertDoesNotThrow(DuplicatePersonException::new);

    }

    @Test
    public void attendanceQuote() {
        Map<LocalDateTime, Boolean> sushruthSchedule = new HashMap<>();
        int[] attendance = {1, 7, 21};

        for (int month = 1; month <= 12; month++) {
            for (int day : attendance) {
                sushruthSchedule.put(LocalDateTime.of(2025, month, day, 8, 30), true);
            }
        }

        Map<LocalDateTime, Boolean> parineetaSchedule = new HashMap<>();
        int[] attendance1 = {1, 21};

        for (int month = 1; month <= 12; month++) {
            for (int day : attendance1) {
                parineetaSchedule.put(LocalDateTime.of(2025, month, day, 8, 30), true);
            }
        }


    }
}
