package com.example.mockbe.model.distributor;

import com.example.mockbe.model.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "distributor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Distributor {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @Column ( name = "distributor_code",unique = true, nullable = false)
    @NotEmpty(message = "Distributor code must not be empty")
    @NotBlank(message = "Distributor code must not be blank")
    @NotNull(message = "Distributor code must not be null")
    private  String distributorCode;
    @Column (name = "name",unique = true, nullable = false)
    @NotEmpty(message = "Distributor code must not be empty")
    @NotBlank(message = "Distributor code must not be blank")
    @NotNull(message = "Distributor code must not be null")
    private  String name;
    @Column (name = "email",unique = true, nullable = false)
    @NotEmpty(message = "Distributor code must not be empty")
    @NotBlank(message = "Distributor code must not be blank")
    @NotNull(message = "Distributor code must not be null")
    private String email;
    @Column (name = "phone",unique = true, nullable = false)
    @NotEmpty(message = "Distributor code must not be empty")
    @NotBlank(message = "Distributor code must not be blank")
    @NotNull(message = "Distributor code must not be null")
    private String phone;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column (name = "web")
    private String web;
    @Column (name = "description")
    private String description;
    @Column (name = "payment")
    private String payment;
    @Column (name = "address")
    private String address;
    @Column (name = "create_at")
    private Date createAt;
    @Column (name = "update_at")
    private Date updateAt;

    @OneToMany(mappedBy = "distributor")
    private List<Product> productList;
}
