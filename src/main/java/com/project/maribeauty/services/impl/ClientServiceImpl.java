package com.project.maribeauty.services.impl;

import com.project.maribeauty.model.Client;
import com.project.maribeauty.repositories.ClientRepository;
import com.project.maribeauty.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client save(Client client){
        return clientRepository.save(client);
    }

}
