import { Component, OnInit, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-save-iteracao',
  templateUrl: './dialog-save-iteracao.component.html',
  styleUrls: ['./dialog-save-iteracao.component.css']
})
export class DialogSaveIteracaoComponent implements OnInit {
  @Input() projeto: any;
  iteracao:any

  constructor(
    public dialogRef: MatDialogRef<DialogSaveIteracaoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any){}

    ngOnInit() {
      if(this.data.projeto){
        this.projeto = this.data.projeto;
      }
    }

    get(iteracao:any){
      this.iteracao = iteracao;
      this.onNoClick();
    }
  
    onNoClick(): void {
      this.dialogRef.close();
    }

}
