package com.fahriaf.fruitdetection.core.di

import com.fahriaf.fruitdetection.core.data.FruitRepository
import com.fahriaf.fruitdetection.core.domain.repository.IFruitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideFruitRepository(fruitRepository: FruitRepository): IFruitRepository
}