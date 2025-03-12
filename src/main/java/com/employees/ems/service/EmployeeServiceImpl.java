package com.employees.ems.service;

import com.employees.ems.dto.EmployeeDTO;
import com.employees.ems.entity.Department;
import com.employees.ems.entity.Employee;
import com.employees.ems.exception.BadRequestException;
import com.employees.ems.exception.ResourceNotFoundException;
import com.employees.ems.mapper.EmployeeMapper;
import com.employees.ems.repository.DepartmentRepository;
import com.employees.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        
        return employeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // Check if email is already used
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new BadRequestException("Email is already in use: " + employeeDTO.getEmail());
        }
        
        // Fetch department
        Department department = null;
        if (employeeDTO.getDepartmentId() != null) {
            department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", employeeDTO.getDepartmentId()));
        }
        
        // Convert DTO to entity and save
        Employee employee = employeeMapper.toEntity(employeeDTO, department);
        Employee savedEmployee = employeeRepository.save(employee);
        
        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        // Find the employee
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        
        // Check if email is already used by another employee
        if (!employee.getEmail().equals(employeeDTO.getEmail()) && 
                employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new BadRequestException("Email is already in use: " + employeeDTO.getEmail());
        }
        
        // Fetch department if departmentId is provided
        Department department = null;
        if (employeeDTO.getDepartmentId() != null) {
            department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", employeeDTO.getDepartmentId()));
        }
        
        // Update employee entity from DTO
        employeeMapper.updateEntityFromDto(employeeDTO, employee, department);
        
        // Save updated employee
        Employee updatedEmployee = employeeRepository.save(employee);
        
        return employeeMapper.toDto(updatedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId) {
        // Verify department exists
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department", "id", departmentId);
        }
        
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> searchEmployeesByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new BadRequestException("Search keyword cannot be empty");
        }
        
        List<Employee> employees = employeeRepository.searchByName(keyword.trim());
        return employees.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !employeeRepository.existsByEmail(email);
    }
}