package com.example.mockbe.model.transcation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_transcation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailTranscation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "trans_id")
    private String  transcationId;
    @Column(name = "product_id")
    private String productId;
    @Column(name="number")
    private int number;
}
