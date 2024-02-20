import { Injectable } from '@angular/core';
import {User} from "../classes/User";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _user: User | null;
  constructor(public router: Router, public httpClient: HttpClient, private matSnackBar: MatSnackBar) {
    this._user = null;
  }

  public get user(): User | null {
    return this._user;
  }

  public set user(user: User | null) {
    this._user = user;
  }

  public loggedIn(): boolean {
    return (this._user != undefined)
  }

  public checkUserExistence(username: string, password: string): User | undefined {
    console.log(username);
    this.httpClient.post<User>('http://localhost:8080/user/login', {
      username: username,
      password: password,
    }, {observe: "response"}).subscribe(response => {
      console.log(response.body);
      if (200 == response.status) {
        this.openSnackBar("Anmeldung erfolgreich", "Okay");
        this.user = response.body;
        console.log(this._user);
        this.router.navigate(["/home"]);
        return this._user;
      }
      return new User(23, "aaa", "aa", "aaa", "aaa", "aaa");
    }, error => {
      if (error.status == 404) {
        this.openSnackBar("Nicht registriert", "Okay");
      };
      if (error.status == 401) {
        this.openSnackBar("Falsches Passwort", "Okay");
      }
    });
    return undefined;
  }

  private openSnackBar(message: string, action: string) {
    this.matSnackBar.open(message, action, {
      duration: 3000,
    });
  }
}
