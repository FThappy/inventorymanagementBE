package com.example.mockbe.repository;

import com.example.mockbe.model.product.Product;
import com.example.mockbe.model.transcation.Transcation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface TranscationRepository extends JpaRepository<Transcation, Integer> {
    default Page<Transcation> getPageTranscation(int page){
        Pageable pageable = PageRequest.of(page, 9);
        return findAll(pageable);
    }
}
