import {Component, OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './user-form.html'
})
export class UserForm implements OnInit{
  //code taken from https://engineerscodinghub.com/form-handling-in-angular-and-spring-boot/

  user = {
    firstName: '',
    lastName: '',
    city: '',
    pinCode: '',
    fullPostalAddress: '',
    section:'',
    country: '',
    dateOfBirth: '',
    mothersName: '',
    fathersName: '',
    fathersEmailID: '',
    mothersEmailID: '',
    childEmailID: '',
    phoneNumber: '',
    whatsappNumber: '',
  };
  sections : any[] = []

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

  onSubmit() {
    this.http.post('http://localhost:8080/api/students', this.user)
      .subscribe(() => {
        alert('Form submitted successfully!');
      });
  }
}
