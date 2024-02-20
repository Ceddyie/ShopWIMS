import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {MatDialog} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";
import {CommonModule, NgIf} from "@angular/common";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  standalone: true,
  imports: [
    MatButton,
    NgIf,
    CommonModule
  ],
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements  OnInit {
  constructor(public userService: UserService, public dialog: MatDialog) {
  }

  ngOnInit() {
    if (!this.userService.loggedIn()) {
      this.userService.router.navigateByUrl("/login");
    }
  }

  public logout() {
    this.userService.user = null;
    this.userService.router.navigateByUrl("/login");
  }

}
