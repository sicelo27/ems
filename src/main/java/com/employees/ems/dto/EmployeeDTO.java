package com.employees.ems.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{10,15}$", message = "Phone number should be valid")
    private String phoneNumber;

    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
    private BigDecimal salary;

    private Long departmentId;
    private String departmentName;
}