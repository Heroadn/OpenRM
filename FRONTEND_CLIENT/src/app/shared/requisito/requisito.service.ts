import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequisitoService {
  public API = environment.url;
  public REQUISITO_API = this.API + '/requisito';

  constructor(private route: ActivatedRoute,private router: Router,private http: HttpClient) { 
  }

  getAll(size = 5,page = 0): Observable<any> {
    return this.http.get(this.REQUISITO_API
        +"?page="+page+"&size="+size);
  }

  get(id: string) {
    return this.http.get(this.REQUISITO_API + '/' + id);
  }

  save(requisito: any): Observable<any> {
    let result: Observable<Object>;

    if (requisito['href']) {
      result = this.http.put(requisito.href, requisito);
    } else {
      result = this.http.post(this.REQUISITO_API, requisito);
    }
    return result;
  }

  adicionarProjeto(href ,requisito:any){
    return this.http.post(href, requisito);
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
