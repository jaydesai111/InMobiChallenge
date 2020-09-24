package com.jay.inmobochallenge.di.module

import com.jay.inmobochallenge.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contribiuteHomeFragment(): HomeFragment
}