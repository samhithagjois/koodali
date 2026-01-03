import { Component } from '@angular/core';
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
export class UserForm {
  //code taken from https://engineerscodinghub.com/form-handling-in-angular-and-spring-boot/

  user = {
    firstName: '',
    lastName: '',
    city: '',
    pinCode: '',
    fullPostalAddress: '',
    nearestShaaleLocation:'',
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
  locations = [
    "Erlangen- In-person Shaale"
    , "DE-Online Shaale",
    "Ingolstadt-In Person Shaale",
    "EU-Online Shaale",
    "Munich-In-person Shaale","Unsure"];

  constructor(private http: HttpClient) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/students', this.user)
      .subscribe(() => {
        alert('Form submitted successfully!');
      });
  }
}
