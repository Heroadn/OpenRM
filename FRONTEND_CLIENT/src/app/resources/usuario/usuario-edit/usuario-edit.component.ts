import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../../../shared/usuario/usuario.service';
import { GiphyService } from '../../../shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../shared/auth/auth.service';

@Component({
  selector: 'app-usuario-edit',
  templateUrl: './usuario-edit.component.html',
  styleUrls: ['./usuario-edit.component.css']
})
export class UsuarioEditComponent implements OnInit, OnDestroy {
  error:   any;
  usuario: any = {};
  sub:     Subscription;

  constructor(
              private route: ActivatedRoute,
              private router: Router,
              private auth: AuthService,
              private usuarioService: UsuarioService,
              private giphyService: GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.usuarioService.get(id).subscribe((usuario: any) => {
          if (usuario) {
            this.usuario = usuario;
            this.usuario.href = usuario._links.self.href;
            this.giphyService.get(usuario.nome).subscribe(url => usuario.giphyUrl = url);
          } else {
            console.log(`usuario with id '${id}' not found, returning to list`);
            this.move();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  move() {
    if(this.auth.isUserLoggedIn){
      this.router.navigate(['/usuario-list']);
    }else{
      this.router.navigate(['/usuario-add']);
    }
  }

  save(form: NgForm) {

    if(form['senha'] == form['senha_confirmar']){
      console.log()
      this.usuarioService.save(form).subscribe(result => {
        this.move();
      }, error => console.error(error));
    }else{
      this.error = 'Verifique as senha digitadas';
    }

    
  }

  remove(href) {
    this.usuarioService.remove(href).subscribe(result => {
      this.move();
    }, error => console.error(error));
  }
}