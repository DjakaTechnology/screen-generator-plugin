package ui.settings.di

import dagger.Binds
import dagger.Module
import ui.settings.SettingsView
import ui.settings.SettingsViewImpl

@Module
interface SettingsModule {

    @Binds
    fun provideSettingsView(view: SettingsViewImpl): SettingsView
}