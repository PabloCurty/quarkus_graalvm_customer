package org.acme.service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {
    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAllCustomers(){
        List<CustomerDTO> customers = new ArrayList<>();
        customerRepository.findAll().stream().forEach(item -> {
            customers.add(mapCustomerEntityToDTO(item));
        });
        return customers;
    }

    public CustomerDTO findCustomerById(long id){
        return mapCustomerEntityToDTO(customerRepository.findById(id));
    }

    public void createNewCustomer(CustomerDTO customerDTO){
        customerRepository.persist(mapCustomerDTOToEntity(customerDTO));
    }

    public void changeCustomer(long id, CustomerDTO customerDTO){
        CustomerEntity customerEntity = customerRepository.findById(id);
        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setAge(customerDTO.getAge());

        customerRepository.persist(customerEntity);
    }

    public void deleteCustomer(long id){
        customerRepository.deleteById(id);
    }

    private CustomerEntity mapCustomerDTOToEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setAge(customerDTO.getAge());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhone(customerDTO.getPhone());
        return customerEntity;

    }

    private CustomerDTO mapCustomerEntityToDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerEntity.getId());
        customerDTO.setAddress(customerEntity.getAddress());
        customerDTO.setAge(customerEntity.getAge());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setPhone(customerEntity.getPhone());
        return customerDTO;
    }
}
