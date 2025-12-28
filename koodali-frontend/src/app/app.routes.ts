import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./features/home/home-view/home-view')
        .then(m => m.HomeView)
  },

];
