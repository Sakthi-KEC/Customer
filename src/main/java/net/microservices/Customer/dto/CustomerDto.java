package net.microservices.Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto
{
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
