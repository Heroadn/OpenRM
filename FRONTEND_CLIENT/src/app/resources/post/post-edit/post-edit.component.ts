import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/shared/auth/auth.service';
import { UsuarioService } from 'src/app/shared/usuario/usuario.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';
import { ProjetoService } from 'src/app/shared/projeto/projeto.service';
import { PostService } from 'src/app/shared/post/post.service';

@Component({
  selector: 'app-post-edit',
  templateUrl: './post-edit.component.html',
  styleUrls: ['./post-edit.component.css']
})
export class PostEditComponent implements OnInit {
  projeto: any = {};
  posts: any   = {};
  post:  any   = {}
  sub: Subscription;

  constructor(
              private route: ActivatedRoute,
              private router: Router,
              private auth: AuthService,
              private projetoService: ProjetoService,
              private postService:    PostService,
              private giphyService:   GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.routerSelection(id);
      }
    });
  }

  routerSelection(id:any){
    if(this.route.url['value'][0].path == 'post-add'){//Caso seja uma operação de adição de post
      this.getData(id); 
    }else{ //Caso seja uma operação de autalização de post
      this.postService.get(id).subscribe((post: any) => {
        this.post = post; post.href = "http:"+this.postService.POST_API+"/"+post.ID;
      });
    }
  }

  /*
  O acaso é algo que sempre inspirou nas mentes do sábios ideias, 
  das quais muitas não conseguem ser absorvidas e podem virar superstições, 
  em uma tentativa de explicar esse desafio a logica, cria-se uma ciência chamada probabilidades
  */
  getData(id:any){
    this.projetoService.get(id).subscribe((projeto: any) => {
      if (projeto) {
        this.projeto = projeto;
        this.posts = projeto.posts;

        for(let post of this.posts){post.href = "http:"+this.postService.POST_API+"/"+post.ID}        
        this.projeto.href = "http:"+this.projetoService.PROJETO_API+"/"+projeto.ID
      } else {
        console.log(`projeto with id '${id}' not found, returning to list`);
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  save(form: NgForm) {
    if(form['href']){
        this.postService.save(form).subscribe(result => {
          this.move();
        }, error => console.error(error));
    }else{
      this.projetoService.adicionarPost(this.projeto.href,form).subscribe(result => {
        this.move();
      }, error => console.error(error));
    }
  }

  remove(href) {
    this.postService.remove(href).subscribe(result => {
    }, error => console.error(error));
  }

  move() {
    this.router.navigate(['/projeto-list']);
  }

}
