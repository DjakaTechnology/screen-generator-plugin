package data.file

import com.intellij.openapi.application.ApplicationManager
import javax.inject.Inject

interface WriteActionDispatcher {
    fun dispatch(action: () -> Unit)
}

class WriteActionDispatcherImpl @Inject constructor() : WriteActionDispatcher {

    override fun dispatch(action: () -> Unit) = ApplicationManager.getApplication().runWriteAction {
        action()
    }
}
