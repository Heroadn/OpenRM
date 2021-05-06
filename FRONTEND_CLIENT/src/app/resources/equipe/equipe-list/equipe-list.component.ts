import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { EquipeService } from '../../../shared/equipe/equipe.service';
import { GiphyService } from '../../../shared/giphy/giphy.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-equipe-list',
  templateUrl: './equipe-list.component.html',
  styleUrls: ['./equipe-list.component.css']
})
export class EquipeListComponent{
  @Input() equipes: Array<any>;
  @Output('equipe') equipeEmitter = new EventEmitter<any>();

  selectEquipe(projeto:any) {
    this.equipeEmitter.emit(projeto);
  }

  /*
  constructor(
    private equipeService: EquipeService, 
    private giphyService: GiphyService) { 
  }

  ngOnInit() {
    this.equipeService.getAll().subscribe(data => {
      this.equipes = data;
      for (const equipe of this.equipes) {
        this.giphyService.get(equipe.nome).subscribe(url => equipe.giphyUrl = url);
      }
    });
  }*/

}
