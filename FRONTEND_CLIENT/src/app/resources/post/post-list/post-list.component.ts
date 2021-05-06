import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent{
  @Input() posts: Array<any>;
  @Input() projeto: any = {}
  pageOfItems: Array<any>;
  @Output('post') postSelecionado = new EventEmitter<any>();
  @Output('comentario') comentarioSelecionado = new EventEmitter<any>();

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  selectPost(post:any) {
    this.postSelecionado.emit(post);
  }

  selectComentario(comentario:any) {
    this.comentarioSelecionado.emit(comentario);
  }

  get($event,post){
    post.comentarios.push($event);
    this.pageOfItems.push($event);
  }
    

}
