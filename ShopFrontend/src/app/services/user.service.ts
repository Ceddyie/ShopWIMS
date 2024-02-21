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
    this.httpClient.post<User>('http://localhost:8082/user/login', {
      username: username,
      password: password,
    }, {observe: "response"}).subscribe(response => {
      console.log(response.body);
      if (200 == response.status) {
        this.openSnackBar("Login successful", "Okay");
        this.user = response.body;
        console.log(this._user);
        this.router.navigate(["/home"]);
        return this._user;
      }
      return new User(23, "aaa", "aa", "aaa", "aaa", "aaa");
    }, error => {
      if (error.status == 404) {
        this.openSnackBar("Not registered", "Okay");
      };
      if (error.status == 401) {
        this.openSnackBar("Wrong password", "Okay");
      }
    });
    return undefined;
  }

  private openSnackBar(message: string, action: string) {
    this.matSnackBar.open(message, action, {
      duration: 3000,
    });
  }

  registerUser(username: any, email: any, password: any, firstName: any, lastName: any) {
    this.httpClient.post<User>('http://localhost:8082/user/register', {
      username: username,
      email: email,
      password: password,
      firstName: firstName,
      lastName: lastName
    }, {observe: "response"}).subscribe(response => {
      console.log(response.body);
      if (response.status == 201) {
        this.openSnackBar("Registration successful", "Okay");
        this.user = response.body;
        console.log(this._user);
      }
    }, error => {
      if (error.status == 409) {
        this.openSnackBar("Email/Username already in use", "Okay");
      }
    })
  }
}
