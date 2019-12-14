package di

import com.intellij.openapi.project.Project

object Injector {

    lateinit var component: AppComponent

    fun init(project: Project) {
        if (!::component.isInitialized) {
            component = DaggerAppComponent.factory().create(project)
        }
    }
}