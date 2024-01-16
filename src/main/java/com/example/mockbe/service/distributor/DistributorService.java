package com.example.mockbe.service.distributor;

import com.example.mockbe.dto.ResDistributor;
import com.example.mockbe.model.distributor.Distributor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DistributorService {
    public ResDistributor createDistributor(Distributor distributor);

    public ResDistributor updateDistributor(Distributor distributor, int id);

    public List<Distributor> getDistributors();

    public Distributor getDistributorById(int id);

    public Page<Distributor> getPageDistributors(int page);

    public String deleteDistributor(int id);

    public ResDistributor updateDistributorStatus(Distributor distributor, int id);
}
