package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Context
import sapsan.core.Job
import sapsan.module.Module

abstract class Build extends Context implements Module {
    static void execute() {
        libraryResource("$Configuration.root/$Job.name/build.groovy")
    }
}
