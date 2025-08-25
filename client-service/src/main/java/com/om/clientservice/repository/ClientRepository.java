package com.om.clientservice.repository;

import com.om.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
