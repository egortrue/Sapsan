package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Context
import sapsan.core.Job
import sapsan.module.Module
import sapsan.util.Log

abstract class Build extends Context implements Module {
    static void execute() {
        script.prependToFile(file: "build.groovy",
                content: script.libraryResource("$Configuration.root/$Job.name/build.groovy"))
        def build = script.load "build.groovy"
        Log.info build.toString()
    }
}
