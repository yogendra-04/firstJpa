package com.example.first.repositories;

import com.example.first.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FirstRepo extends JpaRepository<Students, Integer> {



}
