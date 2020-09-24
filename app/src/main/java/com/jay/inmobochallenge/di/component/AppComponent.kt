package com.jay.inmobochallenge.di.component

import android.app.Application


import com.jay.inmobochallenge.MyApplication
import com.jay.inmobochallenge.di.module.FragmentModule
import com.jay.inmobochallenge.di.module.AppModule
import com.jay.inmobochallenge.di.module.RepositoryModule
import com.jay.inmobochallenge.di.module.ViewModelModule



import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, AppModule::class, FragmentModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }
}