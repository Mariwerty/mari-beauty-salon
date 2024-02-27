package com.project.maribeauty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private Integer duration;
    private String description;
    //join with requests table
    @OneToMany(mappedBy = "serviceItem", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Request> requests;
    //join with workers table
    @ManyToMany
    @JoinTable(name ="service_worker",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id"))
    private List<Worker>workers;

    public ServiceItem(int i, String manicure, int i1, int i2, String manicure1) {
    }
}
