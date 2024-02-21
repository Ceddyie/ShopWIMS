import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {MatSlideToggle} from "@angular/material/slide-toggle";
import {NgIf} from "@angular/common";
import {MatButtonToggle, MatButtonToggleGroup} from "@angular/material/button-toggle";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [
    MatFormField,
    FormsModule,
    MatButton,
    MatInput,
    MatLabel,
    MatSlideToggle,
    NgIf,
    MatButtonToggle,
    MatButtonToggleGroup
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public username: any = "";
  public password: any = "";
  isChecked: boolean = false;
  public email: any = "";
  firstName: any = "";
  usernameRegister: any = "";
  passwordRegister: any = "";
  lastName: any = "";

  constructor(public userService: UserService, public router: Router) { }

  ngOnInit(): void {
  }

  public processLogin() {
    this.userService.checkUserExistence(this.username,this.password);
  }

  processRegistration() {
    this.userService.registerUser(this.usernameRegister, this.email, this.passwordRegister, this.firstName, this.lastName);
    this.isChecked = false;
  }
}
