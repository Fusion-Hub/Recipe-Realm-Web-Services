package com.fusionhub.reciperealm.webservices.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private String firstName;
    private String lastName;
    private byte[] profileImage;
    private byte[] bannerImage;
    private String location;
    private String bio;
    private LocalDate birthDate;
}
