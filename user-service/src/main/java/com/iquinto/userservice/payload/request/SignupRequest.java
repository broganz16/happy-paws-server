package com.iquinto.userservice.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String surname;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }

}
