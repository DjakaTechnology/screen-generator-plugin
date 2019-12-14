package data.repository

import data.file.ProjectStructure
import javax.inject.Inject

interface ModuleRepository {

    fun getAllModules(): List<String>
}

class ModuleRepositoryImpl @Inject constructor(private val projectStructure: ProjectStructure) : ModuleRepository {

    override fun getAllModules() =
        projectStructure.getAllModules().filter { !it.contains(projectStructure.getProjectName()) }
}