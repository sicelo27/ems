package com.employees.ems.android.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.employees.ems.android.R;
import com.employees.ems.android.model.Employee;
import com.employees.ems.android.viewmodel.EmployeeViewModel;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        employeeViewModel = new EmployeeViewModel(getApplication());

        recyclerView = findViewById(R.id.recyclerViewEmployees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employeeViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                employeeAdapter = new EmployeeAdapter(employees);
                recyclerView.setAdapter(employeeAdapter);
            }
        });
    }
}
