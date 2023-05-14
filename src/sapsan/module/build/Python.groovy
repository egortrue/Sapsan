package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Context
import sapsan.core.Pipeline
import sapsan.module.Module

@Singleton
class Python extends Context implements Module {

    @Override
    void initProperties(Object properties) {

    }

    @Override
    void checkProperties(Object properties) {

    }

    static void archive() {
        getInstance().checkProperties(Configuration.properties["docker"])
        Pipeline.stage("Archive Python") {

        }
    }
}
