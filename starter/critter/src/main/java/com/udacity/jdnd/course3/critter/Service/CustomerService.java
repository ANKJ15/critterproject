package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private PetRepo petsRepo;
    @Autowired
    private CustomerRepo customerRepo;

    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map((petId) -> petsRepo.getOne(petId)).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return customerRepo.save(customer);
    }

    public Customer getCustomerByPetID(Long x){
        return petsRepo.getOne(x).getCustomer();
    }
    public List<Customer>getAllCustomers()
    {
        return customerRepo.findAll();
    }

    public void getOwnerByPet(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer getCustomerById(Long x){
        return customerRepo.getOne(x);
    }
}
