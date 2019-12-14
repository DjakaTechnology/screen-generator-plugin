package ui.newscreen.di

import dagger.BindsInstance
import dagger.Subcomponent
import data.file.CurrentPath
import ui.newscreen.NewScreenDialog

@Subcomponent(modules = [NewScreenModule::class])
interface NewScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance dialog: NewScreenDialog, @BindsInstance currentPath: CurrentPath?): NewScreenComponent
    }

    fun inject(dialog: NewScreenDialog)
}