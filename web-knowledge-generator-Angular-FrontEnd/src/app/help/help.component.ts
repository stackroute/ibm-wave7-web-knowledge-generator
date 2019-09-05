import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-help',
  templateUrl: './help.component.html',
  styleUrls: ['./help.component.css']
})
export class HelpComponent implements OnInit {
  images = [  
    { img: "https://material.angular.io/assets/img/examples/shiba2.jpg" },  
    { img: "https://material.angular.io/assets/img/examples/shiba2.jpg" },  
    { img: "https://material.angular.io/assets/img/examples/shiba2.jpg" },  
    { img: "https://material.angular.io/assets/img/examples/shiba2.jpg" },  
    { img: "https://material.angular.io/assets/img/examples/shiba2.jpg" },  
   ];  
  constructor() { }

  ngOnInit() {
  }

}
