import { Customer } from './../models/models';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CustomersService {
  private readonly customersUrl = environment.customersUrl;

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.customersUrl);
  }

  addCustomers(customer: Customer) {
    return this.http.post(`${this.customersUrl}/create`, customer);
  }
}
