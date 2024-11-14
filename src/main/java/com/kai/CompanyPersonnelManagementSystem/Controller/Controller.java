package com.kai.CompanyPersonnelManagementSystem.Controller;

import com.kai.CompanyPersonnelManagementSystem.DTO.EmployeeDTO;
import com.kai.CompanyPersonnelManagementSystem.Entity.Employee;
import com.kai.CompanyPersonnelManagementSystem.Service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/employees")
public class Controller {

    private final EmployeeService employeeService;

    public Controller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(required = false)Pageable pageable) {

        if (pageable == null) {
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        } else {
            Page<EmployeeDTO> employeeDTOPage =
                    employeeService.getAllEmployees(pageable);
            return new ResponseEntity<>(employeeDTOPage, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return (employeeDTO != null) ? new ResponseEntity<>(employeeDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                      @RequestBody Employee updatedEmployee) {
        EmployeeDTO employeeToUpdate = employeeService.updateEmployee(id, updatedEmployee);
        return (employeeToUpdate != null) ? new ResponseEntity<>(employeeToUpdate, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
