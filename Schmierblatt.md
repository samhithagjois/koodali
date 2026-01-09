# koodali
My project for the NKS teachers and admins so they don't have to toggle 543456 excel sheets anymore. 
It used to be a file download and upload site, but after talking, it has now changed,
The name is inspired by "Moodle" and is the name of a village in Karnataka where the two rivers Tunga and the Bhadra merge into the Tungabhadra, and also where there are many historically important temples andashrams. Read more about Koodali, the vilage, [here](https://en.wikipedia.org/wiki/Koodli)! 

now just a clarification : While ChatGPT is telling me to do things because I ask it for clarification, in the end it is me doing them. While I did have a project back in 2nd sem, it wasn't nearly as extensive as this and this is already bigger than any project i've done in college. So naturally I'll need some helps. 
You can be sure that I wrote all this damned code with my own damn hand. 

Sooo here is the plan : 
1. It will be something like moodle but very very simple. The login is done with the student/teachers's ID and the password, which will be assigned to the students in advance. 
2. Then that takes them to the site. It is strictly for homework submission, exchange and download.

3. The students have following koodali.view : | View Pending Assignments  | View Graded Assignments

4. The teachers have following koodali.view : | Upload Assignment | View Ungraded Assignments | View Graded Assignments | Student Overview
5. The administrators have following koodali.view : | Manage Sections | Manage Students | Student Overview | Teachers Overview

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

----------------------------------------------------------------------------------------
Sooooo this is the new README. After talking with Amma, this is the new requirements :
- This is now an administration tool and not a homework upload tool
- The admins have access to the student, section and teacher records. The teachers have access to their sections. Students have no access (except leaderboard?)
- Most of my original classes can stay.
- The Person class is going to change as well as the teacher and student classes. Specifications in the koodali.model
- The koodali.repository classes will (mostly) stay the same
- The koodali.service classes will have way more add/update methods
- The koodali.controller classes will have more fields
-
- The whole thing will still run on Excel files. https://poi.apache.org/components/spreadsheet/quick-guide.html
- There is going to be a leaderboard for homework submissions that can be seen by the teachers (probably also students?)

Functions to be implemented :
1. "Save changes" button saves the updated information back into the excel that was fed in to the system.
   If there is no excel file given, will create own and add the finformation there
2. Search function : find Student by name, find Student by ID
3. Edit button : edits things in student class. update button also writes it back into excel file
4. Teachers : : edit homework leaderboard, add points to each student. Save changes edits the student object and the student koodali.view, and sorts the koodali.view by points
5. Filter functions in the student koodali.view : filter by fees not paid, filter by city, filter by group, filter by active and absent

login to localhost : user and the pw from the log

Other links I will need :
https://spring.io/guides/gs/handling-form-submission form submission

THINGS TO DO (in random order) :
1. ADMIN
    1. Section overview shows all Sections. The info displayed is as follows :
       id| Section Name | Section FUll Name | Taragathi Name |\[Teacher(s)](links to Teacher Overview) |\[Amount Of Students](links to Student Overview) | Edit Section (button)(links to Section edit Form)


------------------------------------------------------------------------------------------