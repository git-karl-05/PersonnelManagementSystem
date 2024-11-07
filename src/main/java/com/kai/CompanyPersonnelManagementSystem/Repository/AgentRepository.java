package com.kai.CompanyPersonnelManagementSystem.Repository;

import com.kai.CompanyPersonnelManagementSystem.Entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
