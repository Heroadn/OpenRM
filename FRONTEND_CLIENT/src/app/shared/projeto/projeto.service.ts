import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProjetoService {
  public API = environment.url;
  public PROJETO_API = this.API + '/projeto';

  constructor(private route: ActivatedRoute,private router: Router,private http: HttpClient) { 
  }

  getAll(size = 5,page = 0): Observable<any> {
    return this.http.get(this.PROJETO_API
        +"?page="+page+"&size="+size);
  }

  getMyProjects(size = 5,page = 0): Observable<any> {
    return this.http.get(this.PROJETO_API + "/meus-projetos"
        +"?page="+page+"&size="+size);
  }

  get(id: string) {
    return this.http.get(this.PROJETO_API + '/' + id);
  }

  save(projeto: any): Observable<any> {
    let result: Observable<Object>;

    if (projeto['href']) {
      result = this.http.put(projeto.href, projeto);
    } else {
      result = this.http.post(this.PROJETO_API, projeto);
    }
    return result;
  }

  /*@href 'link do projeto'
  * @equipe 'equipe a ser adicionada ao projeto'
  */
  adicionarEquipe(href ,equipe:any){
    return this.http.post(href + "/add-equipe", equipe);
  }

  /*@href 'link do projeto'
  * @requisito 'requisito a ser adicionada ao projeto'
  */
  adicionarRequisito(href ,requisito:any){
    return this.http.post(href + "/add-requisito", requisito);
  }

  /*@href 'link do projeto'
  * @iteracao 'iteracao'
  */
  adicionarIteracao(href ,iteracao:any){
  return this.http.post(href + "/add-iteracao", iteracao);
  }

  /*@href 'link do projeto'
  * @post 'post a ser adicionado ao projeto'
  */
  adicionarPost(href ,post:any){
    return this.http.post(href + "/add-post", post);
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
