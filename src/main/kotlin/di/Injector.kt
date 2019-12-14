package di

import com.intellij.openapi.project.Project

object Injector {

    lateinit var component: AppComponent

    fun init(project: Project) {
        component = DaggerAppComponent.factory().create(project)
    }
}