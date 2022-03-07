package net.microservices.Customer.repository;

import net.microservices.Customer.model.Customer;
import reactor.core.publisher.Flux;


public interface CustomerCustomRepository
{
    Flux<Customer> findProperties(String customerCode,
                                  String customerName,
                                  String customerDomain,
                                  String contractStartDate,
                                  String contractEndDate,
                                  String createdBy);
}
