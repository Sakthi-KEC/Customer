package net.microservices.Customer.service;

import net.microservices.Customer.appUtil.AppUtil;
import net.microservices.Customer.dto.CustomerDto;
import net.microservices.Customer.exception.CustomException;
import net.microservices.Customer.repository.CustomerCustomRepository;
import net.microservices.Customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private CustomerCustomRepository customRepo;


    // Adding Truck to the Repository
    public Mono<CustomerDto> addCustomer(Mono<CustomerDto> customer)
    {
        return  customer.map(AppUtil::DtoToEntity)
                .flatMap(customerRepo::insert)
                .map(AppUtil::EntityToDto);
    }

    // Editing Customer by updating the old record and inserting the new record
    public Mono<CustomerDto> editCustomer(Mono<CustomerDto> customer, String id)
    {
        Mono<CustomerDto> r = customerRepo.findById(id).map(AppUtil::EntityToDto);

        customerRepo.findById(id)
                .flatMap(p -> r.map(AppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(customerRepo::save)
                .map(AppUtil::EntityToDto).subscribe();

        return customer.map(AppUtil::DtoToEntity)
                .flatMap(customerRepo::insert)
                .map(AppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Customer
    public Mono<CustomerDto> updateSearchedCustomer(Mono<CustomerDto> customer)
    {
        return  customer.map(AppUtil::DtoToEntity)
                .flatMap(customerRepo::save)
                .map(AppUtil::EntityToDto);
    }

    // Getting all Customer from the Repository
    public Flux<CustomerDto> getAllCustomerByStatus()
    {
        return customerRepo.findByStatus(true).map(AppUtil::EntityToDto);
    }

    // Getting All Customer by dynamically giving any of the fields
    public Flux<CustomerDto> getCustomerByDynamicSearch(String customerCode, String customerName, String customerDomain, String contractStartDate, String contractEndDate, String createdBy)
    {
        return customRepo.findProperties(customerCode,customerName,customerDomain,contractStartDate,contractEndDate,createdBy)
                .filter(a->a.getStatus()==true)
                .map(AppUtil::EntityToDto);
    }

    // Getting all the customerName of Searched Truck
    public Mono<List<String>> getAllCustomerSavedSearchName()
    {
        return customerRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(AppUtil::EntityToDto)
                .map(a->a.getCustomerName())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Customer which saved status is updated
    public Flux<CustomerDto> getSavedCustomerByName(String name)
    {
        return customerRepo.findBycustomerNameIgnoreCase(name)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(AppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Customer Name"))));
    }
}
