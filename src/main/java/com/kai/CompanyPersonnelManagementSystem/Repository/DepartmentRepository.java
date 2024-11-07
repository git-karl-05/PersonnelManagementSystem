package com.kai.CompanyPersonnelManagementSystem.Repository;

import com.kai.CompanyPersonnelManagementSystem.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
