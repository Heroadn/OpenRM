import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ProjetoService } from 'src/app/shared/projeto/projeto.service';
import { EquipeService } from 'src/app/shared/equipe/equipe.service';
import { RequisitoService } from 'src/app/shared/requisito/requisito.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';
import { ComentarioService } from 'src/app/shared/comentario/comentario.service';
import { NgForm } from '@angular/forms';
import { PostService } from 'src/app/shared/post/post.service';

@Component({
  selector: 'app-comentario-edit',
  templateUrl: './comentario-edit.component.html',
  styleUrls: ['./comentario-edit.component.css']
})
export class ComentarioEditComponent implements OnInit {
  @Input() post;
  @Output('comentario') comentarioSelecionado = new EventEmitter<any>();
  comentario: any = {};
  equipes: Array<any>;
  requisitos: Array<any>;
  sub: Subscription;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private projetoService:    ProjetoService,
    private comentarioService: ComentarioService,
    private postService:       PostService,
    private requisitoService:  RequisitoService,
    private giphyService: GiphyService) {}

  ngOnInit() {
    console.log(this.post)
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.comentarioService.get(id).subscribe((comentario: any) => {
          if (comentario) {
            this.comentario = comentario;
            this.comentario.texto = "";
            this.comentario.href = comentario._links.self.href;
            //this.comentario.href = "http://"+ this.comentarioService.POST_API + "/" + this.comentario.ID;    
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
  
  save(form: NgForm) {
    /*O uso de outros serviços deve ser revisto*/
    /*Adiciona o comentario ao post*/
    if(this.post){
      this.postService.adicionarComentario(this.post.href,form).subscribe((result:any) => {
        this.giphyService.get(result.usuario.nome).subscribe(url => result.usuario.giphyUrl = url);
        this.comentarioSelecionado.emit(result);
        (document.querySelector("form") as HTMLFormElement).reset();
      });
    }
  }

  remove(href) {
    this.postService.remove(href).subscribe(result => {
    }, error => console.error(error));
  }

}
