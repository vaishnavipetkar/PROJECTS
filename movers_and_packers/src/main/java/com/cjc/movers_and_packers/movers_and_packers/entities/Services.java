package com.cjc.movers_and_packers.movers_and_packers.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Services")
@Entity
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Service Id")
    private Long serviceId;

    @Column(name = "Service Name")
    private String serviceName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private double price;
    
}
