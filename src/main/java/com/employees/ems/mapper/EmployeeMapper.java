package com.employees.ems.mapper;

import com.employees.ems.dto.EmployeeDTO;
import com.employees.ems.entity.Department;
import com.employees.ems.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setHireDate(employee.getHireDate());
        dto.setSalary(employee.getSalary());
        
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getName());
        }
        
        return dto;
    }

    public Employee toEntity(EmployeeDTO dto, Department department) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setHireDate(dto.getHireDate());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);
        
        return employee;
    }

    public void updateEntityFromDto(EmployeeDTO dto, Employee employee, Department department) {
        if (dto == null || employee == null) {
            return;
        }

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setHireDate(dto.getHireDate());
        employee.setSalary(dto.getSalary());
        
        if (department != null) {
            employee.setDepartment(department);
        }
    }
}