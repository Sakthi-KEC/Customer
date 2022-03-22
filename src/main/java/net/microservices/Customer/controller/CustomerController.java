package net.microservices.Customer.controller;

import net.microservices.Customer.dto.CustomerDto;
import net.microservices.Customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")

public class CustomerController
{

    @Autowired
    private CustomerService customerService;


    @PostMapping()
    public Mono<CustomerDto> addCustomer(@RequestBody Mono<CustomerDto> truck)
    {
        return customerService.addCustomer(truck);
    }

    @PutMapping("/edit/{id}")
    public Mono<CustomerDto> editCustomer(@RequestBody Mono<CustomerDto> customer, @PathVariable String id)
    {
        return customerService.editCustomer(customer,id);
    }

    @PutMapping("/savedsearch")
    public Mono<CustomerDto> updateSearchedCustomer(@RequestBody Mono<CustomerDto> customer)
    {
        return customerService.updateSearchedCustomer(customer);
    }

    @GetMapping()
    public Flux<CustomerDto> getAllCustomerByStatus()
    {
        return customerService.getAllCustomerByStatus();
    }

    @GetMapping("/search")
    public Flux<CustomerDto> getCustomerByDynamicSearch(@RequestParam(required = false) String customerCode,
                                                        @RequestParam(required = false) String customerName,
                                                        @RequestParam(required = false) String customerDomain,
                                                        @RequestParam(required = false) String contractStartDate,
                                                        @RequestParam(required = false) String contractEndDate,
                                                        @RequestParam(required = false) String createdBy)
    {
        return customerService.getCustomerByDynamicSearch(customerCode,customerName,customerDomain,contractStartDate,contractEndDate,createdBy);
    }

    @GetMapping("/savedsearch/names")
    public Mono<List<String>> getAllCustomerSavedSearchName()
    {
        return customerService.getAllCustomerSavedSearchName();
    }

    @GetMapping("/saved/{name}")
    public Flux<CustomerDto> getSavedTruckByCode(@PathVariable String name)
    {
        return customerService.getSavedCustomerByName(name);
    }

}
