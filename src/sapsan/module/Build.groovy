package sapsan.module

import sapsan.core.Config
import sapsan.core.Context
import sapsan.util.Log

final class Build extends Module {

    Script script
    String scriptFilename = "build.groovy"

    @Override
    protected String getStageName() {
        return script.stageName ?: super.stageName
    }

    @Override
    protected void checkProperties() {
        loadScript()
        script.checkProperties()
    }

    @Override
    protected void initProperties() {
        script.initProperties()
    }

    @Override
    protected void execute() {
        script.execute()
    }

    private void loadScript() {
        try {
            String scriptText = Context.pipeline.libraryResource("${Config.projectDir}/$scriptFilename")
            Context.pipeline.prependToFile(file: scriptFilename, content: scriptText)
            script = Context.pipeline.load scriptFilename
            Context.pipeline.sh "rm -f $scriptFilename"
        } catch (Exception e) {
            Log.error("Build loading script threw exception: $e.message")
        }
    }
}
