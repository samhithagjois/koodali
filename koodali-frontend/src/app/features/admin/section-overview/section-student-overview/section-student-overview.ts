import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-section-student-overview',
  standalone:true,
  imports: [
    RouterLink
  ],
  templateUrl: './section-student-overview.html',
  styleUrl: './section-student-overview.css',
})
export class SectionStudentOverview {

}
