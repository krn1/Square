package com.square.home;

import com.square.model.Employee;
import com.square.model.EmployeeList;
import com.square.repository.RestApi;
import com.square.utils.TrampolineSchedulerUtils;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeListPresenterTest {
    @Mock
    private EmployeeListContract.View view;
    @Mock
    private RestApi apiService;

    private CompositeDisposable disposable;

    private EmployeeListPresenter presenter;

    @BeforeClass
    public static void setUpClass() {
        TrampolineSchedulerUtils.convertSchedulersToTrampoline();
    }

    @Before
    public void setUp() throws Exception {
        disposable = spy(new CompositeDisposable());

        presenter = new EmployeeListPresenter(view, apiService, disposable);
    }

    @Test
    public void loadEmployees() throws Exception {
        // Given
        EmployeeList employeeList = createEmployeeList(15);

        when(apiService.getEmployees()).thenReturn(Flowable.just(employeeList));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showSpinner();
        verify(view, times(1)).hideSpinner();
        verify(disposable, times(1)).add(any(Disposable.class));
        verify(view, times(1)).showEmployeeList(employeeList.get());
    }

    @Test
    public void pullToRefresh() throws Exception {
        // Given
        EmployeeList employeeList = createEmployeeList(25);

        when(apiService.getEmployees()).thenReturn(Flowable.just(employeeList));
        when(view.isRefreshing()).thenReturn(true);

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showSpinner();
        verify(view, times(1)).hideSpinner();
        verify(view, times(1)).updateEmployeeList(employeeList.get());
    }

    @Test
    public void emptyProductList() throws Exception {
        // Given
        EmployeeList emptyList = createEmptyList();

        when(apiService.getEmployees()).thenReturn(Flowable.just(emptyList));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showSpinner();
        verify(view, times(1)).hideSpinner();
        verify(disposable, times(1)).add(any(Disposable.class));
        verify(view, times(1)).showError("emptyList");
        verify(view, times(0)).showEmployeeList(emptyList.get());
    }

    @Test
    public void malFormedUUIDList() throws Exception {
        // Given
        EmployeeList emptyList = createMalformedListWith(true,false);

        when(apiService.getEmployees()).thenReturn(Flowable.error(new Exception("Missing field in JSON")));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showSpinner();
        verify(disposable, times(1)).add(any(Disposable.class));
        verify(view, times(1)).showError("Missing field in JSON");
        verify(view, times(0)).showEmployeeList(emptyList.get());
    }

    @Test
    public void malFormedEmailList() throws Exception {
        // Given
        EmployeeList emptyList = createMalformedListWith(false,true);

        when(apiService.getEmployees()).thenReturn(Flowable.error(new Exception("Missing field in JSON")));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showSpinner();
        verify(disposable, times(1)).add(any(Disposable.class));
        verify(view, times(1)).showError("Missing field in JSON");
        verify(view, times(0)).showEmployeeList(emptyList.get());
    }

    @Test
    public void networkIssue() throws Exception {
        // Given
        EmployeeList emptyList = createEmptyList();

        when(apiService.getEmployees()).thenReturn(Flowable.error(new Exception("Unknown host")));

        // When
        presenter.start();

        // Then
        verify(view, times(1)).showSpinner();
        verify(disposable, times(1)).add(any(Disposable.class));
        verify(view, times(1)).showError("Unknown host");
    }
    // region private

    private EmployeeList createEmptyList() {
        List<Employee> emptyList = Collections.emptyList();
        EmployeeList emptyProductList = new EmployeeList();
        emptyProductList.set(emptyList);
        return emptyProductList;
    }

    private EmployeeList createMalformedListWith(boolean isUUidNull, boolean isEmailNull) {
        EmployeeList employeeList = new EmployeeList();
        List<Employee> employees = createProducts(5);
        Employee  employee = employees.get(2);
        if(isUUidNull) {
            employee.setUuid(null);
        }
        if(isEmailNull) {
            employee.setTeam(null);
        }
        employeeList.set(employees);
        return employeeList;
    }

    private EmployeeList createEmployeeList(int size) {
        List<Employee> employees = createProducts(size);
        EmployeeList employeeList = new EmployeeList();
        employeeList.set(employees);
        return employeeList;
    }

    private List<Employee> createProducts(int size) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            employeeList.add(createEmployee(i));
        }
        return employeeList;
    }

    private Employee createEmployee(int id) {
        Employee employee = new Employee();
        employee.setUuid(String.valueOf(id));
        employee.setFullName("emp"+id);
        employee.setPhotoUrlSmall("img"+id);
        employee.setPhotoUrlLarge("IMG"+id);
        employee.setEmailAddress("email"+id);
        employee.setEmployeeType("type");
        employee.setPhoneNumber("ph"+id);
        employee.setBiography("bio");

        return employee;
    }
    //endregion
}
