package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Pipeline
import sapsan.module.Module

class Python extends Module {

    @Override
    void initProperties(Map properties) {

    }

    @Override
    void checkProperties(Map properties) {

    }

    def execute() {
        checkProperties(Configuration.properties["docker"])
        Pipeline.stage("Archive Python") {

        }
    }
}
