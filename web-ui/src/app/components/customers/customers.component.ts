import { CustomersService } from './../../services/customers-service.service';
import { Observable, throwIfEmpty } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/models';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss'],
})
export class CustomersComponent implements OnInit {
  customerFormGroup = new FormGroup({
    name: this.formBuilder.control(''),
    surname: this.formBuilder.control(''),
    pesel: this.formBuilder.control(''),
  });

  customers$: Observable<Customer[]> | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private customersService: CustomersService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.customers$ = this.customersService.getCustomers();
  }

  initForm() {
    this.customerFormGroup = this.formBuilder.group({
      name: this.formBuilder.control(''),
      surname: this.formBuilder.control(''),
      pesel: this.formBuilder.control(''),
    });
  }

  addCustomer() {
    const name = this.customerFormGroup.get('name').value;
    const surname = this.customerFormGroup.get('surname').value;
    const pesel = this.customerFormGroup.get('pesel').value;

    const customer = <Customer> <unknown> { name, surname, pesel };
    this.customersService.addCustomers(customer).subscribe({
      error: err => console.log(err)
    });
  }
}
