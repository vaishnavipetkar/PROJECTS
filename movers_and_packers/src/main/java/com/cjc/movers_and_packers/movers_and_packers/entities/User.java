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
@Table(name = "User")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User Id")
    private Long userId;

    @Column(name = "User Name")
    private String name;

    @Column(name = "User Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Contact Number")
    private Long contactNumber;

    @Column(name = "Address")
    private String address;
    
}
