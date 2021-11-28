package com.luv2code.springdemo.controllers;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.errorhandler.CustomHandlerException;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

  private final CustomerService customerService;

  @Autowired
  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customers")
  public List<Customer> getAllCustomers() {
    return customerService.getCustomers(); // Jackson converts it to JSON
  }

  @GetMapping("/customers/{id}")
  public Customer getCustomersById(@PathVariable int id) {

    if (id < 0) {
      throw new CustomHandlerException("Id can't be lower than 0, id: " + id);
    }
    Customer customer = customerService.getCustomer(id);

    if (customer == null) {
      throw new CustomHandlerException("Customer not found, id: " + id);
    }

    return customer;
  }

  @PostMapping("/customers") // jackson creates an object for us from the JSON
  public Customer insertCustomer(@RequestBody Customer customer) {
    customer.setId(0); // this sets the id to non valid hibernate therefore will create a new one
    customerService.saveCustomer(customer);

    return customer;
  }

  // optimally we should do /customers/{id} and check all data before doing that but this also works
  @PutMapping("/customers") // jackson creates an object for us from the JSON
  public Customer updateCustomer(@RequestBody Customer customer) {
    customerService.saveCustomer(customer);
    return customer;
  }

  @DeleteMapping("/customers/{id}")
  public String deleteCustomer(@PathVariable int id) {
    if (id < 0) {
      throw new CustomHandlerException("Id can't be lower than 0, id: " + id);
    }
    Customer customer = customerService.getCustomer(id);

    if (customer == null) {
      throw new CustomHandlerException("Customer not found, id: " + id);
    }

    customerService.deleteCustomer(id);
    return "Customer with id: " + id + " was deleted";
  }
}
