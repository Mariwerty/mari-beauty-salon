package com.project.maribeauty.services;

import com.project.maribeauty.dto.RequestDto;
import com.project.maribeauty.dto.UserDto;

import java.time.LocalTime;
import java.util.List;

public interface RequestService {
    List<LocalTime> getAvailableRequestByDateAndWorker (String dateString, Integer workerId);
    void saveRequest(RequestDto requestDto);
    List<RequestDto> findAllRequests();
}
