package com.example.mockbe.service;

import com.example.mockbe.dto.DetailTransDto;
import com.example.mockbe.dto.TranscationDto;
import com.example.mockbe.model.MailStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    public void sendMail(String mail, MailStruct mailStruct){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("volunteerapp914@gmail.com");
        simpleMailMessage.setSubject(mailStruct.getSubjectMail());
        simpleMailMessage.setText(mailStruct.getMessageMail());
        simpleMailMessage.setTo(mail);

        mailSender.send(simpleMailMessage);

    }
    public void sendCreateTrans(String mail, TranscationDto transcationDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("volunteerapp914@gmail.com");
        simpleMailMessage.setSubject("Tạo đơn hàng");
        StringBuilder messageBuilder = new StringBuilder();
        for (DetailTransDto detailTransDto : transcationDto.getProduct()) {
            messageBuilder.append("Đặt sản phẩm với id :  " + detailTransDto.getProductId() + " với số lượng " +detailTransDto.getNumber()).append("\n");
        }
        simpleMailMessage.setText(String.valueOf(messageBuilder));
        simpleMailMessage.setTo(mail);

        mailSender.send(simpleMailMessage);

    }

    public void sendStatus(String mail, String id, String status, String description) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("volunteerapp914@gmail.com");
        StringBuilder messageBuilder = new StringBuilder();

        if ("wrong".equals(status)) {
            simpleMailMessage.setSubject("Đơn hàng có vấn đề");
            messageBuilder.append("Đơn hàng với id :  " + id + " có vấn đề như sau " + description).append("\n");
        }
        if ("declined".equals(status)) {
            simpleMailMessage.setSubject("Hủy đơn hàng ");
            messageBuilder.append("Hủy đơn hàng với id :  " + id + " vì lý do sau " + description + " thành thật xin lỗi các bạn ").append("\n");
        }
        if ("approved".equals(status)) {
            simpleMailMessage.setSubject("Nhận đơn hàng thành công");
            messageBuilder.append("Đơn hàng với id :  " + id + " đã được nhận thành công cám ơn ").append("\n");
        }

        simpleMailMessage.setText(messageBuilder.toString());
        simpleMailMessage.setTo(mail);

        mailSender.send(simpleMailMessage);
    }
}
