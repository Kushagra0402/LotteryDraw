package com.example.demo.repository;

import com.example.demo.model.Draw;
import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByDraw(Draw draw);
}
