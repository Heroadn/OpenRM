import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit, Output, EventEmitter } from '@angular/core';
import { UsuarioService } from 'src/app/shared/usuario/usuario.service';
import { GiphyService } from 'src/app/shared/giphy/giphy.service';

@Component({
  selector: 'app-dialog-usuario',
  templateUrl: './dialog-usuario.component.html',
  styleUrls: ['./dialog-usuario.component.css']
})
export class DialogUsuarioComponent implements OnInit{
  usuario:  any;
  usuarios: Array<any>;

  constructor(
    public dialogRef: MatDialogRef<DialogUsuarioComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private usuarioService: UsuarioService, 
    private giphyService:  GiphyService
  ) {}

  ngOnInit() {
    this.usuarioService.getAll().subscribe(data => {
      this.usuarios = data.content;
      for (const usuario of this.usuarios) {
        this.giphyService.get(usuario.nome).subscribe(url => usuario.giphyUrl = url);
      }
    });
  }

  getUsuario(usuario:any){
    this.usuario = usuario;
    this.onNoClick();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}