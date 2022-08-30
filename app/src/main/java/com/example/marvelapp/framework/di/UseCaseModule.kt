package com.example.marvelapp.framework.di

import com.example.core.usecase.GetCharacterUseCaseImpl
import com.example.core.usecase.GetCharacterUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCharactersUseCase(useCase: GetCharacterUseCaseImpl): GetCharacterUseCase
}