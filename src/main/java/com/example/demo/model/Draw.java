package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="draw")
@Getter
@Setter

public class Draw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="startTime")
    private LocalDateTime startTime;

    @OneToOne
    @JoinColumn(name = "winning_ticket_id")
    private Ticket winningTicket;

    @Column(name="is_ended")
    private boolean isEnded;

}
