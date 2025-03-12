package com.employees.ems.android.api;

import com.employees.ems.android.model.ApiResponse;
import com.employees.ems.android.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EmployeeApiService {

    @GET("api/v1/employees")
    Call<ApiResponse<List<Employee>>> getAllEmployees();

    @GET("api/v1/employees/{id}")
    Call<ApiResponse<Employee>> getEmployeeById(@Path("id") Long id);

    @POST("api/v1/employees")
    Call<ApiResponse<Employee>> createEmployee(@Body Employee employee);

    @PUT("api/v1/employees/{id}")
    Call<ApiResponse<Employee>> updateEmployee(@Path("id") Long id, @Body Employee employee);

    @DELETE("api/v1/employees/{id}")
    Call<ApiResponse<?>> deleteEmployee(@Path("id") Long id);

    @GET("api/v1/employees/department/{departmentId}")
    Call<ApiResponse<List<Employee>>> getEmployeesByDepartmentId(@Path("departmentId") Long departmentId);

    @GET("api/v1/employees/search")
    Call<ApiResponse<List<Employee>>> searchEmployeesByName(@Query("name") String name);

    @GET("api/v1/employees/email-check")
    Call<ApiResponse<Boolean>> checkEmailAvailability(@Query("email") String email);
}