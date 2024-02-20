import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [
    MatFormField,
    FormsModule,
    MatButton,
    MatInput,
    MatLabel
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public username: any = "";
  public password: any = "";

  constructor(public userService: UserService, public router: Router) { }

  ngOnInit(): void {
  }

  public processLogin() {
    this.userService.checkUserExistence(this.username,this.password);
  }
}
