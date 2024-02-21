package com.project.maribeauty.controllers;

import com.project.maribeauty.dto.RequestDto;
import com.project.maribeauty.services.RequestService;
import com.project.maribeauty.services.ServiceItemService;
import com.project.maribeauty.services.WorkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class RequestController {
    private final RequestService requestService;
    private final ServiceItemService itemService;
    private final WorkerService workerService;

    @GetMapping("/service")
    public String selectService(Model model){
        model.addAttribute("services", itemService.getAllServiceItems());
        return "/bookings/select_service";
    }

    @GetMapping("/employee")
    public String selectEmployeeForService(@RequestParam Integer serviceId, Model model){
        model.addAttribute("serviceId", serviceId);
        model.addAttribute("workers", itemService.getAllEmployeeForService(serviceId));
        return "/bookings/select_employee";
    }

    @GetMapping("/appointment")
    public String selectAvailableBooking(@RequestParam Integer serviceId, @RequestParam Integer workerId,
                                         @RequestParam(required = false) String dateString,
                                         Model model){
        List<LocalTime> availableTimeList = requestService.getAvailableRequestByDateAndWorker(dateString, workerId);
        model.addAttribute("service", itemService.getServiceById(serviceId));
        model.addAttribute("workerId", workerId);
        model.addAttribute("availableTimeList", availableTimeList);
        return "bookings/select_date_time";
    }
    @GetMapping("/client_info")
    public String selectClientInfo(@RequestParam Integer serviceId, @RequestParam Integer workerId,
                                   @RequestParam(required = false) String dateString,
                                   @RequestParam String timeString, Model model){
        model.addAttribute("service", itemService.getServiceById(serviceId));
        model.addAttribute("worker", workerService.getWorkerById(workerId));
        model.addAttribute("dateString", dateString);
        model.addAttribute("timeString", timeString);
        model.addAttribute("requestDto", new RequestDto());
        return "/bookings/client_form";
    }

    @PostMapping("/save")
    public String saveBooking (@ModelAttribute("requestDto") @Valid RequestDto requestDto, BindingResult result,
                               Model model){
        model.addAttribute("service", itemService.getServiceById(requestDto.getServiceId()));
        model.addAttribute("worker",workerService.getWorkerById(requestDto.getWorkerId()) );
        model.addAttribute("dateString", requestDto.getRequestDate());
        model.addAttribute("timeString", requestDto.getStartTime());
        if (result.hasErrors()){
            return "/bookings/client_form";
        }
        requestService.saveRequest(requestDto);
        return "bookings/book_confirm";
    }



}
