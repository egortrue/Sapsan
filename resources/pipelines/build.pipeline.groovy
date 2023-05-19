@Library("sapsan@master") _

import sapsan.core.Config
import sapsan.core.Pipeline
import sapsan.module.Checkout
import sapsan.module.Module

Pipeline.run(this, "agent") {
    Module.execute(Checkout)
    Module.execute(Config.getProjectFile("build.module.groovy"))
    Module.execute(Config.getProjectFile("run.module.groovy"))
}