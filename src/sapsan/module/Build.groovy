package sapsan.module

import sapsan.core.Config
import sapsan.core.Context
import sapsan.util.Log

final class Build extends Module {

    Script script
    String scriptFilename = "build.groovy"

    @Override
    protected void checkProperties() {
        loadScript()
    }

    @Override
    protected void initProperties() {

    }

    @Override
    protected void execute() {

    }

    private void loadScript() {
        try {
            String scriptText = Context.pipeline.libraryResource("${Config.projectDir}/$scriptFilename")
            script = Context.pipeline.load scriptText
        } catch (Exception e) {
            Log.error("Build loading script threw exception: $e.message")
        }
    }
}
