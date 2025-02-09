import { Component, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-meus-comentarios',
  templateUrl: './meus-comentarios.component.html',
  styleUrls: ['./meus-comentarios.component.css']
})
export class MeusComentariosComponent implements OnInit {
  @Output('comentario') comentario;
  comentarios: Array<any>;
  pageOfItems: Array<any>;
  post:any = {}
  currentPage = 1;

  constructor() { }

  ngOnInit() {
  }

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  get($event,post){}
}
