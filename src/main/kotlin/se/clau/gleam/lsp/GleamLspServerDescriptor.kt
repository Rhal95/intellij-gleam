package se.clau.gleam.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.LspFormattingSupport
import se.clau.gleam.lang.GleamFileType
import java.nio.charset.StandardCharsets

class GleamLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Gleam") {
    override fun isSupportedFile(file: VirtualFile) = file.fileType == GleamFileType
    override fun createCommandLine(): GeneralCommandLine {
        val commandLine = GeneralCommandLine("gleam", "lsp")
        commandLine.charset = StandardCharsets.UTF_8
        return commandLine
    }
    override val lspFormattingSupport = object : LspFormattingSupport() {
        override fun shouldFormatThisFileExclusivelyByServer(
            file: VirtualFile,
            ideCanFormatThisFileItself: Boolean,
            serverExplicitlyWantsToFormatThisFile: Boolean
        ) = true
    }
}
