import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {TokenStorageService} from "../services/token-storage.service";
import {Router} from "@angular/router";
import {UserEntity} from "../user";
import {Form} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css',
    '../../assets/bootstrap.min.css']
})
export class LoginComponent implements OnInit {
  user!: UserEntity;
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  authorities: string[] = [];

  constructor(private router: Router, private authService: AuthService, private tokenStorage: TokenStorageService) { }



  ngOnInit(): void {
    // this.user = this.tokenStorage.getUser();
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.authorities = this.tokenStorage.getUser().authorities;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.authorities = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    // window.location.reload();
    this.router.navigate(['/home']);
  }
}
