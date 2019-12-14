package di

import com.intellij.openapi.project.Project
import dagger.BindsInstance
import dagger.Component
import ui.newscreen.NewScreenComponent

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance project: Project): AppComponent
    }

    fun newScreenFactory(): NewScreenComponent.Factory
}