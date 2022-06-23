package com.fahriaf.fruitdetection.di

import com.fahriaf.fruitdetection.core.domain.usecase.FruitInteractor
import com.fahriaf.fruitdetection.core.domain.usecase.FruitUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideFruitUseCase(fruitInteractor: FruitInteractor): FruitUseCase
}