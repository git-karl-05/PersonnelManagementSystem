package com.kai.CompanyPersonnelManagementSystem.Entity;

import jakarta.persistence.Entity;

@Entity
public class TeamManager extends Employee {
    private int teamSize;

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }
}
