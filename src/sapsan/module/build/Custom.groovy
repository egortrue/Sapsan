package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

@Singleton
class Custom extends Module {

    static Script buildScript
    static String buildScriptPath = ".ci/build.groovy"

    @Override
    void initProperties(Map properties) {
        buildScript.initProperties(properties)
    }

    @Override
    void checkProperties(Map properties) {
        buildScript.checkProperties(properties)
    }

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

        Log.var buildScriptText.size()

        getInstance().checkProperties(Configuration.properties["build"])
        Pipeline.stage(buildScript.name) {
            getInstance().initProperties(Configuration.properties["build"])
            buildScript.call()
        }
    }
}
