package com.cjc.movers_and_packers.movers_and_packers.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

    public String errorMessage;
    public int statusCode;
    public String statusMessage;
}
