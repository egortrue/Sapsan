package sapsan.module.build

import sapsan.core.Pipeline
import sapsan.core.Context
import sapsan.module.Module
import sapsan.util.Log

@Singleton
class Docker extends Context implements Module {

    private String name
    private String image
    private String file

    @Override
    void initProperties(Object properties) {
        image = properties["image"]
        file = properties["dockerfile"]
    }

    @Override
    void checkProperties() {
        assert image != null
        assert file != null
    }

    static void build() {
        Pipeline.properties["docker"].each {
            instance.initProperties(it)
            instance.checkProperties()
        }

        Pipeline.stage("Build Docker") {

        }
    }
}
