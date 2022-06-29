package com.example.xharktankspringboot.repository;

import com.example.xharktankspringboot.entity.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, Long> {

}
