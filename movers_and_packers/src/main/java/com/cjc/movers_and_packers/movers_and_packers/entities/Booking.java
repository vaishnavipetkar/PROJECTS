package com.cjc.movers_and_packers.movers_and_packers.entities;
import java.time.LocalDateTime;
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
@Table(name = "Bookings")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Booking Id")
    private Long bookingId;

    @Column(name = "User Id")
    private Long userId;

    @Column(name = "Service Id")
    private Long serviceId;

    @Column(name = "Pickup Address")
    private String pickupAddress;

    @Column(name = "Destination Address")
    private String destinationAddress;

    @Column(name = " MoveDataTime")
    private LocalDateTime moveDateTime;

    @Column(name = "Total Price")
    private double totalPrice;

    @Column(name = "Status")
    private String status;
    
}
