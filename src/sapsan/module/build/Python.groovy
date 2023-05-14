package sapsan.module.build

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Configuration
import sapsan.core.Pipeline
import sapsan.module.Module

class Python extends Module {

    @Override
    void initProperties() {

    }

    @Override
    @NonCPS
    void checkProperties() {

    }

    def execute() {
        checkProperties(Configuration.properties["docker"])
        Pipeline.stage("Archive Python") {

        }
    }
}
