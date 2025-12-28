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
  /*  path: '/admin/admin-overview',
    loadComponent: () =>
      import('./features/admin/admin-overview')
        .then(m => m.AdminOverview)*/
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

];
