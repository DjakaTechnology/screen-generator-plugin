package ui.settings.di

import dagger.BindsInstance
import dagger.Subcomponent
import ui.settings.SettingsViewImpl

@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: SettingsViewImpl): SettingsComponent
    }

    fun inject(view: SettingsViewImpl)
}