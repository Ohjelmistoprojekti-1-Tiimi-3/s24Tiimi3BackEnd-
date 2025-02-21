package s24.backend.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s24.backend.domain.Customer;
import s24.backend.domain.CustomerRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RestCustomerController {

    @Autowired
    private CustomerRepository customerrepo;

    // Get all customers
    @GetMapping("/customers")
    public Iterable<Customer> getCustomers() {
        return customerrepo.findAll();
    }

    // Get specific customer
    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") Long customerid) {
        return customerrepo.findById(customerid);
    }

    // Adding a new customer
    @PostMapping("/addcustomer")
    public Customer newCustomer(@RequestBody Customer newCustomer) {

        return customerrepo.save(newCustomer);
    }

    // Deleting a customer
    @DeleteMapping("/deletecustomer/{id}")
    public Iterable<Customer> deleteCustomer(@PathVariable("id") Long customerid) {

        customerrepo.deleteById(customerid);
        return customerrepo.findAll();
    }

    // Editing existing customer
    @PutMapping("/customer/{id}")
    Customer editCustomer(@RequestBody Customer editCustomer, @PathVariable("id") Long customerid) {

        editCustomer.setCustomerid(customerid);
        return customerrepo.save(editCustomer);
    }

}
