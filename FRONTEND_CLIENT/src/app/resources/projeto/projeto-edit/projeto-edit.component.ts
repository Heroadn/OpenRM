import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';

import { EquipeService } from '../../../shared/equipe/equipe.service';
import { ProjetoService } from '../../../shared/projeto/projeto.service';
import { GiphyService } from '../../../shared/giphy/giphy.service';

import { DialogEquipeComponent } from '../../../dialog/dialog-equipe/dialog.component-equipe';
import { DialogRequisitoComponent } from '../../../dialog/dialog-requisito/dialog-requisito.component';
import { RequisitoService } from 'src/app/shared/requisito/requisito.service';
import { QuoteService } from 'src/app/shared/quote/quote.service';


@Component({
  selector: 'app-projeto-edit',
  templateUrl: './projeto-edit.component.html',
  styleUrls: ['./projeto-edit.component.css'],
  /*
  providers: [
    {provide: DateAdapter, useClass: CustomDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ]*/
})

export class ProjetoEditComponent implements OnInit, OnDestroy {
  projeto: any = {};
  equipes: Array<any>;
  requisitos: Array<any>;
  iteracoes: Array<any>;
  quote:any = {};
  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private dialog: MatDialog,
              private projetoService:    ProjetoService,
              private equipeService:     EquipeService,
              private requisitoService:  RequisitoService,
              private quoteService:      QuoteService,
              private giphyService:      GiphyService,
              private dateTimeAdapter: DateAdapter<any>) {
  }

  ngOnInit() {
    this.dateTimeAdapter.setLocale('en');

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.projetoService.get(id).subscribe((projeto: any) => {
          if (projeto) {
            this.projeto = projeto;
            this.equipes = projeto.equipes;
            this.requisitos = projeto.backlog;
            this.iteracoes  = projeto.iteracoes;
            this.projeto.href = projeto._links.self.href;
            
            //Atribuindo foto temporaria
            for(let equipe of this.equipes){
              this.giphyService.get(equipe.nome).subscribe(url => equipe.giphyUrl = url);
              this.equipeService.get(equipe.id).subscribe((url:any) => equipe.href = url._links.self.href);
            }

            for(let requisito of this.requisitos){
              this.giphyService.get(requisito.nome).subscribe(url => requisito.giphyUrl = url);
              this.requisitoService.get(requisito.id_requisito).subscribe((url:any) => requisito.href = url._links.self.href);
            }
            
            this.giphyService.get(projeto.nome).subscribe(url => projeto.giphyUrl = url);
          } else {
            console.log(`projeto with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }else{
        this.quoteService.get("").subscribe(quote => {
          this.quote = quote;
          this.projeto.objetivo = ""+this.quote+"";
        });
      }
    });
  }a

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/projeto-list']);
  }

  save(form: NgForm) {
    this.projetoService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  addEquipe(projeto: any) {
    let dialog = this.dialog.open(DialogEquipeComponent)
      dialog.afterClosed().subscribe((equipe: any) => {
        equipe = dialog.componentInstance.equipe
        this.projetoService.adicionarEquipe(projeto.href,equipe).subscribe((result:any) => {
           /*Adiciona a imagem a uma equipe, e automaticamente adiciona a equipe adicionada 
           a lista de equipes do projeto*/
           this.giphyService.get(result.nome).subscribe(url => result.giphyUrl = url);
           this.equipes.push(result)
        }, error => console.error(error));
    })
  }

  addRequisito(projeto: any) {
    let dialog = this.dialog.open(DialogRequisitoComponent)
      dialog.afterClosed().subscribe((requisito: any) => {
        requisito = dialog.componentInstance.requisito
        this.projetoService.adicionarRequisito(this.projeto.href,requisito).subscribe((result:any) => {
           /*Adiciona a imagem a uma equipe, e automaticamente adiciona a equipe adicionada 
           a lista de equipes do projeto*/
           this.giphyService.get(result.nome).subscribe(url => result.giphyUrl = url);
           this.requisitos.push(result)
        }, error => console.error(error));
    })
  }

  removerEquipe(href) {
    this.equipeService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.projetoService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
