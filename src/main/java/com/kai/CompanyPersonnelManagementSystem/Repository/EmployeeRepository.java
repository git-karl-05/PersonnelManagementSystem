package com.kai.CompanyPersonnelManagementSystem.Repository;

import com.kai.CompanyPersonnelManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
