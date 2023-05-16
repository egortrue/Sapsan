package sapsan.module

import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Job
import sapsan.util.Log

abstract class Build extends Module {

    static Script buildScript
    static String buildScriptFile = "build.groovy"
    static String buildScriptPath = "$Config.root/$Job.name/$buildScriptFile"

    /**
     *
     */
    private static void initCustomScript(String scriptPath) {
        String buildScriptText = ""
        try {
            buildScriptText = Context.pipeline.libraryResource(scriptPath)

            GroovyClassLoader.parseClass()

            buildScript = Context.pipeline.load buildScriptPath
        } catch (Exception e) {
            Log.error("Module overriding is thrown exception: $e.message")
        }

        if (buildScriptText.size() == 0 || buildScript == null)
            Log.error("No custom Build found!")

    }

}
