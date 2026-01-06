import {Component, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-section-overview',
  standalone : true,
  imports: [
    RouterLink
  ],
  template: `
    <h3>Section Overview for Administration</h3>

    <div>
      <table class="tg"><thead>
      <tr>
        <th >Section Name</th>
        <th>Section Link or Address</th>
        <th >Teacher(s)</th>
        <th >Students</th>
      </tr></thead>
        <tbody>

        @for (section of sections; track section.id){
        <tr>
          <td> {{section.name }}</td>
        <td>{{section.linkOrAddress}} </td>

        <td>
        <a [routerLink]="['/sections',section.id,'teachers']" routerLinkActive="$router_link-active$" >
        Teachers
        </a>
        </td>
        <td> <a [routerLink]="['/sections',section.id,'students']" routerLinkActive="$router_link-active$" > Students </a> </td>
        <td> <button routerLink="/sections/:id/edit">Edit section</button> </td>
      </tr>
        }
        </tbody>
      </table>
    </div>
    <button routerLink="new"> Add a section</button>
    <button routerLink=".."> Back</button>
  `,
  styleUrl: './section-overview.css',
})
export class SectionOverview implements OnInit {

  sections: any[] = [];
  students : any[] = [];
  teachers : any[] = [];

  constructor(private http: HttpClient,
  private route: ActivatedRoute) {}

  ngOnInit() {
    this.classId = this.route.snapshot.paramMap.get('id')!;
    this.loadSections();
    this.loadStudents();
    this.loadTeachers();

  }

  classId!:string;

  loadTeachers(){
    this.http.get<any[]>(`http://localhost:8080/api/admin/sections/${this.classId}/teachers`)
      .subscribe(data => {
        this.teachers = data;
      });
  }
  loadStudents(){
    this.http.get<any[]>(`http://localhost:8080/api/admin/sections/${this.classId}/students`)
      .subscribe(data => {
        this.students = data;
      });
  }

  loadSections() {
    this.http.get<any[]>(`http://localhost:8080/api/sections`)
      .subscribe(data => {
        this.sections = data;
      });
  }

}
