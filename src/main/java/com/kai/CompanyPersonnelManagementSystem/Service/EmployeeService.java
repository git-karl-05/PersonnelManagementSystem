package com.kai.CompanyPersonnelManagementSystem.Service;

import com.kai.CompanyPersonnelManagementSystem.DTO.EmployeeDTO;
import com.kai.CompanyPersonnelManagementSystem.Entity.Employee;
import com.kai.CompanyPersonnelManagementSystem.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDepartmentName(employee.getDepartment().getName());
        return dto;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public EmployeeDTO createEmployee(Employee employeeToSave) {
        Employee savedEmployee = employeeRepository.save(employeeToSave);
        return convertToDTO(savedEmployee);
    }
    public EmployeeDTO updateEmployee(Long id, Employee employeeToUpdate) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee existingEmployee = employee.get();
            existingEmployee.setName(employeeToUpdate.getName());
            existingEmployee.setDepartment(employeeToUpdate.getDepartment());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return convertToDTO(updatedEmployee);
        }
        return null;
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
