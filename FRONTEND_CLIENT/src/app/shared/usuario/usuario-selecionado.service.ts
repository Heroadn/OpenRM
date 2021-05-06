import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioSelecionadoService {
  usuario:any = {}
  selecionado:Observable<any>;

  constructor() { 
    this.selecionado = new Observable((observer)=>{
      const {next, error} = observer;

      if(this.usuario.access_token){
        console.log('XD')
        observer.next(this.usuario);
        observer.complete()
        return {unsubscribe() { this.usuario }};
      }
    });
  }
}
