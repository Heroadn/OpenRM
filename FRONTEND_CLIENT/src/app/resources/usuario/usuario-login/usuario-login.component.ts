import { Component, OnDestroy, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { HttpParams, HttpResponse } from '@angular/common/http';
import { AuthService } from '../../../shared/auth/auth.service';
import { UsuarioSelecionadoService } from 'src/app/shared/usuario/usuario-selecionado.service';

@Component({
  selector: 'app-login',
  templateUrl: './usuario-login.component.html',
  styleUrls: ['./usuario-login.component.css']
})
export class UsuarioLoginComponent implements OnInit {
  @Output('usuario') usuarioSelecionado = new EventEmitter<any>();
  usuario: any = {};
  error: any;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private selecionado: UsuarioSelecionadoService) {
  }

  ngOnInit() {
    if(this.router.url == '/usuario-logout'){
      this.logout() 
    }
  }

  logout(){
    this.authService.logOut()
    this.router.navigate(['/usuario-login']);
  }

  onSubmit(usuario: any) {
    const payload = new HttpParams()
      .set('username', usuario.email)
      .set('password', usuario.senha)
      .set('grant_type', 'password');
      //this.authService.authenticate(payload)
      
      this.loading = true;
      this.authService.authenticate(payload)
          .pipe(first())
          .subscribe(
              data => {
                this.router.navigate(['home']);
              },
              error => {
                  console.log(error)
                  this.error = error;
                  this.loading = false;
              });
  }

  selectUsuario(usuario:any) {
    this.usuarioSelecionado.emit(usuario);
  }

}
