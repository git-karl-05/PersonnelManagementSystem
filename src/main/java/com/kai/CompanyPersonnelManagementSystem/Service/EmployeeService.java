package com.kai.CompanyPersonnelManagementSystem.Service;

import com.kai.CompanyPersonnelManagementSystem.DTO.EmployeeDTO;
import com.kai.CompanyPersonnelManagementSystem.Entity.Employee;
import com.kai.CompanyPersonnelManagementSystem.Entity.Department;
import com.kai.CompanyPersonnelManagementSystem.Repository.DepartmentRepository;
import com.kai.CompanyPersonnelManagementSystem.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
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

    //returns sorted list
    public Page<EmployeeDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(this::convertToDTO);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public List<EmployeeDTO> getEmployeeByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeeByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployees(String name, Long departmentId) {
        return employeeRepository.searchEmployee(name, departmentId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getManager(Long employeeID) {
        Employee employee =
                employeeRepository.findById(employeeID)
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        if (employee.getTeamManager() != null) {
            return convertToDTO(employee.getTeamManager());
        }
        return null;
    }

    public List<EmployeeDTO> getSubordinates(Long managerId) {
        Employee manager =
                employeeRepository.findById(managerId).orElseThrow(() -> new IllegalArgumentException("Manager not found"));
        return manager.getSubordinate().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeHierarchyDTO getHierarchy(Long managerId) {
        Employee manager =
                employeeRepository.findById(managerId).orElseThrow(() -> new IllegalArgumentException("Manager not found"));
        return buildHierarchy(manager);
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

    public EmployeeDTO transferEmployeeToDepartment(Long employeeId, Long newDepartmentId) {
        Optional<Employee> employeeOptional =
                employeeRepository.findById(employeeId);
        Optional<Department> departmentOptional =
                departmentRepository.findById(newDepartmentId);

        if (employeeOptional.isPresent() && departmentOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Department newDepartment = departmentOptional.get();

            employee.setDepartment(newDepartment);
            Employee updatedEmployee =  employeeRepository.save(employee);

            return convertToDTO(updatedEmployee);
        } else {
            throw new IllegalArgumentException("Invalid employee ID or department ID");
        }
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
