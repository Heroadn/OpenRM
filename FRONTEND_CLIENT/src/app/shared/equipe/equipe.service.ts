import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class EquipeService {
  public API = environment.url;
  public EQUIPE_API = this.API + '/equipe';

  constructor(private route: ActivatedRoute,private router: Router,private http: HttpClient) { 
  }

  getAll(size = 5,page = 0): Observable<any> {
    return this.http.get(this.EQUIPE_API + "?page="+page+"&size="+size);
  }

  get(id: string) {
    return this.http.get(this.EQUIPE_API + '/' + id);
  }

  save(equipe: any): Observable<any> {
    let result: Observable<Object>;
    
    if (equipe['href']) {
      result = this.http.put(equipe.href, equipe);
    } else {
      result = this.http.post(this.EQUIPE_API, equipe);
    }

    return result;
  }

  adicionarUsuario(href, usuario:any){
    return this.http.post(href, usuario);
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
