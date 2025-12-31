import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
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

  constructor(private http: HttpClient) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/students', this.user)
      .subscribe(() => {
        alert('Form submitted successfully!');
      });
  }
}
