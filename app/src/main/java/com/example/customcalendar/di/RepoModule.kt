package com.example.customcalendar.di

import com.example.customcalendar.data.repository.TaskRepository
import com.example.customcalendar.data.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepoModule {

    @Binds
    fun bindRepo(
        repo: TaskRepositoryImpl
    ): TaskRepository
}