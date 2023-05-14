package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Pipeline
import sapsan.module.Module

@Singleton
class Python extends Module {

    @Override
    void initProperties(Map properties) {

    }

    @Override
    void checkProperties(Map properties) {

    }

    static void archive() {
        getInstance().checkProperties(Configuration.properties["docker"])
        Pipeline.stage("Archive Python") {

        }
    }
}
