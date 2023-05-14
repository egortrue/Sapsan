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
    void checkProperties(Object properties) {
        image = properties["image"]
        file = properties["dockerfile"]

        assert image != null
        assert file != null
    }

    static void build() {
        Pipeline.properties["docker"].each {
            instance.checkProperties(it)
        }

        Pipeline.stage("Build Docker") {

        }
    }
}
