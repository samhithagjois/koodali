import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
//import {HomeView} from './features/home-view/home-view';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `


    <router-outlet></router-outlet>
  `
})
export class AppComponent {}

//code given to me by ChatGPT this is the first time I'm trusting the clanker
// // <app-home-view></app-home-view>
