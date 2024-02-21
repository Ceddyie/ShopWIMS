import { Component } from '@angular/core';
import {MatButton} from "@angular/material/button";
import {UserService} from "../../services/user.service";
import {RouterLink} from "@angular/router";

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    standalone: true,
    imports: [
        MatButton,
        RouterLink
    ],
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
    constructor(private userService: UserService) {
    }

    logout() {
        this.userService.user = null;
        localStorage.removeItem('user');
        this.userService.router.navigateByUrl("/login");
    }
}
