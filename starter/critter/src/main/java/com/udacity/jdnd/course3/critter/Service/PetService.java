package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepo petsRepo;
    @Autowired
    private CustomerRepo customerRepo;

    public Pet savePet(Pet pet, Long ownerId) {
        Customer customer = customerRepo.getOne(ownerId);
        pet.setCustomer(customer);
        pet = petsRepo.save(pet);
        customer.addNewPet(pet);
        customerRepo.save(customer);
        return pet;
    }
    public List<Pet> getPetsByCustomerId(Long customerId){
        return petsRepo.getAllByCustomerId(customerId);
    }
    public List<Pet> getAllPets() {
        return petsRepo.findAll();
    }

    public Pet getPetById(Long petId){
        return petsRepo.getOne(petId);
    }



}
