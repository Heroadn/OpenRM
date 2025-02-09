import { Component, OnInit } from '@angular/core';
import { ProjetoService } from '../shared/projeto/projeto.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth/auth.service';
import { UsuarioService } from '../shared/usuario/usuario.service';

@Component({
  selector: 'app-meus-projetos',
  templateUrl: './meus-projetos.component.html',
  styleUrls: ['./meus-projetos.component.css']
})
export class MeusProjetosComponent implements OnInit {
  projetos:Array<any> = [];
  usuario:any = {};
  index  : number = 0;
  size   : number = 5;
  length : number = 5;
  options: number[] = [5, 10, 15, 20];

  constructor( private router: Router,private projetoService: ProjetoService, private giphyService: GiphyService,private authService: AuthService,private usuarioservice:UsuarioService) { }

  ngOnInit() {
    this.getData(null);
  }

  getData(event: PageEvent){
    let index = (event)? event.pageIndex : this.index;
    let size  = (event)? event.pageSize  : this.size;

    this.usuarioservice.get(this.authService.currentUserValue.id_usuario).subscribe((data:any)=>{
      this.usuario = data;

      for(let equipe of this.usuario.equipes){
        this.projetos.push(equipe.projeto);
      }

      this.length   = this.projetos.length;

      for (const projeto of this.projetos) {
        this.giphyService.get(projeto.nome).subscribe(url => projeto.giphyUrl = url);
      }
    })
  }

  go(projeto:any){
    this.router.navigate(['/projeto-ver',projeto.ID]);
  }

}
