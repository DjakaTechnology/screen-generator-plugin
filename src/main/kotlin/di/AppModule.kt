package di

import dagger.Binds
import dagger.Module
import data.file.ProjectStructure
import data.file.ProjectStructureImpl
import data.repository.SettingsRepository
import data.repository.SettingsRepositoryImpl

@Module
interface AppModule {

    @Binds
    fun provideProjectStructure(structure: ProjectStructureImpl): ProjectStructure

    @Binds
    fun provideSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository
}