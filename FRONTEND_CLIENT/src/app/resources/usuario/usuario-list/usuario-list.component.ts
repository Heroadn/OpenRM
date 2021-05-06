import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent{
  @Input() usuarios: Array<any>;
  @Output('usuario') usuarioSelecionado = new EventEmitter<any>();

  selectUsuario(usuario:any) {
    this.usuarioSelecionado.emit(usuario);
  }
}