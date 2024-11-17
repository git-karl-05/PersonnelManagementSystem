package com.kai.CompanyPersonnelManagementSystem.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee teamManager;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Employee> subordinates;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

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
    public void setTeamManager(Employee teamManager) {
        this.teamManager = teamManager;
    }
    public Employee getTeamManager() {
        return teamManager;
    }
    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }
    public List<Employee> getSubordinate() {
        return subordinates;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }
}
