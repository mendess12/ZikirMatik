package com.yusufmendes.zikirmatik.data.di

import com.yusufmendes.zikirmatik.data.repository.HadithRepositoryImpl
import com.yusufmendes.zikirmatik.domain.repos.NameOfAllahRepository
import com.yusufmendes.zikirmatik.data.repository.NameOfAllahRepositoryImpl
import com.yusufmendes.zikirmatik.domain.repos.HadithRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindings {

    @Singleton
    @Binds
    abstract fun bindHadithRepository(hadithRepository: HadithRepositoryImpl): HadithRepository

    @Singleton
    @Binds
    abstract fun bindNameOfAllahRepository(nameOfAllahRepository: NameOfAllahRepositoryImpl): NameOfAllahRepository
}