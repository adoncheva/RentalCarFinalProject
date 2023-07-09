package com.example.RentalCarProject.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String pwd;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
   @JsonBackReference
    private List<Reservation> reservationList;
}
