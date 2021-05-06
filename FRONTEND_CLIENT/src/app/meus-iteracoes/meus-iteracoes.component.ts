import { Component, OnInit, Input } from '@angular/core';
import { retry } from 'rxjs/operators'; 
import { IteracaoService } from '../shared/iteracao/iteracao.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProjetoListComponent } from '../resources/projeto/projeto-list/projeto-list.component';
import { ProjetoService } from '../shared/projeto/projeto.service';
import { TarefaService } from '../shared/tarefa/tarefa.service';
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-meus-iteracoes',
  templateUrl: './meus-iteracoes.component.html',
  styleUrls: ['./meus-iteracoes.component.css']
})
export class MeusIteracoesComponent implements OnInit {
  iteracoes:  Array<any> = [];
  requisitos: Array<any> = [];
  projeto: any = {};
  sub: Subscription;
  @Input() id_projeto:any;

  /*Criar interação no menu de visualização*/

  constructor(private route: ActivatedRoute,private router: Router, private projetoService:ProjetoService, private iteracaoService: IteracaoService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      
        if(id) {
          if(this.id_projeto){
            this.getData(this.id_projeto);
          }else{
            this.getData(id);
          }
        }else{
          this.gotoList();}
        }
      
  );}

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  getData(id){  
    this.projetoService.get(id).subscribe((data:any) => {
      this.projeto      = data;
      this.iteracoes    = data.iteracoes;
      this.projeto.href = data._links.self.href;

      console.log(this.iteracoes)
      if(this.iteracoes){
        for(let iteracao of this.iteracoes){
          iteracao.href = "http:"+this.iteracaoService.ITERACAO_API+"/"+iteracao.ID
        }
      }
    });
  }

  go(iteracao:any){
    this.router.navigate(['/iteracao-edit',iteracao.ID]);
  }

  gotoList() {
    this.router.navigate(['/tarefa-list']);
  }

}
