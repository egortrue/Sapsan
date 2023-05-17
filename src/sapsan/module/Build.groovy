package sapsan.module

import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Job
import sapsan.util.Log

final class Build extends Module {

    String scriptFile
    private Script script

    @Override
    protected void precheck() {
        loadScript()
        script.precheck(properties)
    }

    @Override
    protected void init() {
        script.init(properties)
    }

    @Override
    protected void execute() {
        script.execute()
    }

    @Override
    protected String getStageName() {
        try {
            return script.getStageName()
        }
        catch (NoSuchMethodError e) {
            return super.getStageName()
        }
    }

    private void loadScript() {
        scriptFile = scriptFile ?: "${Config.projectDir}/build.groovy"

        try {
            String scriptText = Context.pipeline.libraryResource(scriptFile)
            Context.pipeline.prependToFile(file: "${Job.tag}-build.groovy", content: scriptText)
            script = Context.pipeline.load "${Job.tag}-build.groovy"
            Context.pipeline.sh "rm -f ${Job.tag}-build.groovy"
        } catch (Exception e) {
            Log.error("Build loading script threw exception: $e.message")
        }
    }
}
