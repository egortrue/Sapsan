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
    void initProperties(def properties) {
        image = properties["image"]
        file = properties["dockerfile"]
    }

    @Override
    void checkProperties(def properties) {
        assert properties != null
        assert properties["image"] != null
        assert properties["dockerfile"] != null
    }

    static void build() {
        Pipeline.properties["docker"].each {
            getInstance().checkProperties(it)
        }

        Pipeline.stage("Build Docker") {

        }
    }
}
