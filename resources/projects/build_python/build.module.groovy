import sapsan.core.Config
import sapsan.core.Pipeline
import sapsan.module.Module

class Build extends Module {
    String name = "Install Requirements"
    String workDir

    @Override
    protected void init() {
        workDir = Config.properties["python"]["workDir"]
        assert (workDir != null) || (workDir != '')
    }

    @Override
    protected void execute() {
        Pipeline.sh "pip install -r ${pipeline.env.WORKSPACE}/$workDir/requirements.txt"
    }
}


return Build.class