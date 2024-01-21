package com.example.mockbe.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private  int id;
    private String subjectMail;
    private String messageMail;
}
