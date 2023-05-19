import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.module.Module

class Run extends Module {
    String name = "Run Python"
    String workDir
    String executable

    @Override
    protected void init() {
        workDir = Config.properties["python"]["workDir"]
        executable = Config.parameters["EXECUTABLE"]

        assert (workDir != null) && (workDir != '')
        assert (executable != null) && (executable != '')

        Job.buildName = "$executable"
    }

    @Override
    protected void execute() {
        Pipeline.sh "python3 ${Context.pipeline.env.WORKSPACE}/$workDir/$executable"
    }
}

return Run.class