import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { UserForm } from './app/student-registration-form/user-form';

bootstrapApplication(UserForm, appConfig)
  .catch((err) => console.error(err));
