import { Component, OnInit, Input } from '@angular/core';
import { AuthService } from '../shared/auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  dismissible = true;
  usuario:any = {};
  info:any;

  constructor(private authService:    AuthService) { }

  ngOnInit() {
    if(this.authService.isUserLoggedIn()){
      this.usuario.nome = this.authService.currentUserValue.user_name;
    }else{
      this.usuario.nome = null;
    }
  }

}
