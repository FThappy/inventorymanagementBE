package com.example.mockbe.controller;

import com.example.mockbe.dto.MailDto;
import com.example.mockbe.model.MailStruct;
import com.example.mockbe.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class MailController {

    @Autowired
    private MailService mailService;
    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStruct mailsssss){

        mailService.sendMail( mail,mailsssss );
        return "success";

    }
}
