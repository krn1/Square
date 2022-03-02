// Generated by Dagger (https://dagger.dev).
package com.square.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SquareAppModule_ProvideApplicationFactory implements Factory<SquareApp> {
  private final SquareAppModule module;

  public SquareAppModule_ProvideApplicationFactory(SquareAppModule module) {
    this.module = module;
  }

  @Override
  public SquareApp get() {
    return provideApplication(module);
  }

  public static SquareAppModule_ProvideApplicationFactory create(SquareAppModule module) {
    return new SquareAppModule_ProvideApplicationFactory(module);
  }

  public static SquareApp provideApplication(SquareAppModule instance) {
    return Preconditions.checkNotNull(instance.provideApplication(), "Cannot return null from a non-@Nullable @Provides method");
  }
}