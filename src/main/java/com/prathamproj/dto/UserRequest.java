package com.prathamproj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Schema(name = "First Name")
    private String firstName;

    @Schema(name = "Last Name")
    private String lastName;

    @Schema(name = "Other Name")
    private String otherName;

    @Schema(name = "Gender")
    private String gender;

    @Schema(name = "Address")
    private String address;

    @Schema(name = "State of Origin")
    private String stateOfOrigin;

    @Schema(name = "Email Address")
    private String email;

    @Schema(name = "Primary Phone Number")
    private String phoneNumber;

    @Schema(name = "Alternative Phone Number")
    private String alternativePhoneNumber;
}
