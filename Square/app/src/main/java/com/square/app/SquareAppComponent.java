package com.square.app;

import com.square.repository.NetworkModule;
import com.square.repository.RestApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SquareAppModule.class, NetworkModule.class})
public interface SquareAppComponent {

    void inject(SquareApp application);

    SquareApp application();

    RestApi restApi();
}
