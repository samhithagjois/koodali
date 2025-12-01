package controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    //1 : clicks on button "view pending assignments" :
    // GET posting, student/pending -> studentService.getPendingAssignemnts(studentID)
    // new view -> list of assignment(s) with the  most actual one on top
    // List should look like a table : assignment name | view details | assigned on | submission deadline | submit assignment | status
    // student should only be able to click on 'view details' and 'submit'. The rest is text, i.e. non clickable
    // GET posting, /student/assignments/{id} -> studentService.getAssignmentDetails (get Head of pending assignments list)
    // clicks on "view details", new view -> assignment should be seen as name in the title, description, (if given/optional) pdf file/file attachment , submission deadline , and a check box "check if you read this"
    // (IRL student clicks on assignment file and it should open the file explorer and let IRL student choose where to save)
    // once check box is checked, the assignment is marked as "read, not submitted" in the status box.
    // POST /student/assignments/{id}/mark-read -> studentService.markAsRead(studentID, assignmentID)
    // (IRL student downloads pdf and works on assignment)
    // (IRL student is done)
    // clicks on "view pending assignments" and then clicks on submit
    // new view -> submit page
    // POST /student/assignments/{id}/upload
    // button "upload assignment" uploads the file, button "rename file" should be able to rename the file (automatically renamed by the system?)
    // button "save" saves it to attachment variable of Assignment (AssignmentService?)
    // "save" also changes status to "submitted, not graded", removes the assignment from pending assignment view and
    // adds this submitted assignment to section teachers' "view ungraded assignments"


    //2 : clicks on "view graded assignments"
    // new view -> list of assignments that were graded with most recent one on top
    // List should look like a table : assignment name | view details | assigned on | submission deadline | submitted on | status
    // as above student should only be able to click on 'view details'. The rest is text, i.e. non clickable
    // clicks on "view details", new view -> assignment should be seen as name in the title, description, (if given) pdf file/file attachment which is the graded assignment, score/feedback , and a check box "check if you read this"
    // GET /student/assignments/graded
    // (IRL student clicks on attachment and it should open the file explorer and let IRL student choose where to save)
    // once check box is checked, the assignment is marked as "graded and received" in the status box.
    // GET /student/assignments/{id}/graded





}
