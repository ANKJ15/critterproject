package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Employee;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.Entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SceduelRepo extends JpaRepository<Schedule, Long> {
    List<Schedule> getAllByEmployees(Employee x);
    List<Schedule> getAllByPets(Pet x);
    List<Schedule> getAllByCustomer(Customer x);

}
