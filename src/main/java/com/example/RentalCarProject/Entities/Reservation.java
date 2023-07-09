package com.example.RentalCarProject.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private Double price;
    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @Column(name="period")
    private Integer period;






}
