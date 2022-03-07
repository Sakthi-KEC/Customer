package net.microservices.Customer.repository;

import net.microservices.Customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerCustomRepositoryImpl implements CustomerCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

    @Override
    public Flux<Customer> findProperties(String customerCode,
                                         String customerName,
                                         String customerDomain,
                                         String contractStartDate,
                                         String contractEndDate,
                                         String createdBy)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (customerCode != null && !customerCode.isEmpty())
            criteria.add(Criteria.where("customerCode").is(customerCode));

        if (customerName != null && !customerName.isEmpty())
            criteria.add(Criteria.where("customerName").is(customerName));

        if (customerDomain != null && !customerDomain.isEmpty())
            criteria.add(Criteria.where("customerDomain").is(customerDomain));

        if (contractStartDate != null && !contractStartDate.isEmpty())
            criteria.add(Criteria.where("contractStartDate").is(contractStartDate));

        if (contractEndDate != null && !contractEndDate.isEmpty())
            criteria.add(Criteria.where("contractEndDate").is(contractEndDate));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return template.find(query, Customer.class);
    }
}
