package com.example.mockbe.controller;


import com.example.mockbe.dto.CategoryProductCount;
import com.example.mockbe.dto.TestDto;
import com.example.mockbe.model.product.Image;
import com.example.mockbe.repository.ImageProductRepository;
import com.example.mockbe.service.CloudinaryService;
import com.example.mockbe.service.products.ProductsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ProductsServiceImp imageService;

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list(){
        List<Image> list = imageService.listImage();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    public class ImageUploadDTO {
        private MultipartFile imageFile;

        public MultipartFile getImageFile() {
            return imageFile;
        }

        public void setImageFile(MultipartFile imageFile) {
            this.imageFile = imageFile;
        }
    }
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> upload(@ModelAttribute TestDto requestDTO) throws IOException {

        List<MultipartFile> multipartFiles = requestDTO.getImageFiles();

        for (MultipartFile multipartFile : multipartFiles) {
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                return new ResponseEntity<>("Image non valide!", HttpStatus.BAD_REQUEST);
            }

            Map result = cloudinaryService.upload(multipartFile, "aaaaaa");
            Image image = new Image();
            image.setUrl((String) result.get("url"));
            imageService.saveImage(image);
        }

        return ResponseEntity.status(HttpStatus.OK).body(imageService.listImage());
    }

}
