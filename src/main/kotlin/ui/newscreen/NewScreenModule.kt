package ui.newscreen

import dagger.Binds
import dagger.Module
import data.file.*
import data.repository.ModuleRepository
import data.repository.ModuleRepositoryImpl
import data.repository.SourceRootRepository
import data.repository.SourceRootRepositoryImpl

@Module
interface NewScreenModule {

    @Binds
    fun provideNewScreenView(dialog: NewScreenDialog): NewScreenView

    @Binds
    fun provideSourceRootRepository(repository: SourceRootRepositoryImpl): SourceRootRepository

    @Binds
    fun provideFileCreator(creator: FileCreatorImpl): FileCreator

    @Binds
    fun providePackageExtractor(extractor: PackageExtractorImpl): PackageExtractor

    @Binds
    fun provideWriteActionDispatcher(dispatcher: WriteActionDispatcherImpl): WriteActionDispatcher

    @Binds
    fun provideModuleRepository(repository: ModuleRepositoryImpl): ModuleRepository
}