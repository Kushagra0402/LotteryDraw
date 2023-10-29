package com.example.demo.repository;

import com.example.demo.model.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestantRepository extends JpaRepository<Contestant,Long> {

    Contestant findByName(String name);
}
