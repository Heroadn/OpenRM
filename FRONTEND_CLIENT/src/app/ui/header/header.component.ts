import { Component, OnInit, Input } from '@angular/core';
import { AuthService } from 'src/app/shared/auth/auth.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';
import { UsuarioSelecionadoService } from 'src/app/shared/usuario/usuario-selecionado.service';
import { tap } from 'rxjs/operators';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import 'rxjs/add/observable/forkJoin';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  @Input() usuario:any;
  public imagem:any = {};

  constructor(
    private authService: AuthService,
    private giphyService: GiphyService,
    private selecionarUsuario: UsuarioSelecionadoService
  ) { }


  async getImage(){
    let result = await this.giphyService.get(this.usuario.user_name).toPromise().then((result) => {
      this.imagem = result;
    });
    return result;
  }
  
  ngOnInit(): void {
    if(this.usuario && this.authService.currentUserValue){
        this.imagem = this.getImage();
    }
  }

  options(){
    console.log(this.usuario.nome)
  }
  

}
