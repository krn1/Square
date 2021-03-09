package com.square.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.square.R;
import com.square.app.SquareApp;
import com.square.databinding.ActivityEmployeeListBinding;
import com.square.model.Employee;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class EmployeeListActivity extends AppCompatActivity implements EmployeeListContract.View, EmployeeItemClickListener {

    @Inject
    EmployeeListPresenter presenter;
    private ActivityEmployeeListBinding binding;
    private EmployeeListAdapter adapter;

    // region Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_list);

        getComponent().inject(this);

        setupToolbar();
        setupRecycleView();
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchEmployees();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    // endregion override

    // region EmployeeList Business Contract

    @Override
    public void showSpinner() {
        if (!isRefreshing()) {
            binding.progressBar.Container.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideSpinner() {
        if (isLoading()) {
            binding.progressBar.Container.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmployeeList(List<Employee> employees) {
        binding.emptyView.setVisibility(View.GONE);
        binding.list.setVisibility(View.VISIBLE);
        binding.pullToRefresh.setRefreshing(false);
        adapter.display(employees);
    }

    @Override
    public void updateEmployeeList(List<Employee> employees) {
        binding.emptyView.setVisibility(View.GONE);
        binding.list.setVisibility(View.VISIBLE);
        binding.pullToRefresh.setRefreshing(false);
        adapter.update(employees);
    }

    @Override
    public boolean isRefreshing() {
        return binding.pullToRefresh.isRefreshing();
    }

    @Override
    public void showError(String message) {
        Timber.e("ERR: %s", message);
        binding.emptyView.setVisibility(View.VISIBLE);
        binding.list.setVisibility(View.GONE);
        stopRefreshing();
        hideSpinner();
        if (TextUtils.isEmpty(message)) {
            message = getResources().getString(R.string.nw_error_msg);
        }

        if (message.contains("Unable to resolve host")) {
            message = getResources().getString(R.string.nw_host_error);
        }

        if (message.contains("emptyList")) {
            message = getResources().getString(R.string.nw_empty_list);
        }
        if (message.contains("Missing field in JSON")) {
            message = getResources().getString(R.string.nw_malformed_msg);
        }
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // endregion

    // region private
    private EmployeeListComponent getComponent() {
        return DaggerEmployeeListComponent.builder()
                .squareAppComponent(((SquareApp) getApplication()).getComponent())
                .employeeListModule(new EmployeeListModule(this))
                .build();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void setupRecycleView() {
        binding.list.setVisibility(View.VISIBLE);
        binding.emptyView.setVisibility(View.GONE);
        adapter = new EmployeeListAdapter(this);
        binding.list.setAdapter(adapter);
        binding.pullToRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        binding.pullToRefresh.setOnRefreshListener(this::refreshFeed);
    }

    private boolean isLoading() {
        return binding.progressBar.Container.getVisibility() == View.VISIBLE;
    }

    private void stopRefreshing() {
        binding.pullToRefresh.setRefreshing(false);
    }

    private void refreshFeed() {
        binding.pullToRefresh.setRefreshing(true);
        binding.pullToRefresh.postDelayed(this::fetchEmployees, 500);
    }

    private void fetchEmployees() {
        presenter.start();
    }

    @Override
    public void onItemClick(Employee employee) {
        Timber.e("Employee clicked " + employee.getFullName());
    }

}