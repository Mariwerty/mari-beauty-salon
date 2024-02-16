package com.project.maribeauty.repositories;

import com.project.maribeauty.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequestDateAndStartTimeAndWorkerId(LocalDate requestDate,
                                                           LocalTime startTime,
                                                           Integer workerId);
}
