import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material'
import { MatProgressSpinnerModule } from '@angular/material';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router, private _loginService:LoginServiceService) { }
username: string;
password: string;
result: boolean;
  ngOnInit() {
  }
  login() : void {

    this._loginService.authenticateDomainExpert(this.username,this.password).
    subscribe(data=>{if(data==true){
      localStorage.setItem('username', this.username);
      localStorage.setItem('password', this.password);
      this.router.navigate(["domainexpert"]);
  }else {
    this.result=true;
  //  alert("Invalid credentials");
  } console.log(this.result);});  
    // if(this.result.equals("true"))
    // {
    //   this.router.navigate(["help"]);
    // }else {
    //   alert("Invalid credentials");
    // }
    }

  //   if(this.username == 'admin' && this.password == 'admin'){
  //    this.router.navigate(["help"]);
  //   }else {
  //     alert("Invalid credentials");
  //   }
  // }
  }
