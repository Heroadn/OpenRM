import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {
  public API = environment.url;
  public Tarefa_API = this.API + '/tarefa';

  constructor(private route: ActivatedRoute,private router: Router,private http: HttpClient) { 
  }

  getAll(size = 5,page = 0): Observable<any> {
    return this.http.get(this.Tarefa_API
        +"?page="+page+"&size="+size);
  }

  getMyTarefas(size = 5,page = 0): Observable<any> {
    return this.http.get(this.Tarefa_API + "/meus-Tarefas"
        +"?page="+page+"&size="+size);
  }

  get(id: string) {
    return this.http.get(this.Tarefa_API + '/' + id);
  }

  save(tarefa: any): Observable<any> {
    let result: Observable<Object>;

    if (tarefa['href']) {
      result = this.http.put(tarefa.href, tarefa);
    } else {
      result = this.http.post(this.Tarefa_API, tarefa);
    }
    return result;
  }

  /*@href 'link da tarefa'
  */
  remove(href: string) {
    return this.http.delete(href);
  }
}

