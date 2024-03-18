import {Component, Injectable, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient, HttpResponse,HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Location, LocationStrategy } from "@angular/common";
import {map} from "rxjs/operators";



@Injectable({
  providedIn: 'root'
})
export class WelcomeRequest {
  constructor(private http: HttpClient) {}

  getWelcomeMessages(): Observable<Array<string>> {
    return this.http.get<Array<string>>('http://localhost:8080/welcomeFREN');
  }

}
@Injectable({
  providedIn: 'root'
})
export class TimeZoneRequest {
  constructor(private http: HttpClient) {}

  getTimeZones(): Observable<Map<string, string>> {
    return this.http.get<Map<string, string>>('http://localhost:8080/time')
  }
}


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(
    private httpClient:HttpClient,
    private welcomeRequest:WelcomeRequest,
    private timeZoneRequest:TimeZoneRequest,
    private location:Location,
    private locationStrategy:LocationStrategy
  ){}

  //private baseURL:string='http://localhost:8080';
  private baseURL:string = this.location.path();

  private getUrl:string = this.baseURL + '/room/reservation/v1/';
  private postUrl:string = this.baseURL + '/room/reservation/v1';
  public submitted!:boolean;
  roomsearch! : FormGroup;
  rooms! : Room[];
  request!:ReserveRoomRequest;
  currentCheckInVal!:string;
  currentCheckOutVal!:string;
  public welcomeMessages: Array<string> = new Array<string>();
  public timeZones: Array<string> = new Array<string>();

    ngOnInit(){
      this.roomsearch= new FormGroup({
        checkin: new FormControl(' '),
        checkout: new FormControl(' ')
      });

      this.welcomeRequest.getWelcomeMessages().subscribe(message => {
        this.welcomeMessages = message;
      })

      this.timeZoneRequest.getTimeZones().subscribe(time => {
        this.timeZones = Object.values(time as Map<string, string>);
      })
 //     this.rooms=ROOMS;

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });
  }

    onSubmit({value,valid}:{value:Roomsearch,valid:boolean}){
      this.getAll().subscribe(

        rooms => {console.log(Object.values(rooms)[0]);this.rooms=<Room[]>Object.values(rooms)[0]; }


      );
    }
    reserveRoom(value:string){
      this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

      this.createReservation(this.request);
    }
    createReservation(body:ReserveRoomRequest) {
      let bodyString = JSON.stringify(body); // Stringify payload
      let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON
     // let options = new RequestOptions({headers: headers}); // Create a request option

     const options = {
      headers: new HttpHeaders().append('key', 'value'),

    }

      this.httpClient.post(this.postUrl, body, options)
        .subscribe(res => console.log(res));
    }

  /*mapRoom(response:HttpResponse<any>): Room[]{
    return response.body;
  }*/

    getAll(): Observable<any> {


       return this.httpClient.get(this.baseURL + '/room/reservation/v1?checkin='+ this.currentCheckInVal + '&checkout='+this.currentCheckOutVal, {responseType: 'json'});
    }

  }



export interface Roomsearch{
    checkin:string;
    checkout:string;
  }




export interface Room{
  id:string;
  roomNumber:string;
  price:string;
  links:string;

}
export class ReserveRoomRequest {
  roomId:string;
  checkin:string;
  checkout:string;

  constructor(roomId:string,
              checkin:string,
              checkout:string) {

    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}

/*
var ROOMS: Room[]=[
  {
  "id": "13932123",
  "roomNumber" : "409",
  "price" :"20",
  "links" : ""
},
{
  "id": "139324444",
  "roomNumber" : "509",
  "price" :"30",
  "links" : ""
},
{
  "id": "139324888",
  "roomNumber" : "609",
  "price" :"40",
  "links" : ""
}
] */

