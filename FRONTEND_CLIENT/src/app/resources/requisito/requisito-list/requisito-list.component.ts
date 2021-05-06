import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RequisitoService } from '../../../shared/requisito/requisito.service';
import { GiphyService } from '../../../shared/giphy/giphy.service';

@Component({
  selector: 'app-requisito-list',
  templateUrl: './requisito-list.component.html',
  styleUrls: ['./requisito-list.component.css']
})
export class RequisitoListComponent {
  @Input() requisitos: Array<any>;
  @Output('requisito') requisitoSelecionado = new EventEmitter<any>();

  selectRequisito(requisito:any) {
    this.requisitoSelecionado.emit(requisito);
  }
}
