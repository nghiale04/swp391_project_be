package com.SWP391_Project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data //toString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Password can not be blank")
    private String password;
}
