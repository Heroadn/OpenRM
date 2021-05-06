import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './shared/auth/auth.service';
import { GiphyService } from './shared/giphy/giphy.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'RM-Frontend';
  currentUser: any = {};

    constructor(
        private router: Router,
        private authService: AuthService
    ) {
        this.authService.currentUser.subscribe(user => {
          this.currentUser = user;
        });
    }

    logout() {
        this.authService.logOut();
        this.router.navigate(['/login']);
    }
}
