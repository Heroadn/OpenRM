import { Component, OnDestroy, OnInit, NgModule, Input, EventEmitter, Output } from '@angular/core';
import { Subscription, from } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { IteracaoService } from '../../../shared/iteracao/iteracao.service';
import { GiphyService } from '../../../shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';
import { RequisitoService } from '../../../shared/requisito/requisito.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogRequisitoComponent } from 'src/app/dialog/dialog-requisito/dialog-requisito.component';
import { TarefaService } from 'src/app/shared/tarefa/tarefa.service';
import { ProjetoService } from 'src/app/shared/projeto/projeto.service';

@Component({
  selector: 'app-iteracao-edit',
  templateUrl: './iteracao-edit.component.html',
  styleUrls: ['./iteracao-edit.component.css']
})

export class IteracaoEditComponent implements OnInit, OnDestroy {
  @Input() projeto: any;
  @Input() returnUrl = null;
  @Output('iteracao') iteracaoSelecionado = new EventEmitter<any>();
  iteracao: any = {};
  requisitos: Array<any>;
  sub: Subscription;

  constructor(private route:  ActivatedRoute,
              private router: Router,
              private dialog: MatDialog,
              private iteracaoService:   IteracaoService,
              private projetoService:    ProjetoService,
              private requisitoService:  RequisitoService,
              private giphyService:      GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.iteracaoService.get(id).subscribe((iteracao: any) => {
          if (iteracao) {
            this.iteracao = iteracao;
            this.requisitos = iteracao.requisitos;
            this.iteracao.href = iteracao._links.self.href;

            //Atribuindo foto temporaria
            for(let requisito of this.requisitos){
              this.giphyService.get(requisito.nome).subscribe(url => requisito.giphyUrl = url);
              this.requisitoService.get(requisito.id_requisito).subscribe((url:any) => requisito.href = url._links.self.href);
            }
            
            this.giphyService.get(iteracao.nome).subscribe(url => iteracao.giphyUrl = url);
          } else {
            console.log(`iteracao with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/iteracao-list']);
  }

  save(form: NgForm) {
    this.iteracaoService.save(form).subscribe(result => {
        this.iteracao.href = "http:"+this.iteracaoService.ITERACAO_API+"/"+this.iteracao.ID
        this.addIteracao(this.projeto,result);
        this.selectIteracao(result);
    }, error => console.error(error));
  }

  addIteracao(projeto:any, iteracao:any){
    this.projetoService.adicionarIteracao(projeto.href,iteracao).subscribe(result => {
      this.selectIteracao(result);
    }, error => console.error(error));
  }

  addRequisito(iteracao: any) {
    let dialog = this.dialog.open(DialogRequisitoComponent)
      dialog.afterClosed().subscribe((requisito: any) => {
        requisito = dialog.componentInstance.requisito

        this.iteracaoService.adicionarRequisito(iteracao.href,requisito).subscribe((result:any) => {
           /*Adiciona a imagem a um requisito, e automaticamente adiciona o requisito adicionada 
           a lista de requisitos da iteracao*/
           this.giphyService.get(result.nome).subscribe(url => result.giphyUrl = url);
           this.requisitos.push(result)
        }, error => console.error(error));
    })
  }

  removerEquipe(href) {
    this.iteracaoService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.iteracaoService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  selectIteracao(iteracao:any) {
    this.iteracaoSelecionado.emit(iteracao);
  }
}
