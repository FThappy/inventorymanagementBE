package com.example.mockbe.controller;


import com.example.mockbe.request.AuthenticationRequest;
import com.example.mockbe.request.RegisterRequest;
import com.example.mockbe.response.AuthenticationResponse;
import com.example.mockbe.response.ResponeLogin;
import com.example.mockbe.response.ResponeRegister;
import com.example.mockbe.service.jwt.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<ResponeRegister> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<ResponeLogin> authenticate(
            @RequestBody AuthenticationRequest request

    ) {
        System.out.println(request);
        String descryptPassword = desEncrypt(request.getPassword());
        AuthenticationRequest newRequest = new AuthenticationRequest(request.getUsername(),descryptPassword);
        return ResponseEntity.ok(service.authenticate(newRequest));
    }
    public static String desEncrypt(String data){

        try
        {

            String key = "1234567812345678";
            String iv = "1234567812345678";

            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encrypted1 = decoder.decode(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);

            return  originalString.trim();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
