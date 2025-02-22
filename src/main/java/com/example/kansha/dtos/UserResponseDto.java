package com.example.kansha.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private String token;
    private String provider;

}
