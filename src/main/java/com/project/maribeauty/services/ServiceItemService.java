package com.project.maribeauty.services;

import com.project.maribeauty.model.ServiceItem;
import com.project.maribeauty.model.Worker;

import java.util.List;

public interface ServiceItemService {
    List<ServiceItem> getAllServiceItems();
    List<Worker> getAllEmployeeForService(Integer serviceId);
    ServiceItem getServiceById(Integer serviceId);
}
