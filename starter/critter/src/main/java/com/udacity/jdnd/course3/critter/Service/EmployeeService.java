package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Employee;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private PetRepo petsRepo;
    @Autowired
    private EmployeeRepo  employeeRepo;

    public Employee saveEmployee(Employee x) {
        return employeeRepo.save(x);
    }

    public void setEmployeeAvaliability(Set<DayOfWeek> days, Long employeeId){
        Employee employee = employeeRepo.getOne(employeeId);
        employee.setDaysAvaliable(days);
        employeeRepo.save(employee);

    }
    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills){
        List<Employee> employees = employeeRepo.getAllByDaysAvaliable(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getEmployeeSkills().containsAll(skills))
                .collect(Collectors.toList());
        return employees;
    }
    public Employee EmployeegetEmployeeByID(Long x) {
        return employeeRepo.getOne(x);

    }


}
