package com.example.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private int size;

    @ManyToOne
    private Hotel hotel;

}
