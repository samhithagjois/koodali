import { Routes } from '@angular/router';
import {AdminOverview} from './features/admin/admin-overview';
import {SectionOverview} from './features/admin/section-overview/section-overview';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./features/home-view/home-view')
        .then(m => m.HomeView)
  },

  {
    path:'admin',component : AdminOverview
  },
  {
    path:'admin/section-overview',component : SectionOverview
  },
  {
    path: 'student/student-registration',
    loadComponent: () =>
      import('./features/student/student-registration-form/user-form')
        .then(m => m.UserForm)
  },
  //admin -> button -> sectionOverview -> edit Section -> button -> EditSectionView
  //admin -> button -> studentOverview -> edit Student -> button -> EditStudentView (Student registration)
  //admin -> button -> teacherOverview -> edit Teacher -> button -> EditTeacherView
  //admin -> button -> sectionOverview -> teachers -> text hyperlink -> See Teachers Page
  //admin -> button -> sectionOverview -> students -> text hyperlink -> See Students Page

];
