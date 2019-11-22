import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RequisitoService } from '../shared/requisito/requisito.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { PageEvent } from '@angular/material';

@Component({
  selector: 'app-meus-requisitos',
  templateUrl: './meus-requisitos.component.html',
  styleUrls: ['./meus-requisitos.component.css']
})
export class MeusRequisitosComponent implements OnInit {
  requisitos:Array<any>;
  index  :number = 0;
  size   :number = 5;
  length :number = 5;
  options: number[] = [5, 10, 15, 20];

  constructor( private router: Router,private requisitoService: RequisitoService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.getData(null);
  }

  getData(event: PageEvent){
    let index = (event)? event.pageIndex : this.index;
    let size  = (event)? event.pageSize  : this.size;

    this.requisitoService.getAll(size,index).subscribe(data => {
      this.requisitos = data.content;
      this.length = data.totalElements;

      for (const requisito of this.requisitos) {
        this.giphyService.get(requisito.nome).subscribe(url => requisito.giphyUrl = url);
      }
    });
  }

  go(requisito:any){
    this.router.navigate(['/requisito-edit',requisito.ID]);
  }

}
