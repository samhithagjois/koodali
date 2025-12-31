import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-student-overview',
  standalone : true,
  imports: [
    RouterLink
  ],
  templateUrl: './student-overview.html',
  styleUrl: './student-overview.css',
})
export class StudentOverview {

}
