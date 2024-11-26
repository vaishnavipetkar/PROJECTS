package com.cjc.movers_and_packers.movers_and_packers.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Service name is required.")
    @Size(max = 100, message = "Service name must not exceed 100 characters.")
    @Column(name = "Service Name")
    private String serviceName;

    @NotBlank(message = "Description is required.")
    @Column(name = "Description")
    private String description;

    @Positive(message = "Price must be greater than zero.")
    @Column(name = "Price")
    private double price;
    
}
