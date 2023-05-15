package sapsan.module.build

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Configuration
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

class Custom extends Module {

    Script buildScript
    String buildScriptFile = "build.groovy"
    String buildScriptPath = ".ci/$buildScriptFile"

    @Override
    protected Map getProperties() {
        return Configuration.properties['build']
    }

    @Override
    void initProperties() {}

    @Override
    @NonCPS
    void checkProperties() {}

    /**
     * Запуск кастомного скрипта для сборки проекта
     * @param name название шага пайплайна
     */
    @Override
    def execute() {
        initCustomScript()
        Pipeline.stage(buildScript.name) {
            initProperties()
            buildScript.execute(properties)
        }
    }

    private void initCustomScript() {
        String buildScriptText = ""
        try {
            buildScriptText = script.libraryResource("$Configuration.root/$Job.name/$buildScriptFile")
            script.prependToFile(file: buildScriptPath, content: buildScriptText)
            buildScript = script.load buildScriptPath
        } catch (Exception e) {
            Log.error("Build overriding is thrown exception: $e.message")
        }
        if (buildScriptText.size() == 0 || buildScript == null)
            Log.error("No custom Build found!")
        Log.var buildScriptText.size()
    }
}
