package com.example.mockbe.repository;

import com.example.mockbe.model.transcation.DetailTranscation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface DetailTranscationRepository extends JpaRepository<DetailTranscation, Integer> {
}
