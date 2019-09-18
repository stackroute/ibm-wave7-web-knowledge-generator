import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpParams } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
  headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
 
  
  constructor(private http: HttpClient) {}

    // authenticate the user as domain expert
    authenticateDomainExpert(userName,userPassword){
      return this.http.get("http://172.23.238.220:9090/api/v1?userName="+userName+"&&userPassword="+userPassword,
      {headers: this.headers});
      }
    // populate data by domain expert
    // populateData(concepts){
    //   return this.http.get("http://172.23.238.226:8402/api/v1/save",concepts,
    //   {headers: this.headers});
    //   }
      populateData(concepts): Observable<any> {

      
        // const body = new HttpParams()
        //     .set(concepts)
      
            console.log("yes");

        return this.http.post("http://172.23.238.226:8402/api/v1/save/"+concepts,
            // body.toString(),
            {
                headers: new HttpHeaders()
                    .set('Content-Type', 'application/x-www-form-urlencoded')
            }
        );
       
    }
  
}
