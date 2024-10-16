package com.cjc.movers_and_packers.movers_and_packers.entities;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment Id")
    private Long paymentId;

    @Column(name = "Booking Id")
    private Long bookingId;

    @Column(name = "Amount Paid")
    private double amountPaid;

    @Column(name = "Payment Method")
    private String paymentMethod;

    @Column(name = "Payment Date")
    private LocalDateTime paymentDate;
    
}
