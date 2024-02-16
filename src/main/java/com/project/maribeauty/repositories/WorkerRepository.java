package com.project.maribeauty.repositories;

import com.project.maribeauty.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}
