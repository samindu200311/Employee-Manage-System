package edu.icet.ecom.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name can be up to 100 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Department is required")
    @Pattern(regexp = "HR|IT|Finance|Operations", message = "Department must be HR, IT, Finance, or Operations")
    private String department;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
