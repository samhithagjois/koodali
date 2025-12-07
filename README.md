# koodali
My project for the NKS kids so they don't have to submit their stuff on whatsapp anymore. The name is inspired by "Moodle" and is the name of a village in Karnataka where the two rivers Tunga and the Bhadra merge into the Tungabhadra, and also where there are many historically important temples andashrams. Read more about Koodali, the vilage, [here](https://en.wikipedia.org/wiki/Koodli)! 

now just a clarification : While ChatGPT is telling me to do things because I ask it for clarification, in the end it is me doing them. While I did have a project back in 2nd sem, it wasn't nearly as extensive as this and this is already bigger than any project i've done in college. So naturally I'll need some helps. 
You can be sure that I wrote all this damned code with my own damn hand. 

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


@RestController
@RequestMapping(path = "/server")

since this is all students, here is the plan
userIDs HAVE to start with a big letter so we can switch that
TO fool anyone, let's do V for vidhyarthi and S for Shikshak, and K for Karyavaha(admin) (shakha styleee)
So an user ID will be something like KPraveen_823?
The we can use a SecureRandom for the number, and play switch with a RandomNumberGenerator and give them a  
UID like that. 

After we give them a basic password, we tell them to change it but keep a similar format (basically play password game with them LOL)
The rules shall be : 
1. Do not include anything that is in your UID in it
2. Include a Taluk of Karnataka in your password
3. Include four numbers, the sum of the numbers has to be greater than 20
4. Include one of the following symbols : 
   - a heart <3
   - a snail _@_
   - a rose --;-@
   - a surprised face 0_0
   - the stars and moon *(C)*
   - a dollar sign $$
IDK IM TIRED I WANT TO BE FINISHED ONCE
