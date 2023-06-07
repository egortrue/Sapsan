@Library("sapsan@master") _

import sapsan.core.Config
import sapsan.core.Pipeline
import sapsan.module.Module

Pipeline.run(this, "agent") {
    Module.execute(Config.getProjectFile("run.module.groovy"))
}