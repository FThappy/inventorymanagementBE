package com.example.mockbe.service.transcation;


import com.example.mockbe.dto.TranscationDto;
import com.example.mockbe.model.distributor.Distributor;
import com.example.mockbe.model.product.Product;
import com.example.mockbe.model.transcation.Transcation;
import org.springframework.data.domain.Page;

public interface TranscationService {
    public String createTranscation(TranscationDto transcation);

    public Page<Transcation> getPageTranscation(int page);

    public Transcation getTransById(int id);

    public String browserTrans(int id,String status, String description);



}
