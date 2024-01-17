package com.example.mockbe.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {

    private List<MultipartFile> imageFiles;
    private String id; // hoặc bất kỳ trường nào khác bạn muốn chấp nhận
}
