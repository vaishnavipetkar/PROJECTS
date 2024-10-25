package com.cjc.movers_and_packers.movers_and_packers.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    private String name;
    private String email;
    private String password;
    private Long ContactNumber;
    private String address;
    
}
