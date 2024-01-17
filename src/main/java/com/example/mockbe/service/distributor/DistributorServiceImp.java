package com.example.mockbe.service.distributor;

import com.example.mockbe.dto.ResDistributor;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DistributorServiceImp implements DistributorService {

     @Autowired
    public DistributorRepository distributorRepository;
    @Override
    public ResDistributor createDistributor(Distributor distributor) {
        Distributor existingDistributor = distributorRepository.findDistributorByDistributorCodeOrPhoneOrEmail(distributor.getDistributorCode(), distributor.getPhone(), distributor.getEmail());
        if (existingDistributor != null){
            ResDistributor resDistributor = new ResDistributor("1",null);
            return resDistributor;
        }

        try {
            Distributor newDistributor = new Distributor();
            newDistributor.setName(distributor.getName());
            newDistributor.setEmail(distributor.getEmail());
            newDistributor.setPhone(distributor.getPhone());
            newDistributor.setAddress(distributor.getAddress());
            newDistributor.setDistributorCode(distributor.getDistributorCode());
            newDistributor.setPayment(distributor.getPayment());
            newDistributor.setDescription(distributor.getDescription());
            newDistributor.setWeb(distributor.getWeb());
            newDistributor.setCreateAt(distributor.getCreateAt());
            newDistributor.setUpdateAt(distributor.getUpdateAt());
            ResDistributor resDistributor = new ResDistributor("0",distributorRepository.save(newDistributor));
            return resDistributor;

        }
        catch (Exception e) {
            return null;
        }
    }
    @Override
    // lấy tất cả nhà cung cấp
    public List<Distributor> getDistributors() {
        return distributorRepository.findAll();
    }
    @Override
    public Page<Distributor> getPageDistributors(int page) {
        return distributorRepository.getPageDistributor(page);
    }

    @Override
    public ResDistributor updateDistributor(Distributor distributor, int id) {
        Distributor oldDistributor = distributorRepository.findById(id).orElse(null);
        if(oldDistributor == null){
            return null;
        }
        try{
            Distributor newDistributor ;
            oldDistributor.setId(id);
            oldDistributor.setAddress(distributor.getAddress());
            oldDistributor.setCreateAt(oldDistributor.getCreateAt());
            oldDistributor.setUpdateAt(distributor.getUpdateAt());
            oldDistributor.setDescription(distributor.getDescription());
            oldDistributor.setPayment(distributor.getPayment());
            oldDistributor.setWeb(distributor.getWeb());
            oldDistributor.setDistributorCode(distributor.getDistributorCode());
            oldDistributor.setPhone(distributor.getPhone());
            oldDistributor.setEmail(distributor.getEmail());
            oldDistributor.setName(distributor.getName());
            newDistributor = distributorRepository.save(oldDistributor);
            ResDistributor resDistributor = new ResDistributor("0",newDistributor);
            return resDistributor;
        }catch (Exception e){
            ResDistributor resDistributor = new ResDistributor("1",null);
           return resDistributor;
        }
    }

    @Override
    public ResDistributor updateDistributorStatus(Distributor distributor, int id) {
        Distributor oldDistributor = distributorRepository.findById(id).orElse(null);
        if(oldDistributor == null){
            return null;
        }
        try{
            Distributor newDistributor ;
            oldDistributor.setStatus(distributor.getStatus());
            newDistributor = distributorRepository.save(oldDistributor);
            ResDistributor resDistributor = new ResDistributor("0",newDistributor);
            return resDistributor;
        }catch (Exception e){
            ResDistributor resDistributor = new ResDistributor("1",null);
            return resDistributor;
        }
    }

    @Override
    public Distributor getDistributorById(int id) {
        return distributorRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteDistributor(int id) {
        Distributor distributor = distributorRepository.findById(id).orElse(null);
        if(distributor == null){
            return "4";
        }
        try {
            distributorRepository.deleteById(id);
            return "0";
        }
        catch (Exception e){
            return "1";
        }
    }

    @Override
    public Distributor getDistributorByCode(String code) {
        return distributorRepository.findByDistributorCode(code);
    }
}
