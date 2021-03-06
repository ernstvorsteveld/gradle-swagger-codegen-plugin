package org.hidetake.gradle.swagger.codegen

import org.gradle.api.tasks.*

/**
 * A task to generate code.
 *
 * @author Hidetake Iwata
 */
class SwaggerCodegen extends JavaExec {

    @Input
    String language

    @InputFile
    File inputFile

    @OutputDirectory
    File outputDir

    @Optional @Input
    String library

    @Optional @InputFile
    File configFile

    @Optional @InputDirectory
    File templateDir

    @TaskAction
    @Override
    void exec() {
        main = 'io.swagger.codegen.SwaggerCodegen'
        classpath(project.configurations.swaggerCodegen)
        args(buildOptions())
        super.exec()
    }

    private List<String> buildOptions() {
        assert language, "language should be set in the task $name"
        assert inputFile, "inputFile should be set in the task $name"
        assert outputDir, "outputDir should be set in the task $name"

        def options = []
        options << 'generate'
        options << '-l' << language
        options << '-i' << inputFile.path
        options << '-o' << outputDir.path
        if (library) {
            options << '--library' << library
        }
        if (configFile) {
            options << '-c' << configFile.path
        }
        if (templateDir) {
            options << '-t' << templateDir.path
        }
        options
    }

}
