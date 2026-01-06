import {Component, inject, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {Router, ActivatedRoute, RouterLink} from '@angular/router';


@Component({
  selector: 'app-add-section-form',
  standalone:true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './add-section-form.html',
  styleUrl: './add-section-form.css',
})
export class AddSectionForm implements OnInit{

  section={
    name:'',
    linkOrAddress:''
  }

  private route = inject(ActivatedRoute);
  private router = inject(Router);


  ngOnInit() {
    this.sectionId = this.route.snapshot.paramMap.get('id');

    if (this.sectionId) {
      this.isEditMode = true;
      this.loadSection(this.sectionId);
    }
  }
  isEditMode = false;
  sectionId?: string | null;


  constructor(private http: HttpClient) {}
  onSubmit() {
      if (this.isEditMode) {
        this.updateSection();
      } else {
        this.createSection();
      }
  }

  createSection(){
    this.http.post(`http://localhost:8080/api/admin/sections`, this.section)
      .subscribe(() => {
        {
          this.router.navigate(['/admin/sections'],{ relativeTo: this.route });
        }
      });
  }

  updateSection(){
    this.http.put(`http://localhost:8080/api/sections/${this.sectionId}`, this.section)
      .subscribe(() => {
        {
          this.router.navigate(['/admin/sections'],{ relativeTo: this.route });
        }
      });
  }

  loadSection(id: string) {
    this.http
      .get<any>(`http://localhost:8080/api/sections/${id}`)
      .subscribe(section => {
        this.section = section;
      });
  }
}
