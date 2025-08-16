package com.springdemo.springdatajpahomework.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @NotBlank(message = "Name cannot be Empty")
    @Size(message = "Name cannot be longer than 50 characters",max = 50)
    private String name;
    @NotBlank(message = "Name cannot be Empty")
    @Size(message = "Name cannot be longer than 50 characters",max = 50)
    private String address;
    @NotBlank(message = "Name cannot be Empty")
    @Size(message = "Name cannot be longer than 50 characters",max = 50)
    private String phoneNumber;
    @NotBlank(message = "Name cannot be Empty")
    @Size(message = "Name cannot be longer than 50 characters",max = 50)
    private String username;
    @NotBlank(message = "Name cannot be Empty")
    @Size(message = "Name cannot be longer than 50 characters",max = 50)
    private String password;
}
