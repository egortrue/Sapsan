package sapsan.module.build

import sapsan.core.Pipeline
import sapsan.module.Module

class Docker extends Module {

    private String image
    private String file

    @Override
    protected void initProperties(Map properties) {
        image = properties["docker.image"]
        file = properties["docker.file"]

        assert image != null
        assert file != null
    }

    static void build() {
        initProperties(Pipeline.properties)
        Pipeline.stage("Build Docker") {

        }
    }
}
