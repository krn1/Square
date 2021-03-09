package com.square.home;

import com.square.scope.PerActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
class EmployeeListModule {
    private EmployeeListContract.View view;

    EmployeeListModule(EmployeeListContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    EmployeeListContract.View providesView() {
        return view;
    }

    @PerActivity
    @Provides
    CompositeDisposable providesDisposable() {
        return new CompositeDisposable();
    }
}
