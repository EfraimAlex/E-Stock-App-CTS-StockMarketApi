package com.stockMarketApi.Model;



import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name ="stockDetails")
@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "stock", nullable = false)
    private Float stock;
    @Column(name = "companyName", nullable = false)
    private String companyName;
    @Column(name = "company_code", nullable = false)
    private Integer companyCode;
    @CreationTimestamp
    @Column(name = "timeStamp", nullable = false, updatable = false)
    private LocalDateTime timeStamp;

}

