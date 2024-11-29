package com.kai.CompanyPersonnelManagementSystem.DTO;

import java.util.List;

public class EmployeeHierarchyDTO {
    private Long id;
    private String name;
    private List<EmployeeHierarchyDTO> subordinates;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<EmployeeHierarchyDTO> getSubordinates() {
        return subordinates;
    }
    public void setSubordinates(List<EmployeeHierarchyDTO> subordinates) {
        this.subordinates = subordinates;
    }
}
