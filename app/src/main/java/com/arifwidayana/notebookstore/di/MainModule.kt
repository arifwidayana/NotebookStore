package com.arifwidayana.notebookstore.di

import android.content.Context
import com.arifwidayana.notebookstore.common.base.BaseViewModel
import com.arifwidayana.notebookstore.data.local.StoreDatabase
import com.arifwidayana.notebookstore.data.local.datasource.LocalDatasource
import com.arifwidayana.notebookstore.data.local.datasource.LocalDatasourceImpl
import com.arifwidayana.notebookstore.data.local.datasource.UserPreferenceDatasource
import com.arifwidayana.notebookstore.data.local.datasource.UserPreferenceDatasourceImpl
import com.arifwidayana.notebookstore.data.local.model.dao.StoreDao
import com.arifwidayana.notebookstore.data.local.model.dao.UserDao
import com.arifwidayana.notebookstore.data.local.repository.*
import com.arifwidayana.notebookstore.presentation.ui.add.AddProductViewModel
import com.arifwidayana.notebookstore.presentation.ui.edit.EditProductViewModel
import com.arifwidayana.notebookstore.presentation.ui.login.LoginViewModel
import com.arifwidayana.notebookstore.presentation.ui.main.MainViewModel
import com.arifwidayana.notebookstore.presentation.ui.profile.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideStoreDatabase(
        @ApplicationContext context: Context,
    ): StoreDatabase = StoreDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserDao(storeDatabase: StoreDatabase) = storeDatabase.userDao()

    @Provides
    @Singleton
    fun provideStoreDao(storeDatabase: StoreDatabase) = storeDatabase.storeDao()
}

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {
    @Provides
    @Singleton
    fun provideLocalDatasource(userDao: UserDao, storeDao: StoreDao): LocalDatasource {
        return LocalDatasourceImpl(userDao, storeDao)
    }

    @Provides
    @Singleton
    fun provideUserPreferenceDatasource(@ApplicationContext context: Context): UserPreferenceDatasource {
        return UserPreferenceDatasourceImpl(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(
        localDatasource: LocalDatasource,
        userPreferenceDatasource: UserPreferenceDatasource
    ): LoginRepository {
        return LoginRepositoryImpl(localDatasource, userPreferenceDatasource)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        localDatasource: LocalDatasource,
        userPreferenceDatasource: UserPreferenceDatasource
    ): MainRepository {
        return MainRepositoryImpl(localDatasource, userPreferenceDatasource)
    }

    @Provides
    @Singleton
    fun provideAddProductRepository(localDatasource: LocalDatasource): AddProductRepository {
        return AddProductRepositoryImpl(localDatasource)
    }

    @Provides
    @Singleton
    fun provideEditProductRepository(localDatasource: LocalDatasource): EditProductRepository {
        return EditProductRepositoryImpl(localDatasource)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        localDatasource: LocalDatasource,
        userPreferenceDatasource: UserPreferenceDatasource
    ): ProfileRepository {
        return ProfileRepositoryImpl(localDatasource, userPreferenceDatasource)
    }
}

@Module
@InstallIn(ActivityComponent::class)
object ActivityViewModelModule {
    @Provides
    @ActivityScoped
    fun provideLoginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return BaseViewModel(LoginViewModel(loginRepository)).create(
            LoginViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideMainViewModel(mainRepository: MainRepository): MainViewModel {
        return BaseViewModel(MainViewModel(mainRepository)).create(
            MainViewModel::class.java
        )
    }
}

@Module
@InstallIn(FragmentComponent::class)
object FragmentViewModelModule {
    @Provides
    @FragmentScoped
    fun provideAddProductViewModel(addProductRepository: AddProductRepository): AddProductViewModel {
        return BaseViewModel(AddProductViewModel(addProductRepository)).create(
            AddProductViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideEditProductViewModel(editProductRepository: EditProductRepository): EditProductViewModel {
        return BaseViewModel(EditProductViewModel(editProductRepository)).create(
            EditProductViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideProfileViewModel(profileRepository: ProfileRepository): ProfileViewModel {
        return BaseViewModel(ProfileViewModel(profileRepository)).create(
            ProfileViewModel::class.java
        )
    }
}
