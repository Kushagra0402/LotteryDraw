package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ticket")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "contestant_id")
    @ManyToOne
    private Contestant contestant;

    @Column(name="serial_code")
    private String serialCode;

    @ManyToOne
    @JoinColumn(name = "draw_id")
    private Draw draw;

    @Column(name="valid")
    private Boolean isValid;

}
