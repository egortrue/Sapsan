package sapsan.module.build

import sapsan.core.Configuration
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
        Log.var properties
        Log.var properties["image"]
    }

    static void build() {
        instance.checkProperties(Configuration.properties["docker"])
        Pipeline.stage("Build Docker") {

        }
    }
}
