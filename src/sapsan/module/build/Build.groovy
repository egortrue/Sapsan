package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

abstract class Build extends Context implements Module {

    static Script build

    static boolean isOverriden() {
        try {
            String buildScript = script.libraryResource("$Configuration.root/$Job.name/build.groovy")
            script.prependToFile(file: "build.groovy", content: buildScript)
            build = script.load "build.groovy"
            return buildScript.size() != 0 && build != null
        } catch (Exception e) {
            Log.warning("Build overriding is thrown exception: $e.message")
            return false
        }
    }


    static void execute() {
        if (!isOverriden())
            build.call()
        else {
            Pipeline.properties.each {
                switch (it.key) {
                    case ("python"):
                        Python.make()
                    case ("docker"):
                        Docker.build()
                }
            }
        }
    }
}
