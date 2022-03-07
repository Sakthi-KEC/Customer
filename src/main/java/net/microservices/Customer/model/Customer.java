package net.microservices.Customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer
{
    @Id
    private String id;

    private String customerCode;
    private String customerName;
    private String customerDomain;
    private String contractStartDate;
    private String contractEndDate;
    private Boolean status;
    private Boolean saved;
    private String createdBy;
    private String createdDate;
}
