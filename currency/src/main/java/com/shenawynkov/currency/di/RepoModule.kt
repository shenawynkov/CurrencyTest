package com.shenawynkov.currency.di



import com.shenawynkov.currency.data.api.ApiService
import com.shenawynkov.currency.data.db.CurrenciesDatabase
import com.shenawynkov.currency.data.repo.CurrenciesRepoImpl
import com.shenawynkov.currency.domain.repo.CurrenciesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideCurrenciesRepository(apiService: ApiService, db: CurrenciesDatabase): CurrenciesRepo {
        return CurrenciesRepoImpl(apiService,db)
    }

}