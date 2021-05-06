import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, Inject } from '@angular/core';
import { EquipeService } from 'src/app/shared/equipe/equipe.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog-equipe.component.html',
  styleUrls: ['./dialog-equipe.component.css']
})

export class DialogEquipeComponent{
  equipe: any = {}
  equipes: Array<any>;

  constructor(
    public dialogRef: MatDialogRef<DialogEquipeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private equipeService:  EquipeService, 
    private giphyService:   GiphyService
  ) {}

  ngOnInit() {
    this.equipeService.getAll().subscribe(data => {
      this.equipes = data.content;
      for (const equipe of this.equipes) {
        this.giphyService.get(equipe.nome).subscribe(url => equipe.giphyUrl = url);
      }
    });
  }

  get(equipe:any){
    this.equipe = equipe;
    this.onNoClick();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}


