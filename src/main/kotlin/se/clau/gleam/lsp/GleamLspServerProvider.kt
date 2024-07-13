package se.clau.gleam.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import se.clau.gleam.lang.GleamFileType

class GleamLspServerProvider: LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
        ){
        if (file.fileType == GleamFileType) {
            serverStarter.ensureServerStarted(GleamLspServerDescriptor(project))
        }
    }
}