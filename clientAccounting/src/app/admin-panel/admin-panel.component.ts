import { Component, OnInit } from '@angular/core';
import {Computer} from "../computer";
import {UserEntity} from "../user";
import {NgForm} from "@angular/forms";
import {ComputerService} from "../services/computer.service";
import {UserService} from "../services/user.service";
import {TokenStorageService} from "../services/token-storage.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

export interface JWTSession {
  authorities: String[];
  id: number;
  token: string;
  type: string;
  username: string;
}

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css',
    '../../assets/bootstrap.min.css']
})
export class AdminPanelComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  jwtSession!: JWTSession;
  isAdmin = false;
  isManager = false;

  public blankPc !: Computer;
  public blankUser !: UserEntity;
  public computers !: Computer[];
  public editComputer !: Computer;
  public addedComputer !: Computer;
  public deleteComputer !: Computer;


  public user !: UserEntity;
  constructor(private authService: AuthService, private router: Router, private computerService: ComputerService, private userService: UserService, private tokenStorageService: TokenStorageService) {
    this.user = this.tokenStorageService.getUser();
  }

  ngOnInit(): void {
    this.getAllComputers();
    let token = sessionStorage.getItem('auth-user')
    if (token === null)
      return
    if (token.includes('user:write'))
      this.isManager = true;
    if (token.includes('user:create'))
      this.isAdmin = true;
  }

  public navToHome() {
    this.router.navigate(['/home']);
  }

  public getAllComputers() {
    this.computerService.getAllComputers().subscribe(
      (response: Computer[]) => {
        this.computers = response;
        this.computers.sort(function (a, b) {
          return b.price - a.price;
        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddUser(addForm: NgForm): void {
    this.isSignUpFailed = false;
    this.authService.register(addForm.value).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        addForm.reset();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
        addForm.reset();
      }
    );
  }

  public onUpdateComputer(computer: Computer) : void {
    if (computer.status === 'OK') {
      computer.reportMessage = '';
    }
    this.computerService.updateComputer(computer).subscribe(
      (response: Computer) => {
        console.log(response);
        this.getAllComputers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getTitle(str: string | undefined): string {
    if (str === undefined)
      return '';
    return str.substring(0, str.indexOf('\n'));
  }

  public getBody(str: string): string {
    return str.substring(str.indexOf('\n'), str.length);
  }

  public getPosition(string: string, subString: string, index: number) {
    return string.split(subString, index).join(subString).length;
  }




  public onAddComputer(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-computer-form').click();
    this.computerService.addComputer(addForm.value).subscribe(
      (response: Computer) => {
        this.addedComputer = response;
        this.getAllComputers();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onDeleteComputer(computerId: number): void {
    this.computerService.deleteComputer(computerId).subscribe(
      (response: void) => {
        console.log(response);
        this.getAllComputers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchComputers(key: string): void {
    console.log(key);
    const results: Computer[] = [];
    for (const toy of this.computers) {
      if (toy.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || toy.type.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || toy.drive.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || toy.operatingSystem.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || toy.coolingSystem.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || toy.cpu.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || toy.status.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || toy.type.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(toy);
      }
    }
    this.computers = results;
    if (!key) {
      this.getAllComputers();
    }
  }

  public onOpenModal(computer: Computer, user: UserEntity, mode: string): void {
    const container = document.getElementById('main-container')
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'addComputer') {
      button.setAttribute('data-target', '#addComputerModal');
    }
    if (mode === 'addUser') {
      button.setAttribute('data-target', '#addUserModal');
    }
    if (mode === 'editComputer') {
      this.editComputer = computer;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'reportMessage') {
      this.editComputer = computer;
      button.setAttribute('data-target', '#reportMessageModal');
    }
    if (mode === 'delete') {
      this.deleteComputer = computer;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    if(container) {
      container.appendChild(button);
    }
    button.click();
  };

  logout(): void {
    this.tokenStorageService.signOut();
    // window.location.reload();
    this.router.navigate(['/', 'login']);
  }
}
