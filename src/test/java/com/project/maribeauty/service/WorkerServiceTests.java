package com.project.maribeauty.service;

import com.project.maribeauty.model.Worker;
import com.project.maribeauty.repositories.WorkerRepository;
import com.project.maribeauty.services.impl.WorkerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkerServiceTests {
    @Mock
    private WorkerRepository workerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    WorkerRepository mockWorkerRepository = Mockito.mock(WorkerRepository.class);
    WorkerServiceImpl workerService = new WorkerServiceImpl(mockWorkerRepository);
    @Test
    public void testGetWorkerById() {
        // Arrange
        Integer workerId = 1;
        Worker expectedWorker = new Worker(workerId, "Anna Ivanova", "+375291112233", "ann@example.com");
        when(mockWorkerRepository.findById(workerId)).thenReturn(Optional.of(expectedWorker));
        // Act
        Worker resultWorker = workerService.getWorkerById(workerId);
        // Assert
        assertEquals(expectedWorker, resultWorker);
    }
    @Test
    public void testGetWorkerByIdNotFound() {
        // Arrange
        Integer workerId = 1;
        when(mockWorkerRepository.findById(workerId)).thenReturn(Optional.empty());

        // Act
        Worker resultWorker = workerService.getWorkerById(workerId);

        // Assert
        assertNull(resultWorker);
    }

    @Test
    public void testGetAllWorkers() {
        // Arrange
        List<Worker> expectedWorkers = Arrays.asList(
                new Worker(1, "Anna Ivanova", "+375291112233", "ann@example.com"),
                new Worker(2, "Jane New", "+375292223331", "jane@example.com")
        );
        when(mockWorkerRepository.findAll()).thenReturn(expectedWorkers);

        // Act
        List<Worker> resultWorkers = workerService.getAllWorkers();

        // Assert
        assertEquals(expectedWorkers, resultWorkers);
    }
}
