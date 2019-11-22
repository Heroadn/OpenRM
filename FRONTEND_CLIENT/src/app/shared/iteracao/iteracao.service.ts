import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class IteracaoService {
  public API = environment.url;
  public ITERACAO_API = this.API + '/iteracao';

  constructor(private route: ActivatedRoute,private router: Router,private http: HttpClient) { 
  }

  getAll(): Observable<any> {
    return this.http.get(this.ITERACAO_API + '/');
  }

  get(id: string) {
    return this.http.get(this.ITERACAO_API + '/' + id);
  }

  save(ITERACAO: any): Observable<any> {
    let result: Observable<Object>;

    if (ITERACAO['href']) {
      result = this.http.put(ITERACAO.href, ITERACAO);
    } else {
      result = this.http.post(this.ITERACAO_API, ITERACAO);
    }

    return result;
  }

  adicionarRequisito(href ,requisito:any){
    return this.http.post(href, requisito);
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
