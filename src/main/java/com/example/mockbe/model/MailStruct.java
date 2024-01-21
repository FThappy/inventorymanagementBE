package com.example.mockbe.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailStruct {
    private  int id;
    private String subjectMail;
    private String messageMail;
}
