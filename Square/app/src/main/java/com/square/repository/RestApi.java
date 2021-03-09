package com.square.repository;

import com.square.model.EmployeeList;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("/sq-mobile-interview/employees.json")
    Flowable<EmployeeList> getEmployees();
}
