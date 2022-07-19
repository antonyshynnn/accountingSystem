import { Component, OnInit } from '@angular/core';
import {ComputerService} from "../services/computer.service";
import {UserService} from "../services/user.service";
import {TokenStorageService} from "../services/token-storage.service";
import {Computer} from "../computer";
import {HttpErrorResponse} from "@angular/common/http";
import {UserEntity} from "../user";
import {AuthService} from "../services/auth.service";
import {Token} from "@angular/compiler";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css',
    '../../../themify-icons/themify-icons.css']
})
export class HomeComponent implements OnInit {
  public isAdmin !: boolean;

  public user !: UserEntity;
  constructor(private router: Router, private tokenStorageService: TokenStorageService) {
    let token = sessionStorage.getItem('auth-user')
    if (token === null)
      return
    if (token.includes('user:write'))
      this.isAdmin = true;
  }

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
  }

  public navigateToAdminPanel(): void {
    this.router.navigate(['admin']);
  }

  logout(): void {
    this.tokenStorageService.signOut();
    // window.location.reload();
    this.router.navigate(['/', 'login']);
  }

  public onAlert(): void {
    alert("Message")
  }

  scroll(el: HTMLElement) {
    el.scrollIntoView({behavior: "smooth"});
  }


}
