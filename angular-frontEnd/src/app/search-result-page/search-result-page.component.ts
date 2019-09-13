import{Router} from '@angular/router'
import { Component, OnInit, OnDestroy } from '@angular/core';
import { SpeechService } from './../speech.service';
import { Subscription } from 'rxjs/Subscription';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/map';
import { WebSocketAPI } from '../WebSocketAPI';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-search-result-page',
  templateUrl: './search-result-page.component.html',
  styleUrls: ['./search-result-page.component.css']
})
export class SearchResultPageComponent implements OnInit {


  name: string;
  greeting: string;
  message$: Observable<string>;
  message: string;

  constructor(private router:Router, private webSocketAPI:WebSocketAPI) { }

  ngOnInit() {
      this.message$ = this.webSocketAPI.data;
      this.message$.subscribe(data => {
        this.message = data;
        this.message = JSON.stringify(this.message).split("content")[3];
        });
    this.webSocketAPI._connect();
  }  
 _connect(){
   
}

disconnect(){
  this.webSocketAPI._disconnect();
}

sendMessage() {
  
  this.webSocketAPI._send(this.name);
}


}
  

      
    




