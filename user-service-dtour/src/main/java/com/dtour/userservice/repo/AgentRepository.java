package com.dtour.userservice.repo;

import com.dtour.userservice.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgentRepository extends JpaRepository<Agent, UUID> {

    public Agent save(Agent agent);
}
