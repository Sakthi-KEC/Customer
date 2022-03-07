package net.microservices.Customer.repository;

import net.microservices.Customer.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,String>
{
    Flux<Customer> findBycustomerNameIgnoreCase(String name);
    Flux<Customer> findByStatus(Boolean b);
}
