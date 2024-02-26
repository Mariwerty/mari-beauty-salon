package com.project.maribeauty.dto;

import com.project.maribeauty.model.ServiceItem;
import com.project.maribeauty.model.Worker;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDto {
    private LocalDate requestDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comments;
    @NotBlank
    private String clientName;
    @NotBlank
    private String clientPhoneNumber;
    @NotBlank
    private String clientEmail;
    private Integer serviceId;
    private Integer workerId;
    private String serviceName;
    private String workerName;

    public RequestDto(Long id, LocalTime startTime, String name, String phoneNumber, String name1, String name2) {
    }
}
