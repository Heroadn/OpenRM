import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjetoService } from 'src/app/shared/projeto/projeto.service';
import { EquipeService } from 'src/app/shared/equipe/equipe.service';
import { RequisitoService } from 'src/app/shared/requisito/requisito.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';
import { Subscription } from 'rxjs/internal/Subscription';
import * as moment from 'moment';
import { Observable} from 'rxjs';
import { faCog } from '@fortawesome/free-solid-svg-icons';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';


@Component({
  selector: 'app-projeto-ver',
  templateUrl: './projeto-ver.component.html',
  styleUrls: ['./projeto-ver.component.css']
})
export class ProjetoVerComponent implements OnInit {
  projeto:    any = {};
  //equipes:    Array<any>;
  requisitos: Array<any>;
  iteracoes:  Array<any>;
  tempo:any;
  sub: Subscription;
  clock: Observable<any>;
  dismissible: true;
  days:any;
  hours:any;
  minutes:any;
  seconds:any;
  
  constructor(private route: ActivatedRoute,
    private projetoService:    ProjetoService,
    private equipeService:     EquipeService,
    private requisitoService:  RequisitoService,
    private giphyService:      GiphyService,
    private library:           FaIconLibrary) 
    {
      library.addIcons(
        faCog,
      );
    }

  ngOnInit() {
    this.tempo = new Date()
    this.tempo = this.tempo.toJSON("yyyy/MM/dd HH:mm")
    

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.projetoService.get(id).subscribe((projeto: any) => {
          if (projeto) {
            this.projeto = projeto;
            //this.equipes = projeto.equipes;
            this.requisitos = projeto.backlog;
            this.iteracoes  = projeto.iteracoes;
            this.projeto.href = projeto._links.self.href;

            this.createClock(projeto.data_conclusao)
            
            //Atribuindo foto temporaria
            //for(let equipe of this.equipes){
              //this.giphyService.get(equipe.nome).subscribe(url => equipe.giphyUrl = url);
              //this.equipeService.get(equipe.id).subscribe((url:any) => equipe.href = url._links.self.href);
            //}

            for(let requisito of this.requisitos){
              this.giphyService.get(requisito.nome).subscribe(url => requisito.giphyUrl = url);
              this.requisitoService.get(requisito.id_requisito).subscribe((url:any) => requisito.href = url._links.self.href);
            }
            
            this.giphyService.get(projeto.nome).subscribe(url => projeto.giphyUrl = url);
          } else {
            console.log(`projeto with id '${id}' not found, returning to list`);
          }
        });
      }
    });
  }

  verifiIfExpired(hours){
    let expired = false;
    if(hours < 0){
      expired = true;
    }
    return expired;
  }

  createClock(time){
    const orderDate: number = moment(time, 'YYYY-MM-DD HH:mm:ss').valueOf();

    this.clock = new Observable((subscriber) => 
    {
      subscriber.next(1);
      subscriber.next(2);
      subscriber.next(3);
      setTimeout(() => {
        subscriber.next(4);
        subscriber.complete();
      }, 1000);
      setInterval(() => {
        var elapsed = orderDate - Date.now()
        moment.duration(elapsed)
      }, 1000);
    });

    //this.days    = this.clock.map(date => date.days());
    //this.hours   = this.clock.map(date => date.hours());
    //this.minutes = this.clock.map(date => date.minutes());
    //this.seconds = this.clock.map(date => date.seconds());
  }
}
