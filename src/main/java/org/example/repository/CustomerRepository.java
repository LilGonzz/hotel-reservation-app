package org.example.repository;

import org.example.models.Customer;

import java.util.HashSet;
import java.util.Set;

public class CustomerRepository {
    private static final Set<Customer> customers = new HashSet<>();

    private CustomerRepository(){}

    public static Set<Customer> getInstance(){
        if (customers == null){
            return new HashSet<Customer>();
        }
        return customers;
    }
}
