import {Routes} from '@angular/router';
import {AdminOverview} from './features/admin/admin-overview';
import {SectionOverview} from './features/admin/section-overview/section-overview';
import {UserForm} from './features/student/student-form/user-form';
import {StudentOverview} from './features/student/student-overview';
import {TeacherOverview} from './features/teacher/teacher-overview';
import {AdminTeacherOverview} from './features/admin/admin-teacher-overview/teacher-overview';
import {Leaderboard} from './features/leaderboard/leaderboard';
import {AdminStudentOverview} from './features/admin/admin-student-overview/student-overview';
import {TeacherForm} from './features/teacher/teacher-form/teacher-form';
import {AddSectionForm} from './features/admin/section-overview/add-section-form/add-section-form';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./features/home-view/home-view')
        .then(m => m.HomeView)
  },

  {
    path: 'admin', component: AdminOverview
  },
  {
    path: 'student', component: StudentOverview
  },
  {
    path: 'teacher', component: TeacherOverview
  },
  {
    path: 'leaderboard', component: Leaderboard
  },
  {
    path: 'student/edit-profile', component: UserForm
  },
  {
    path: 'admin/section-overview', component: SectionOverview
  },
  {
    path: 'admin/teacher-overview', component: AdminTeacherOverview
  },
  {
    path: 'admin/student-overview', component: AdminStudentOverview
  },
  {
    path: 'student/student-registration', component: UserForm
  },
  {
    path: 'teacher/edit-teacher-profile', component: TeacherForm
  },
  {
    path: 'admin/section-overview/add-section-form', component: AddSectionForm
  },
  //admin -> (Sections Overview) button -> sectionOverview -> edit Section -> button -> EditSectionView
  //admin -> (Student Overview) button -> studentOverview -> edit Student -> button -> EditStudentView (Student registration)
  //admin -> (Teacher Overview) button -> teacherOverview -> edit Teacher -> button -> EditTeacherView
  //admin -> (Add Person) button -> (Form) Add Person Page ->
  //admin -> (Sections Overview) button -> sectionOverview -> teachers -> text hyperlink -> See Teachers Of Section Page -> (Add Teachers) button -> Text field -> search for Teacher name among people in the system
  //admin -> button -> sectionOverview -> students -> text hyperlink -> See Students Page


  //teacher ->


  //student -> button -> Leaderboard

];
