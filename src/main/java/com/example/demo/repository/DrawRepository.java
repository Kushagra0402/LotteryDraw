package com.example.demo.repository;

import com.example.demo.model.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawRepository extends JpaRepository<Draw,Long> {
    Draw findFirstByOrderByStartTimeDesc();

    Draw findFirstByIsEndedOrderByStartTimeDesc(boolean isEnded);
}
