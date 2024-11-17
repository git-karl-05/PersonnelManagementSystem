package com.kai.CompanyPersonnelManagementSystem.Repository;

import com.kai.CompanyPersonnelManagementSystem.DTO.EmployeeDTO;
import com.kai.CompanyPersonnelManagementSystem.Entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        PagingAndSortingRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> searchEmployee(
            @Param("name") String name,
            @Param("departmentId") Long departmentId);

}
