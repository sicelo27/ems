package com.employees.ems.service;

import com.employees.ems.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    
    List<EmployeeDTO> getAllEmployees();
    
    EmployeeDTO getEmployeeById(Long id);
    
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    
    void deleteEmployee(Long id);
    
    List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId);
    
    List<EmployeeDTO> searchEmployeesByName(String keyword);
    
    boolean isEmailUnique(String email);
}