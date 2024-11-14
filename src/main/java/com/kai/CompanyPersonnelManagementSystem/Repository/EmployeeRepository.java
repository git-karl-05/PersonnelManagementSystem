package com.kai.CompanyPersonnelManagementSystem.Repository;

import com.kai.CompanyPersonnelManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        PagingAndSortingRepository<Employee, Long> {
}
