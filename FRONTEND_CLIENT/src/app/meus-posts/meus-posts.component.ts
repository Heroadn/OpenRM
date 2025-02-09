import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { GiphyService } from '../shared/giphy/giphy.service';
import { PageEvent } from '@angular/material/paginator';
import { PostService } from '../shared/post/post.service';
import { ProjetoService } from '../shared/projeto/projeto.service';

@Component({
  selector: 'app-meus-posts',
  templateUrl: './meus-posts.component.html',
  styleUrls: ['./meus-posts.component.css']
})
export class MeusPostsComponent implements OnInit {
  posts:Array<any>;
  projeto:any = {};
  index  :number = 0;
  size   :number = 5;
  length :number = 5;
  options: number[] = [5, 10, 15, 20];

  constructor(private route: ActivatedRoute, private router: Router, private postService: PostService,private projetoService: ProjetoService, private giphyService: GiphyService) { }
    
    ngOnInit() {
      this.getData(null);
    }

    getData(event: PageEvent){
      let index = (event)? event.pageIndex : this.index;
      let size  = (event)? event.pageSize  : this.size;
      
      this.route.params.subscribe(params => {
        const id = params['id'];
        this.projetoService.get(id).subscribe((projeto: any) => {
          if (projeto) {
            this.projeto = projeto;
            this.posts   = projeto.posts;
            this.projeto.href = projeto._links.self.href;
    
            for (const post of this.posts) {
              post.href = "http:" + this.postService.POST_API + "/" + post.ID;
              this.giphyService.get(post.usuario.nome).subscribe(url => post.usuario.giphyUrl = url);
    
              for (const comentario of post.comentarios) {
                this.giphyService.get(comentario.usuario.nome).subscribe(url => comentario.usuario.giphyUrl = url);
              }
            }
            
          } else {
            console.log(`projeto with id '${id}' not found, returning to list`);
          }
        });
      });

    }

    go(post:any){
      this.router.navigate(['/post-edit',post.ID]);
    }

}
