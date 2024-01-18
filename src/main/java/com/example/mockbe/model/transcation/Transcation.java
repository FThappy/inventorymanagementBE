package com.example.mockbe.model.transcation;

import com.example.mockbe.model.product.Image;
import com.example.mockbe.model.product.Statuss;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "transcation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "trans_id",nullable = false, unique = true)
    private String transcationId;
    @Column (name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'pending'")
    @Enumerated(EnumType.STRING)
    private Statusss status;
    @OneToMany(targetEntity = DetailTranscation.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="trans_id",referencedColumnName = "trans_id")
    private List<DetailTranscation> detail;

    private double totalCost ;
    private String distributorCode;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
