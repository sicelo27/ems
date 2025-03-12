package com.employees.ems.android.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.employees.ems.android.api.EmployeeApiService;
import com.employees.ems.android.api.RetrofitClient;
import com.employees.ems.android.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {

    private final EmployeeApiService apiService;

    public EmployeeRepository() {
        this.apiService = RetrofitClient.getClient().create(EmployeeApiService.class);
    }

    // Fetch all employees
    public LiveData<List<Employee>> getAllEmployees() {
        MutableLiveData<List<Employee>> employeesLiveData = new MutableLiveData<>();
        apiService.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    employeesLiveData.setValue(response.body());
                } else {
                    Log.e("EmployeeRepository", "Error fetching employees, response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e("EmployeeRepository", "API Call failed: " + t.getMessage());
            }
        });
        return employeesLiveData;
    }

    // Fetch employee by ID
    public LiveData<Employee> getEmployeeById(Long id) {
        MutableLiveData<Employee> employeeLiveData = new MutableLiveData<>();
        apiService.getEmployeeById(id).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful() && response.body() != null) {
                    employeeLiveData.setValue(response.body());
                } else {
                    Log.e("EmployeeRepository", "Error fetching employee by ID, response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e("EmployeeRepository", "API Call failed: " + t.getMessage());
            }
        });
        return employeeLiveData;
    }

    // Create employee
    public LiveData<Employee> createEmployee(Employee employee) {
        MutableLiveData<Employee> employeeLiveData = new MutableLiveData<>();
        apiService.createEmployee(employee).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful() && response.body() != null) {
                    employeeLiveData.setValue(response.body());
                } else {
                    Log.e("EmployeeRepository", "Error creating employee, response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e("EmployeeRepository", "API Call failed: " + t.getMessage());
            }
        });
        return employeeLiveData;
    }

    // Update employee
    public LiveData<Employee> updateEmployee(Long id, Employee employee) {
        MutableLiveData<Employee> employeeLiveData = new MutableLiveData<>();
        apiService.updateEmployee(id, employee).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful() && response.body() != null) {
                    employeeLiveData.setValue(response.body());
                } else {
                    Log.e("EmployeeRepository", "Error updating employee, response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e("EmployeeRepository", "API Call failed: " + t.getMessage());
            }
        });
        return employeeLiveData;
    }

    // Delete employee
    public LiveData<Boolean> deleteEmployee(Long id) {
        MutableLiveData<Boolean> deleteSuccess = new MutableLiveData<>();
        apiService.deleteEmployee(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                deleteSuccess.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("EmployeeRepository", "API Call failed: " + t.getMessage());
                deleteSuccess.setValue(false);
            }
        });
        return deleteSuccess;
    }
}
