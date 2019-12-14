package ui.newscreen

import dagger.BindsInstance
import dagger.Subcomponent
import data.file.CurrentPath

@Subcomponent(modules = [NewScreenModule::class])
interface NewScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance dialog: NewScreenDialog, @BindsInstance currentPath: CurrentPath?): NewScreenComponent
    }

    fun inject(dialog: NewScreenDialog)
}