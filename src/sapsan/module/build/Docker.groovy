package sapsan.module.build

import sapsan.core.Configuration
import sapsan.core.Pipeline
import sapsan.module.Module

class Docker extends Module {

    private String name
    private String image
    private String file

    @Override
    void initProperties(Map properties) {
        image = properties["image"]
        file = properties["dockerfile"]
    }

    @Override
    void checkProperties(Map properties) {
        assert properties != null
        assert properties["image"] != null
        assert properties["dockerfile"] != null
    }

    @Override
    def execute() {
        checkProperties(Configuration.properties["docker"])
        Pipeline.stage("Build Docker") {

        }
    }
}
