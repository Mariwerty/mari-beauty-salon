package com.project.maribeauty.repositories;

import com.project.maribeauty.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceItemRepository extends JpaRepository <ServiceItem, Integer> {
}
