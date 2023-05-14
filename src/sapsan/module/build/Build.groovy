package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Context
import sapsan.core.Job
import sapsan.module.Module
import sapsan.util.Log

abstract class Build extends Context implements Module {
    static void execute() {
        def content = script.libraryResource("$Configuration.root/$Job.name/build.groovy")
        Log.info content
        script.prependToFile(file: "build.groovy",
                content: content)
        def build = script.load "build.groovy"
        script.sh "cat build.groovy"
    }
}
