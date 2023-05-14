package sapsan.module.build

import sapsan.core.Pipeline
import sapsan.core.Context
import sapsan.module.Module
import sapsan.util.Log

class Docker extends Context implements Module {

    static private String name
    static private String image
    static private String file

    @Override
    void initProperties(AbstractMap properties) {
        name = properties.key
        image = properties.value["image"]
        file = properties.value["dockerfile"]

        assert name != null
        assert image != null
        assert file != null
    }

    static void build() {
        Pipeline.stage("Build Docker") {
            Pipeline.properties["docker"].each {
                Log.info it.class.name
                initProperties(it)

            }
        }
    }
}
