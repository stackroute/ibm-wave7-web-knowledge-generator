import{Router} from '@angular/router'
import { Component, OnInit, OnDestroy } from '@angular/core';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/map';
import { WebSocketAPI } from '../WebSocketAPI';
import { Observable } from 'rxjs';
import { element } from 'protractor';


@Component({
  selector: 'app-search-result-page',
  templateUrl: './search-result-page.component.html',
  styleUrls: ['./search-result-page.component.css']
})
export class SearchResultPageComponent implements OnInit {


  name: string;
  greeting: string;
  result$: Observable<string>;
  result: any=[];
  suggestions$: Observable<string>;
  suggestion: string;
  
  suggestions: string[];
  title: string;
  arr1: string[];
  questions: string[] = []
  answers: string[] = []
  maincontent:any=[];

  constructor(private router:Router, private webSocketAPI:WebSocketAPI) { }

  ngOnInit() {
      this.result$ = this.webSocketAPI.resultData;
      this.result$.subscribe(data => {
        this.result = data;
        console.log(this.result);
        this.maincontent=[];
        for(var i=0;i<this.result.length;i++){
          if(this.result[i].name.title){
            this.maincontent.push(this.result[i].name.title);
            console.log("title")
          }else if(this.result[i].name.name){
            this.maincontent.push(this.result[i].name.name);
            console.log("name")
          }else{
            this.maincontent.push(this.result[i].name.year);
            console.log("year")
          }
         
        }
        console.log(this.maincontent)
       
      });
    

  
      this.suggestions = []
      this.suggestions$ = this.webSocketAPI.suggestionsData;
      this.suggestions$.subscribe(data => {
        this.suggestion = data;
        console.log(data);
        this.suggestions = JSON.stringify(this.suggestion).replace("{","").replace("}","").replace("\"","").trim().split(',')
        console.log(this.suggestions);
        this.questions = []
        this.answers = []
        this.suggestions.forEach(element => {
          let questionAnswer = element.split(":")
          this.questions.push(questionAnswer[0].replace("\"","").replace("  ","").replace("\"",""))
          this.answers.push(questionAnswer[1])
          console.log(this.questions)
          console.log(this.answers)
        });
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
  

      
    




