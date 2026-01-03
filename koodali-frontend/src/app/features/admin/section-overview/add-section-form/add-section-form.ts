import {Component, inject} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router,ActivatedRoute  } from '@angular/router';
@Component({
  selector: 'app-add-section-form',
  standalone:true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './add-section-form.html',
  styleUrl: './add-section-form.css',
})
export class AddSectionForm {

  section={
    name:'',
    linkOrAddress:''
  }

  private route = inject(ActivatedRoute);
  private router = inject(Router);

  constructor(private http: HttpClient) {}
  onSubmit() {
    this.http.post('http://localhost:8080/api/sections', this.section)
      .subscribe(() => {
         {
          this.router.navigate(['..'],{ relativeTo: this.route });
        }
      });
  }
}
