import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {
  public API = environment.url;
  public POST_API = this.API + '/comentario';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any> {
    return this.http.get(this.POST_API + '/');
  }

  get(id: string) {
    return this.http.get(this.POST_API + '/' + id);
  }

  save(comentario: any): Observable<any> {
    let result: Observable<Object>;

    if (comentario['href']) {
      result = this.http.put(comentario.href, comentario);
    } else {
      result = this.http.post(this.POST_API, comentario);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
