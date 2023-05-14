package sapsan.module.build

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Pipeline
import sapsan.module.Module

class Docker extends Module {

    private String name
    private String image
    private String file

    @Override
    void initProperties() {
        image = properties["image"]
        file = properties["dockerfile"]
    }

    @Override
    @NonCPS
    void checkProperties() {
        assert properties != null
        assert properties["image"] != null
        assert properties["dockerfile"] != null
    }

    @Override
    def execute() {
        Pipeline.stage("Build Docker") {

        }
    }
}
