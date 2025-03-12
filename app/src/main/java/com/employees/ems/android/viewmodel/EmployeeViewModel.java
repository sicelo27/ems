package com.employees.ems.android.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.employees.ems.android.model.Employee;
import com.employees.ems.android.repository.EmployeeRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private final EmployeeRepository employeeRepository;

    public EmployeeViewModel(Application application) {
        super(application);
        employeeRepository = new EmployeeRepository();
    }

    // Get all employees
    public LiveData<List<Employee>> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    // Get employee by ID
    public LiveData<Employee> getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    // Create employee
    public LiveData<Employee> createEmployee(Employee employee) {
        return employeeRepository.createEmployee(employee);
    }

    // Update employee
    public LiveData<Employee> updateEmployee(Long id, Employee employee) {
        return employeeRepository.updateEmployee(id, employee);
    }

    // Delete employee
    public LiveData<Boolean> deleteEmployee(Long id) {
        return employeeRepository.deleteEmployee(id);
    }
}
