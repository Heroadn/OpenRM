import { Component, OnDestroy, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { GiphyService }     from '../../../shared/giphy/giphy.service';
import { ProjetoService }   from '../../../shared/projeto/projeto.service';
import { RequisitoService } from '../../../shared/requisito/requisito.service';
import { IteracaoService } from 'src/app/shared/iteracao/iteracao.service';

@Component({
  selector: 'app-requisito-edit',
  templateUrl: './requisito-edit.component.html',
  styleUrls: ['./requisito-edit.component.css']
})
export class RequisitoEditComponent implements OnInit, OnDestroy {
  @Input() returnUrl = null;
  @Input() iteracao  = {};
  @Output('requisito') requisitoSelecionado = new EventEmitter<any>();

  requisito: any = {};
  projeto: any = {};
  sub: Subscription;

  constructor(
    private route:  ActivatedRoute,
    private router: Router,
    private requisitoService: RequisitoService,
    private projetoService:   ProjetoService,
    private iteracaoService:  IteracaoService,
    private giphyService:     GiphyService
  ) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      
      if (id) {
        this.requisitoService.get(id).subscribe((requisito: any) => {
          if (requisito) {
            this.requisito = requisito;
            this.projeto   = requisito.projeto;
            this.requisito.href = requisito._links.self.href;

            this.giphyService.get(requisito.nome).subscribe(url => requisito.giphyUrl = url);

            if(this.projeto){
              this.giphyService.get(this.projeto.nome).subscribe(url => this.projeto.giphyUrl = url);
            }
          } else {
            console.log(`requisito with id '${id}' not found, returning to list`);
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
    this.router.navigate(['/requisito-list']);
  }

  save(requisito: any) {

    this.requisitoService.save(requisito).subscribe(result => {
      /*REMOVER*/

      if(this.iteracao){
        this.addRequisitoToIteracao(this.iteracao,requisito);
      }

      if(this.returnUrl){
        this.gotoList();
      }else{
        this.selectRequisito(requisito);
      }
      /*END*/
    }, error => console.error(error));
  }

  addRequisitoToIteracao(iteracao:any,requisito:any){
    
    this.iteracaoService.adicionarRequisito(iteracao.href,requisito).subscribe((result:any) => {
      this.giphyService.get(result.nome).subscribe(url => result.giphyUrl = url);
   }, error => console.error(error));
  }

  remove(href) {
    this.requisitoService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  selectRequisito(requisito:any) {
    this.requisitoSelecionado.emit(requisito);
  }

}
