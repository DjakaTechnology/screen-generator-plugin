import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import di.Injector

class ScreenGeneratorStartupActivity : StartupActivity {

    override fun runActivity(project: Project) {
        Injector.init(project)
    }
}