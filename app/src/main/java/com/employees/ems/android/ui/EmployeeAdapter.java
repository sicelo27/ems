package com.employees.ems.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.employees.ems.android.R;
import com.employees.ems.android.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private final List<Employee> employeeList;

    public EmployeeAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.nameTextView.setText(employee.getFirstName() + " " + employee.getLastName());
        holder.emailTextView.setText(employee.getEmail());
        holder.phoneTextView.setText(employee.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, phoneTextView;

        public EmployeeViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameTextView);
            emailTextView = view.findViewById(R.id.emailTextView);
            phoneTextView = view.findViewById(R.id.phoneTextView);
        }
    }
}
