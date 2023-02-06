package org.example.services;

import org.example.models.Customer;
import org.example.repository.CustomerRepository;

import java.util.Collection;
import java.util.Collections;

public class CustomerService {

    public static void addCustomer(String email, String firstName, String lastName){
        var customers = CustomerRepository.getInstance();
        customers.add(new Customer(firstName, lastName, email));
        System.out.println();
    }

    public static Customer getCustomer(String email){
        var customers = CustomerRepository.getInstance();
        for(Customer x : customers){
            if(x.getEmail().equals(email))
                return x;
        }
        return null;
    }

    public static Collection<Customer> getAllCustomers(){
        return CustomerRepository.getInstance().stream().toList();
    }
}
