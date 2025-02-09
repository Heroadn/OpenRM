import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { IteracaoService } from '../../../shared/iteracao/iteracao.service';
import { GiphyService } from '../../../shared/giphy/giphy.service';
import { DragulaService } from 'ng2-dragula';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogSaveRequisitoComponent } from 'src/app/dialog/dialog-save-requisito/dialog-save-requisito.component';
import { DialogSaveIteracaoComponent } from 'src/app/dialog/dialog-save-iteracao/dialog-save-iteracao.component';
import { IteracaoMenuComponent } from 'src/app/menu/iteracao-menu/iteracao-menu.component';


@Component({
  selector: 'app-iteracao-list',
  templateUrl: './iteracao-list.component.html',
  styleUrls: ['./iteracao-list.component.css']
})
export class IteracaoListComponent{
  @Input() iteracoes:  Array<any>;
  @Input() requisitos: Array<any>;
  @Input() projeto: any;
  @Output('iteracao') iteracaoEmitter = new EventEmitter<any>();
  menu = IteracaoMenuComponent;

  iteracoes_debug = [
      //ITERACAO_1
      { requisitos:[
        {nome:"Bad Vamp"},
        {nome:"Petrovitch the Slain"},
        {nome:"Petrovitch the Slain"},
        {nome:"Bob of the Everglades"},
        {nome:"The Optimistic Reaper"}
        ] 
      },
      //ITERACAO_1
      { requisitos:[
        {nome:"Dracula"},
        {nome:"Kurz"},
        {nome:"Petrovitch the Slain"},
        {nome:"Vladislav"},
        {nome:"Deacon"}
        ] 
      },
  ];

  constructor(private dragulaService: DragulaService, private dialog: MatDialog) {
    this.dragulaService.destroy("ITERACOES");

    this.dragulaService.createGroup("ITERACOES", {
      // ...
    });

    this.dragulaService.dropModel("ITERACOES").subscribe(args => {
      console.log(args);
    });

    this.dragulaService.drop("ITERACOES").subscribe(args => {
        console.log(args);
    });

    this.dragulaService.drag("ITERACOES").subscribe(args => {
        console.log(args);
    });
  }

  selectIteracao(iteracao:any) {
    this.iteracaoEmitter.emit(iteracao);
  }

  addRequisito(iteracao:any){
    let dialog = this.dialog.open(DialogSaveRequisitoComponent, {data:{iteracao: iteracao}});

      dialog.afterClosed().subscribe((requisito: any) => {
        let req = dialog.componentInstance.requisito
        if(req){
          iteracao.requisitos.push(req)
        }
    })
  }

  addIteracao(projeto:any){
    let dialog = this.dialog.open(DialogSaveIteracaoComponent, {data:{projeto: projeto}});

    dialog.afterClosed().subscribe((iteracao: any) => {
      let req = dialog.componentInstance.iteracao
      if(req){
        this.iteracoes.push(req)
      }
   })
  }

  handleMenuAction($event){

  }
}
