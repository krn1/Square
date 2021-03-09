package com.square.app;

import dagger.Module;
import dagger.Provides;

@Module
class SquareAppModule {

    private final SquareApp application;

    SquareAppModule(SquareApp application) {
        this.application = application;
    }

    @Provides
    public SquareApp provideApplication() {
        return application;
    }

}
