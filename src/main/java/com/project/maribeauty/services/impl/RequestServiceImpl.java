package com.project.maribeauty.services.impl;

import com.project.maribeauty.dto.RequestDto;
import com.project.maribeauty.model.*;
import com.project.maribeauty.repositories.RequestRepository;
import com.project.maribeauty.services.ClientService;
import com.project.maribeauty.services.RequestService;
import com.project.maribeauty.services.ServiceItemService;
import com.project.maribeauty.services.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final ServiceItemService serviceItemService;
    private final WorkerService workerService;
    private final ClientService clientService;
    private final ModelMapper mapper;

    @org.springframework.beans.factory.annotation.Value("10:00")
    private String openingHour;

    @org.springframework.beans.factory.annotation.Value("20:00")
    private String closingHour;

    @org.springframework.beans.factory.annotation.Value("60")
    private String durationTime;

    @Override
    public List<LocalTime> getAvailableRequestByDateAndWorker(String dateString, Integer workerId) {
        List<LocalTime> availableTimeList = new ArrayList<>();
        LocalDate requestDate = dateString == null ? LocalDate.now() : LocalDate.parse(dateString);
        LocalTime startTime = LocalTime.parse(openingHour);
        LocalTime endTime = LocalTime.parse(closingHour);
        long duration = Long.parseLong(durationTime);

        LocalTime tempTime = startTime;
        while (endTime.compareTo(tempTime) != 0) {
            List<Request> requestList = requestRepository
                    .findByRequestDateAndStartTimeAndWorkerId(requestDate, tempTime, workerId);
            if(requestList.isEmpty()) {
                availableTimeList.add(tempTime);
            }
            tempTime = tempTime.plusMinutes(duration);
        }
        return availableTimeList;
    }

    @Override
    public void saveRequest(RequestDto requestDto) {
        Client client = Client.builder().
                name(requestDto.getClientName())
                .phoneNumber(requestDto.getClientPhoneNumber())
                .email(requestDto.getClientEmail())
                .build();
        client = clientService.save(client);
        Worker worker = workerService.getWorkerById(requestDto.getWorkerId());
        ServiceItem serviceItem = serviceItemService.getServiceById(requestDto.getServiceId());

        Request request = Request.builder()
                .requestDate(requestDto.getRequestDate())
                .startTime(requestDto.getStartTime())
                .comments(requestDto.getComments())
                .worker(worker)
                .serviceItem(serviceItem)
                .client(client)
                .build();
        requestRepository.save(request);
    }

    @Override
    public List<RequestDto> findAllRequests() {
        List<Request> requests = requestRepository.findAll();
        return requests.stream()
                .map((request) -> mapToRequestDto(request))
                .collect(Collectors.toList());
    }


    private RequestDto mapToRequestDto(Request request){
        RequestDto requestDto = new RequestDto();
        requestDto.setRequestDate(request.getRequestDate());
        requestDto.setStartTime(request.getStartTime());
        requestDto.setClientName(request.getClient().getName());
        requestDto.setClientPhoneNumber(request.getClient().getPhoneNumber());
        requestDto.setWorkerName(request.getWorker().getName());
        requestDto.setServiceName(request.getServiceItem().getName());
        return requestDto;
    }
}
