package com.employees.ems.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.employees.ems.android.R;
import com.employees.ems.android.model.Employee;
import com.employees.ems.android.repository.EmployeeRepository;
import com.employees.ems.android.viewmodel.EmployeeViewModel;

import java.math.BigDecimal;

public class EmployeeFormActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhoneNumber, editTextSalary;
    private Button buttonSaveEmployee;

    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_form);

        // Initialize Views
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextSalary = findViewById(R.id.editTextSalary);
        buttonSaveEmployee = findViewById(R.id.buttonSaveEmployee);

        // Get the EmployeeViewModel
        employeeViewModel = new EmployeeViewModel(getApplication());

        // Check if the activity is for creating a new employee or updating an existing one
        Intent intent = getIntent();
        Long employeeId = intent.getLongExtra("EMPLOYEE_ID", -1);

        if (employeeId != -1) {
            // Populate fields for updating
            employeeViewModel.getEmployeeById(employeeId).observe(this, employee -> {
                editTextFirstName.setText(employee.getFirstName());
                editTextLastName.setText(employee.getLastName());
                editTextEmail.setText(employee.getEmail());
                editTextPhoneNumber.setText(employee.getPhoneNumber());
                editTextSalary.setText(employee.getSalary().toString());
            });
        }

        buttonSaveEmployee.setOnClickListener(view -> saveEmployee(employeeId));
    }

    private void saveEmployee(Long employeeId) {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String salaryString = editTextSalary.getText().toString().trim();

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(salaryString)) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert salary string to BigDecimal
        BigDecimal salary = new BigDecimal(salaryString);

        // Create a new employee object
        Employee employee = new Employee(employeeId, firstName, lastName, email, phoneNumber, null, salary, null);

        if (employeeId != -1) {
            // Update existing employee
            employeeViewModel.updateEmployee(employee);
            Toast.makeText(this, "Employee updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Create new employee
            employeeViewModel.createEmployee(employee);
            Toast.makeText(this, "Employee created successfully", Toast.LENGTH_SHORT).show();
        }

        // Go back to employee list
        finish();
    }
}
