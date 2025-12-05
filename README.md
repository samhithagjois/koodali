# koodali
My project for the NKS kids so they don't have to submit their stuff on whatsapp anymore. The name is inspired by "Moodle" and is the name of a village in Karnataka where the two rivers Tunga and the Bhadra merge into the Tungabhadra, and also where there are many historically important temples andashrams. Read more about Koodali, the vilage, [here](https://en.wikipedia.org/wiki/Koodli)! 

Sooo here is the plan : 
1. It will be something like moodle but very very simple. The login is done with the student/teachers's ID and the password, which will be assigned to the students in advance. 
2. Then that takes them to the site. It is strictly for homework submission, exchange and download.

3. The students have following view : | View Pending Assignments  | View Graded Assignments

4. The teachers have following view : | Upload Assignment | View Ungraded Assignments | View Graded Assignments | Student Overview
5. The administrators have following view : | Manage Sections | Manage Students | Student Overview | Teachers Overview

6. So the workflow is :
    -> Teacher uploads assignment A for class B
    -> all students of B get A added to their Pending Assignments Tab, and the teacers Student Overview shows "assigned, not read"
    -> Then They click on the pdf file or read the assignment and mark the assignment as read by pressing a little button on the side
    -> This triggers the teacher's Student Overview, which marks the Student's assignment status as "read, not submitted"
    -> Then they do the homework
    -> They then go to the assignment in the pending assignment tab and click on the submit button, which takes them to the upload site
    -> There, they upload their homework, if needed, they change the name to a certain format, and click upload
    -> This triggers following actions : a) removes A from Pending Assignments, b)add student's A to teacher's Ungraded Assignments, and Student Overview, where the status changes to "submitted, not graded"
    -> The teacher goes to View Ungraded Assignments, downloads the assignment, and grades it
    -> The teacher presses the "Grade" button on the View Ungraded Assignment page which takes them to the grading upload site
    -> There, they upload their graded assignment or just leave a text and a score
    -> This triggers the following actions : a) delete A from teachers' View Ungraded Assignment b) add graded A to student's View Graded Assignments c)Student Overview, which now shows "graded"
    -> The student can download it and/or mark the grading as read, which triggers the teachers Student Overview yet again and changes the status to "graded and recieved".



7. The student overview lets teachers see a list of their students that they teach and/or Co-teach
8. Only the administrator(s) can add and remove students/teachers to their respective section. 

Malte tipps : 
Input sanitization
Safety (DDOS protection) (injection protection)
IPV6


