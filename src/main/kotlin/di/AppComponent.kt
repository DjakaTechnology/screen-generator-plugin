package di

import com.intellij.openapi.project.Project
import dagger.BindsInstance
import dagger.Component
import ui.newscreen.di.NewScreenComponent
import ui.settings.di.SettingsComponent

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance project: Project): AppComponent
    }

    fun newScreenFactory(): NewScreenComponent.Factory
    fun settingsFactory(): SettingsComponent.Factory
}