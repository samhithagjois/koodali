import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.html'
})
export class UserForm {
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

  }

  constructor(private http: HttpClient) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/students', this.user)
      .subscribe(response => {
        console.log('User data saved successfully:', response);
        alert('Form submitted successfully!');
      });
  }
}

