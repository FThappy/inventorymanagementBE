package com.example.mockbe.service.transcation;

import com.example.mockbe.dto.DetailTransDto;
import com.example.mockbe.dto.ResProduct;
import com.example.mockbe.dto.TranscationDto;
import com.example.mockbe.model.product.Product;
import com.example.mockbe.model.transcation.DetailTranscation;
import com.example.mockbe.model.transcation.Statusss;
import com.example.mockbe.model.transcation.Transcation;
import com.example.mockbe.repository.DetailTranscationRepository;
import com.example.mockbe.repository.TranscationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Service

public class TranscationServiceImp implements TranscationService {

    @Autowired
    TranscationRepository transcationRepository;
    @Autowired
    DetailTranscationRepository detailTranscationRepository;
    @Override
    public String createTranscation(TranscationDto transcation) {
        try {
            Transcation newTranscation = new Transcation();
            newTranscation.setTranscationId(transcation.getTranscationId());
            newTranscation.setDistributorCode(transcation.getDistributorCode());
            newTranscation.setTotalCost(transcation.getTotalCost());
            newTranscation.setCreatedAt(LocalDateTime.now());
            newTranscation.setUpdatedAt(LocalDateTime.now());
            newTranscation.setStatus(Statusss.pending);
            transcationRepository.save(newTranscation);


            for (DetailTransDto detailTranscation : transcation.getProduct()){
                DetailTranscation newDetailTranscation = new DetailTranscation();
                newDetailTranscation.setTranscationId(transcation.getTranscationId());
                newDetailTranscation.setProductId(detailTranscation.getProductId());
                newDetailTranscation.setNumber(detailTranscation.getNumber());
                detailTranscationRepository.save(newDetailTranscation);
            }

            return "Thành Công";
        }
        catch (Exception e) {
            return null;
        }
    }
    @Override
    public Page<Transcation> getPageTranscation(int page) {
        return transcationRepository.getPageTranscation(page);
    }

    @Override
    public Transcation getTransById(int id) {
        return transcationRepository.findById(id).orElse(null);
    }

    @Override
    public String browserTrans(int id,String status, String description) {
         Transcation transcation =  transcationRepository.findById(id).orElse(null);
         transcation.setStatus(Statusss.valueOf(status));
         transcation.setDescription(description);
         transcation.setUpdatedAt(LocalDateTime.now());
        transcationRepository.save(transcation);
        return "thành công";
    }
}
