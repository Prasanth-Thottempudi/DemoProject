package com.usermanagement.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    
    @NotNull(message = "First name cannot be null")
    @Size(min = 3, max = 10, message = "First name must be between 3 and 10 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 3, max = 10, message = "Last name must be between 3 and 10 characters")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    

    @NotNull(message = "Mobile number cannot be null")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number format")
    private String mobileNumber;

    @NotNull(message = "Enabled status cannot be null")
    private Boolean enabled;
    
    
    
    
    
}
