import { environment } from '../../../environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { nextTick } from 'q';
import { GiphyService } from '../giphy/giphy.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  public CLIENT = 'angular';
  public SECRET = '@hu#nteF$rDF63a3rkn4ssYFou';
  public API    = environment.url;
  public ORIGIN = environment.rootUrl;

  constructor(private http: HttpClient,public giphyService:GiphyService) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(sessionStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
   }

  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }

  authenticate(payload) {
    const headers = {
      'Authorization': 'Basic ' + btoa(this.CLIENT+':'+this.SECRET),
      'Content-type':  'application/x-www-form-urlencoded',
      'Origen': this.ORIGIN
    }

    return this.http.post<any>(this.API+'/oauth/token', payload,{headers})
      .pipe(map((userData:any) => {
          // login successful if there's a jwt token in the response
          if (userData && userData.access_token) {
            const helper = new JwtHelperService();
            let user:any = {}
              // store user details and jwt token in local storage to keep user logged in between page refreshes7jwtService: JwtHelperService
              user = helper.decodeToken(userData.access_token);
              user.token = userData.access_token
              sessionStorage.setItem('currentUser', JSON.stringify(user));
              this.currentUserSubject.next(user);
          }
          
          return userData;
      }));

  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('currentUser')
    return !(user === null)
  }
  
  logOut() {
    sessionStorage.removeItem('currentUser')
    this.currentUserSubject.next(null);
  }
}
