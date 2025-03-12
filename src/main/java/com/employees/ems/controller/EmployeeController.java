package com.employees.ems.controller;

import com.employees.ems.dto.ApiResponse;
import com.employees.ems.dto.EmployeeDTO;
import com.employees.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(ApiResponse.success("Employees retrieved successfully", employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(ApiResponse.success("Employee retrieved successfully", employee));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(
                ApiResponse.success("Employee created successfully", createdEmployee),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(
            @PathVariable Long id, 
            @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(ApiResponse.success("Employee updated successfully", updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(ApiResponse.success("Employee deleted successfully", null));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartmentId(departmentId);
        return ResponseEntity.ok(ApiResponse.success("Employees retrieved successfully", employees));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> searchEmployeesByName(@RequestParam String name) {
        List<EmployeeDTO> employees = employeeService.searchEmployeesByName(name);
        return ResponseEntity.ok(ApiResponse.success("Search results", employees));
    }

    @GetMapping("/email-check")
    public ResponseEntity<ApiResponse<Boolean>> checkEmailAvailability(@RequestParam String email) {
        boolean isAvailable = employeeService.isEmailUnique(email);
        return ResponseEntity.ok(ApiResponse.success(
                isAvailable ? "Email is available" : "Email is already in use",
                isAvailable));
    }
}