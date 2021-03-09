package com.square.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import timber.log.Timber;

public class SquareApp extends Application {

    private SquareAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // dependency injection object graph
        component = DaggerSquareAppComponent.builder().squareAppModule(new SquareAppModule(this)).build();
        component.inject(this);

        Timber.plant(new Timber.DebugTree());
        initializeFresco();
    }

    public SquareAppComponent getComponent() {
        return component;
    }

    // region private

    private void initializeFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    //endregion

}
