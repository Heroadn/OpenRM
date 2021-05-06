import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class QuoteService {
  api = '//api.quotable.io/';

  constructor(public http: HttpClient) {
  }

  get(searchTerm) {
    const apiLink = this.api + 'random' //+ searchTerm;
    return this.http.get(apiLink).pipe(map((response: any) => {
        return response.content;
    }));
  }
  
}
