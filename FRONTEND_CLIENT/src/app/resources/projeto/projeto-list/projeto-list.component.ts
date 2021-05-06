import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-projeto-list',
  templateUrl: './projeto-list.component.html',
  styleUrls: ['./projeto-list.component.css']
})
export class ProjetoListComponent{
  @Input() projetos: Array<any>;
  @Output('projeto') projetoEmitter = new EventEmitter<any>();

  selectProjeto(projeto:any) {
    this.projetoEmitter.emit(projeto);
  }

}
