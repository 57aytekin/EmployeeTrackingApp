package com.example.employeetrackingapp.di

import android.content.Context
import androidx.room.Room
import com.example.employeetrackingapp.db.AppDatabase
import com.example.employeetrackingapp.db.EmployeeDao
import com.example.employeetrackingapp.other.PrefUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_db"
    ).fallbackToDestructiveMigration().allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun providePrefUtil(@ApplicationContext context: Context) = PrefUtils.with(context)

    @Singleton
    @Provides
    fun provideEmployeeDao(db : AppDatabase) = db.employeeDao()

    @Singleton
    @Provides
    fun provideProcessDao(db : AppDatabase) = db.processDao()

    @Singleton
    @Provides
    fun provideCrossRef(db : AppDatabase) = db.crossRefDao()
}