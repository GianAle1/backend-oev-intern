package com.unmsm.oevbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_conference")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private Integer totalStudents;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private LocalDate date;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrationList;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
