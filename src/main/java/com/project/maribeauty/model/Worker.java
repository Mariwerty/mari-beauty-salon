package com.project.maribeauty.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "workers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    // join with requests table
    @OneToMany(mappedBy = "worker", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Request> requests;
    //join with services table
    @ManyToMany(mappedBy = "workers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ServiceItem> serviceItems;
}
