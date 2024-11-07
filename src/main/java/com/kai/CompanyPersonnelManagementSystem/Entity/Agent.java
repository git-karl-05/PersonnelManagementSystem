package com.kai.CompanyPersonnelManagementSystem.Entity;

import jakarta.persistence.Entity;

@Entity
public class Agent extends Employee {
    private String shift;

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }
}
