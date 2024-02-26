package com.project.maribeauty.services.impl;

import com.project.maribeauty.model.Worker;
import com.project.maribeauty.repositories.WorkerRepository;
import com.project.maribeauty.services.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    @Override
    public Worker getWorkerById(Integer workerId) {
        return workerRepository.findById(workerId).orElse(null);
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
}
