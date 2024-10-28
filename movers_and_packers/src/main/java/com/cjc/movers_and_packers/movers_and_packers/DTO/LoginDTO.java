package com.cjc.movers_and_packers.movers_and_packers.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String email;
    private Long contactNumber;
    private String password;

}
