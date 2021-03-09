package com.square.home;

import com.square.model.Employee;
import com.square.model.EmployeeList;
import com.square.repository.RestApi;
import com.square.scope.PerActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;

@PerActivity
class EmployeeListPresenter implements EmployeeListContract.Presenter {

    private EmployeeListContract.View view;
    private CompositeDisposable disposable;
    private RestApi apiService;

    @Inject
    EmployeeListPresenter(EmployeeListContract.View view,
                          RestApi apiService,
                          CompositeDisposable disposable) {
        this.view = view;
        this.apiService = apiService;
        this.disposable = disposable;
    }

    @Override
    public void start() {
        loadEmployees();
    }

    @Override
    public void stop() {
        disposable.clear();
    }

    @Override
    public void loadEmployees() {
        view.showSpinner();
        disposable.add(apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<EmployeeList>() {
                    @Override
                    public void onNext(EmployeeList employeeList) {
                        List<Employee> employees = employeeList.get();
                        Timber.d("We got size %d", employees.size());
                        onRetrieveComplete(employees);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.showError(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    private void onRetrieveComplete(final List<Employee> employeeList) {
        view.hideSpinner();

        if (employeeList == null || employeeList.isEmpty()) {
            view.showError("emptyList");
            return;
        }

        // Sorting  Alphabetically
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getFullName().compareTo(employee2.getFullName());
            }

        });
        if (view.isRefreshing()) {
            view.updateEmployeeList(employeeList);
        } else {
            view.showEmployeeList(employeeList);
        }
    }

}
