package ui.settings

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: SettingsViewImpl): SettingsComponent
    }

    fun inject(view: SettingsViewImpl)
}