package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Employee;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.Entities.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeRepo;
import com.udacity.jdnd.course3.critter.user.SceduelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SceduelService {
    @Autowired
    private PetRepo petsRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SceduelRepo sceduelRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    public Schedule saveScheduel(Schedule x, List<Long> empID,List<Long>petIds) {
        List<Employee> employees = employeeRepo.findAllById(empID);
        List<Pet> pets = petsRepo.findAllById(petIds);
        x.setEmployees(employees);
        x.setAllpets(pets);
        return sceduelRepo.save(x);
    }

    public List<Schedule> getScheduelForPet(Long x){
        Pet y = petsRepo.getOne(x);
        return sceduelRepo.getAllByPets(y);
    }


    public List<Schedule> getScheduelForCustomer(Long x){
        Customer y = customerRepo.getOne(x);
        return sceduelRepo.getAllByCustomer(y);
    }

    public List<Schedule> getScheduelForEmployee(Long x){
        Employee y = employeeRepo.getOne(x);
        return sceduelRepo.getAllByEmployees(y);
    }
    public List<Schedule> getAllScheduels() {
        return sceduelRepo.findAll();
    }



}
