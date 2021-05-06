import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../shared/usuario/usuario.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-meus-usuarios',
  templateUrl: './meus-usuarios.component.html',
  styleUrls: ['./meus-usuarios.component.css']
})
export class MeusUsuariosComponent implements OnInit {
  usuarios:Array<any>
  index  : number = 0;
  size   : number = 5;
  length : number = 5;
  options: number[] = [5, 10, 15, 20];

  constructor(private router: Router,public usuarioService:UsuarioService, public giphyService:GiphyService) { }

  ngOnInit() {
    this.usuarioService.getAll().subscribe(data => {
      this.usuarios = data.content;
      for (const usuario of this.usuarios) {
        this.giphyService.get(usuario.nome).subscribe(url => usuario.giphyUrl = url);
      }
    });
  }

  getData($event){
    
  }

  go(usuario:any){
    this.router.navigate(['/usuario-edit',usuario.ID]);
  }

}
