package com.example.controllerbank.Controller;

import com.example.controllerbank.Api.ApiResponse;
import com.example.controllerbank.Model.Bank;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    ArrayList<Bank> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Bank> getCustomers () {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer (@RequestBody Bank customer) {
        customers.add(customer);
        return new ApiResponse("Customer added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer (@RequestBody Bank customer, @PathVariable int index) {
        customers.set(index, customer);
        return new ApiResponse("Customer updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer (@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("Customer deleted successfully");
    }

    @GetMapping("/deposit/{deposit}/{customerId}")
    public ApiResponse deposit (@PathVariable double deposit, @PathVariable int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (i == customerId) {
                customers.get(customerId).setBalance(customers.get(customerId).getBalance() + deposit);
                return new ApiResponse("Deposit made successfully");
            }
        }
        return new ApiResponse ("user not found");
    }

    @GetMapping("/withdraw/{withdraw}/{customerId}")
    public ApiResponse withdraw (@PathVariable double withdraw, @PathVariable int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (i == customerId) {
                customers.get(i).setBalance(customers.get(i).getBalance() - withdraw);
                return new ApiResponse("Withdraw made successfully");
            }
        }
        return new ApiResponse("User not found");
    }

}
