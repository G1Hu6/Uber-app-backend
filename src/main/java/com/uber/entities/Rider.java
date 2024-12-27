package com.uber.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rider")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double ratting;
}
