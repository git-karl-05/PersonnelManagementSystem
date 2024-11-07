package com.kai.CompanyPersonnelManagementSystem.DTO;

import com.kai.CompanyPersonnelManagementSystem.Entity.Department;
import jakarta.persistence.*;

public class EmployeeDTO {

    private Long id;
    private String name;
    private String departmentName;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
}
