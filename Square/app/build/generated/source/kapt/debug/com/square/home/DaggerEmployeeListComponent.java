// Generated by Dagger (https://dagger.dev).
package com.square.home;

import com.square.app.SquareAppComponent;
import com.square.repository.RestApi;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
final class DaggerEmployeeListComponent implements EmployeeListComponent {
  private Provider<EmployeeListContract.View> providesViewProvider;

  private Provider<RestApi> restApiProvider;

  private Provider<CompositeDisposable> providesDisposableProvider;

  private Provider<EmployeeListPresenter> employeeListPresenterProvider;

  private DaggerEmployeeListComponent(EmployeeListModule employeeListModuleParam,
      SquareAppComponent squareAppComponentParam) {

    initialize(employeeListModuleParam, squareAppComponentParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final EmployeeListModule employeeListModuleParam,
      final SquareAppComponent squareAppComponentParam) {
    this.providesViewProvider = DoubleCheck.provider(EmployeeListModule_ProvidesViewFactory.create(employeeListModuleParam));
    this.restApiProvider = new com_square_app_SquareAppComponent_restApi(squareAppComponentParam);
    this.providesDisposableProvider = DoubleCheck.provider(EmployeeListModule_ProvidesDisposableFactory.create(employeeListModuleParam));
    this.employeeListPresenterProvider = DoubleCheck.provider(EmployeeListPresenter_Factory.create(providesViewProvider, restApiProvider, providesDisposableProvider));
  }

  @Override
  public void inject(EmployeeListActivity employeeListActivity) {
    injectEmployeeListActivity(employeeListActivity);}

  private EmployeeListActivity injectEmployeeListActivity(EmployeeListActivity instance) {
    EmployeeListActivity_MembersInjector.injectPresenter(instance, employeeListPresenterProvider.get());
    return instance;
  }

  static final class Builder {
    private EmployeeListModule employeeListModule;

    private SquareAppComponent squareAppComponent;

    private Builder() {
    }

    public Builder employeeListModule(EmployeeListModule employeeListModule) {
      this.employeeListModule = Preconditions.checkNotNull(employeeListModule);
      return this;
    }

    public Builder squareAppComponent(SquareAppComponent squareAppComponent) {
      this.squareAppComponent = Preconditions.checkNotNull(squareAppComponent);
      return this;
    }

    public EmployeeListComponent build() {
      Preconditions.checkBuilderRequirement(employeeListModule, EmployeeListModule.class);
      Preconditions.checkBuilderRequirement(squareAppComponent, SquareAppComponent.class);
      return new DaggerEmployeeListComponent(employeeListModule, squareAppComponent);
    }
  }

  private static class com_square_app_SquareAppComponent_restApi implements Provider<RestApi> {
    private final SquareAppComponent squareAppComponent;

    com_square_app_SquareAppComponent_restApi(SquareAppComponent squareAppComponent) {
      this.squareAppComponent = squareAppComponent;
    }

    @Override
    public RestApi get() {
      return Preconditions.checkNotNull(squareAppComponent.restApi(), "Cannot return null from a non-@Nullable component method");
    }
  }
}
