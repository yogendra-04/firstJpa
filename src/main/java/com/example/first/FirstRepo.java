package com.example.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface FirstRepo extends JpaRepository<Students, Integer> {



}
