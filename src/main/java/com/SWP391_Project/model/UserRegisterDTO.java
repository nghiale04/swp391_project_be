package com.SWP391_Project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    @JsonProperty("fristName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phoneNumber")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Password can not be blank")
    private String password;

    @JsonProperty("retypePassword")
    private String retypePassword;

    @NotBlank(message = "Email can not be blank")
    private String email;

}
