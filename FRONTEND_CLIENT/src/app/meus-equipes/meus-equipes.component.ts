import { Component, OnInit } from '@angular/core';
import { EquipeService } from '../shared/equipe/equipe.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { PageEvent } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meus-equipes',
  templateUrl: './meus-equipes.component.html',
  styleUrls: ['./meus-equipes.component.css']
})
export class MeusEquipesComponent implements OnInit {
  equipes:Array<any>;
  index  :number = 0;
  size   :number = 5;
  length :number = 5;
  options: number[] = [5, 10, 15, 20];

  constructor(private router: Router, private equipeService: EquipeService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.getData(null);
  }

  getData(event: PageEvent){
    let index = (event)? event.pageIndex : this.index;
    let size  = (event)? event.pageSize  : this.size;

    this.equipeService.getAll(size,index).subscribe(data => {
      this.equipes = data.content;
      this.length = data.totalElements;

      for (const equipe of this.equipes) {
        this.giphyService.get(equipe.nome).subscribe(url => equipe.giphyUrl = url);
      }
    });
  }

  go(equipe:any){
    this.router.navigate(['/equipe-edit',equipe.ID]);
  }

}
