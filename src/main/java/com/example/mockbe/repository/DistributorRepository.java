package com.example.mockbe.repository;

import com.example.mockbe.model.distributor.Distributor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface DistributorRepository extends JpaRepository<Distributor, Integer> {

    default Page<Distributor> getPageDistributor(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return findAll(pageable);
    }

    Distributor findByNameContainsIgnoreCase(String name);



}
