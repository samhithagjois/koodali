# koodali 

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
5. Filter functions in the student koodali.view : filter by fees not paid, filter by city, filter by group, filter by active and absent#

