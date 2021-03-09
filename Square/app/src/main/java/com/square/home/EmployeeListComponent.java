package com.square.home;

import com.square.app.SquareAppComponent;
import com.square.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = SquareAppComponent.class, modules = EmployeeListModule.class)
interface EmployeeListComponent {
    void inject(EmployeeListActivity employeeListActivity);
}
