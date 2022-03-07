package net.microservices.Customer.appUtil;

import net.microservices.Customer.dto.CustomerDto;
import net.microservices.Customer.model.Customer;
import org.springframework.beans.BeanUtils;


public class AppUtil
{
    public static Customer DtoToEntity(CustomerDto dto)
    {
        Customer entity=new Customer();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static CustomerDto EntityToDto(Customer entity)
    {
        CustomerDto dto=new CustomerDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
