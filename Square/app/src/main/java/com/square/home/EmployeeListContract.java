package com.square.home;

import com.square.model.Employee;

import java.util.List;

interface EmployeeListContract {
    interface View {
        void showSpinner();

        void hideSpinner();

        void showEmployeeList(List<Employee> employees);

        void updateEmployeeList(List<Employee> employees);

        boolean isRefreshing();

        void showError(String message);
    }

    interface Presenter {

        void start();

        void stop();

        void loadEmployees();
    }
}
