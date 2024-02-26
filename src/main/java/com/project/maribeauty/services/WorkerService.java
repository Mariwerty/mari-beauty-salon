package com.project.maribeauty.services;

import com.project.maribeauty.model.Worker;

import java.util.List;

public interface WorkerService {
    Worker getWorkerById(Integer workerId);
    List<Worker> getAllWorkers();
}
