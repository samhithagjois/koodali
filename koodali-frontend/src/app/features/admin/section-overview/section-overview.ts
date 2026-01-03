import {Component, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {HttpClient} from '@angular/common/http';

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
        <th >Section ID</th>
        <th>Section Name</th>
        <th >Teacher(s)</th>
        <th >Students</th>
        <th>Schedule</th>
      </tr></thead>
        <tbody>

        @for (section of sections; track section.id){
        <tr>
          <td>{{ section.name }}</td>
        <td>{{ section.linkOrAddress }}</td>
        <!--TODO: add a hyperlink to the students of this class!-->
      </tr>
        }
        </tbody>
      </table>


    </div>


    <button routerLink="add-section-form"> Add a section</button>
    <button routerLink=".."> Back</button>
  `,
  styleUrl: './section-overview.css',
})
export class SectionOverview implements OnInit {

  sections: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadSections();
  }

  loadSections() {
    this.http.get<any[]>('http://localhost:8080/api/sections')
      .subscribe(data => {
        this.sections = data;
      });
  }

}
