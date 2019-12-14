package ui.settings

import dagger.Binds
import dagger.Module

@Module
interface SettingsModule {

    @Binds
    fun provideSettingsView(view: SettingsViewImpl): SettingsView
}