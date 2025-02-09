import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';

@Component({
  selector: 'app-comentario-list',
  templateUrl: './comentario-list.component.html',
  styleUrls: ['./comentario-list.component.css']
})
export class ComentarioListComponent{
  @Input() comentarios: Array<any>;
  @Output('comentario') comentarioSelecionado = new EventEmitter<any>();

  selectComentario(comentario:any) {
    this.comentarioSelecionado.emit(comentario);
  }

}
