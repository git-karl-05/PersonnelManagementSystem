package com.kai.CompanyPersonnelManagementSystem.Entity;

import jakarta.persistence.Entity;

@Entity
public class OperationsManager extends Employee {
    private int teamManagerSize;

    public int getTeamManagerSize() {
        return teamManagerSize;
    }
    public void setTeamManagerSize(int teamManagerSize) {
        this.teamManagerSize = teamManagerSize;
    }
}
