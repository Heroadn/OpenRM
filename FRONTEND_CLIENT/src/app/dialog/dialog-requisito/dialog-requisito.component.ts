import { Component, OnInit, Inject, Output } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { RequisitoService } from 'src/app/shared/requisito/requisito.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';

@Component({
  selector: 'app-dialog-requisito',
  templateUrl: './dialog-requisito.component.html',
  styleUrls: ['./dialog-requisito.component.css']
})
export class DialogRequisitoComponent implements OnInit {
  requisito: any = {}
  requisitos: Array<any>;

  constructor(
    public dialogRef: MatDialogRef<DialogRequisitoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private requisitoService: RequisitoService, 
    private giphyService:     GiphyService
  ) {}

  ngOnInit() {
    this.requisitoService.getAll().subscribe(data => {
      this.requisitos = data.content;

      console.log(this.requisitos);
      for (const requisito of this.requisitos) {
        this.giphyService.get(requisito.nome).subscribe(url => requisito.giphyUrl = url);
      }
    });
  }

  get(requisito:any){
    this.requisito = requisito;
    this.onNoClick();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
