import {Component, Input, OnInit} from '@angular/core';
import {Computer} from "../computer";
import {HttpErrorResponse} from "@angular/common/http";
import {ComputerService} from "../services/computer.service";
import {UserService} from "../services/user.service";
import {TokenStorageService} from "../services/token-storage.service";
import {UserEntity} from "../user";
import {NgForm} from "@angular/forms";
import {AdminPanelComponent} from "../admin-panel/admin-panel.component";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css',
  '../../assets/bootstrap.min.css']
})
export class CardComponent implements OnInit {
  public computers: Computer[] = [];
  public user: UserEntity;
  public editComputer!: Computer;

  constructor(private computerService: ComputerService, private userService: UserService, private tokenStorageService: TokenStorageService) {
    this.user = tokenStorageService.getUser();
  }

  ngOnInit(): void {
    this.getComputers(this.user.id)
  }

  public onOpenModal(computer: Computer) {
    const container = document.getElementById('main-container')
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#updateReport');
    this.editComputer = computer;
    if(container) {
      container.appendChild(button);
    }
    button.click();
  }

  public onUpdateComputer(computer: Computer) : void {
    this.computerService.updateComputer(computer).subscribe(
      (response: Computer) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public onUpdateReport(message: string) {
    this.editComputer.reportMessage = "\n\nUser: @" + this.user.username
      + " reporting about computer: #" + this.editComputer.name +  "\n\n" + message.valueOf();
    this.editComputer.status = "BROKEN";
    if (this.editComputer.id != null) {
      this.onUpdateComputer(this.editComputer);
    }
  }

  public getComputers(id: number): void {
    this.computerService.getComputersById(id).subscribe(
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
}
