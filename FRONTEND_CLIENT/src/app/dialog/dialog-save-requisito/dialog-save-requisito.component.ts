import { Component, OnInit, EventEmitter, Output, Inject, Input } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UsuarioService } from 'src/app/shared/usuario/usuario.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';

@Component({
  selector: 'app-dialog-save-requisito',
  templateUrl: './dialog-save-requisito.component.html',
  styleUrls: ['./dialog-save-requisito.component.css']
})
export class DialogSaveRequisitoComponent  implements OnInit {
  requisito:any = {}
  @Input() iteracao: any;

  constructor(
    public dialogRef: MatDialogRef<DialogSaveRequisitoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any){}

  ngOnInit() {
    if(this.data.iteracao){
      this.iteracao = this.data.iteracao;
    }
  }

  get(requisito:any){
    this.requisito = requisito;
    this.onNoClick();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
