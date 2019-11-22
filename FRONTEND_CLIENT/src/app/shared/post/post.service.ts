import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  public API = environment.url;
  public POST_API = this.API + '/post';

  constructor(private http: HttpClient) {}

  getAll(size = 5,page = 0): Observable<any> {
    return this.http.get(this.POST_API 
      +"?page="+page+"&size="+size);
  }

  get(id: string) {
    return this.http.get(this.POST_API + '/' + id);
  }

  adicionarComentario(href ,comentario:any){
    return this.http.post(href + "/add-comentario", comentario);
  }

  save(post: any): Observable<any> {
    let result: Observable<Object>;

    if (post['href']) {
      console.log(post['href']);
      result = this.http.put(post.href, post);
    } else {
      result = this.http.post(this.POST_API, post);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
