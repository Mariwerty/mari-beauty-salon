package com.project.maribeauty.services.impl;

import com.project.maribeauty.exception.ServiceItemNotFoundException;
import com.project.maribeauty.model.ServiceItem;
import com.project.maribeauty.model.Worker;
import com.project.maribeauty.repositories.ServiceItemRepository;
import com.project.maribeauty.services.ServiceItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService {

    private final ServiceItemRepository serviceItemRepository;
    @Override
    public List<ServiceItem> getAllServiceItems() {
        return serviceItemRepository.findAll();
    }

    @Override
    public List<Worker> getAllEmployeeForService(Integer serviceId) {
        ServiceItem serviceItem = serviceItemRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceItemNotFoundException("service not found"));
        return serviceItem.getWorkers();
    }

    @Override
    public ServiceItem getServiceById(Integer serviceId) {
        return serviceItemRepository.findById(serviceId)
                .orElseThrow(()-> new ServiceItemNotFoundException("service not found"));
    }
}
