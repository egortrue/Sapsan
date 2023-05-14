package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

class CustomBuild extends Module {

    static Script buildScript
    static String buildScriptPath = ".ci/build.groovy"

    /**
     * Запуск кастомного скрипта для сборки проекта
     * @param name название шага пайплайна
     */
    static void execute() {
        String buildScriptText = ""

        try {
            buildScriptText = script.libraryResource("$Configuration.root/$Job.name/build.groovy")
            script.prependToFile(file: buildScriptPath, content: buildScriptText)
            buildScript = script.load buildScriptPath
        } catch (Exception e) {
            Log.error("Build overriding is thrown exception: $e.message")
        }

        if (buildScriptText.size() == 0 || buildScript == null)
            Log.error("No custom build found!")


        Pipeline.stage(buildScript.getName()) {
            buildScript.call()
        }
    }
}
