package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Entities.Customer;
import com.udacity.jdnd.course3.critter.Entities.Employee;
import com.udacity.jdnd.course3.critter.Entities.Pet;
import com.udacity.jdnd.course3.critter.Service.CustomerService;
import com.udacity.jdnd.course3.critter.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        List<Long> petIds = customerDTO.getPetIds();
        return getCustomerDTO(customerService.saveCustomer(customer, petIds));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map(this::getCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return getCustomerDTO(customerService.getCustomerByPetID(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDaysAvaliable(employeeDTO.getDaysAvailable());
        employee.setEmployeeSkills(employeeDTO.getSkills());
        return getEmployeeDTO(employeeService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return getEmployeeDTO(employeeService.EmployeegetEmployeeByID(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvaliability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getEmployeesForService(employeeDTO.getDate(), employeeDTO.getSkills());
        return employees.stream().map(this::getEmployeeDTO).collect(Collectors.toList());
    }

    private CustomerDTO getCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customer.getNumber());
        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    private EmployeeDTO getEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDaysAvailable(employee.getDaysAvaliable());
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getEmployeeSkills());
        return employeeDTO;
    }


}
