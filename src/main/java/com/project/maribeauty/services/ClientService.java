package com.project.maribeauty.services;

import com.project.maribeauty.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientService {
    Client save(Client client);
}
