import sapsan.core.Pipeline
import sapsan.module.Module

class Run extends Module {
    String name = "Run Docker"
    String image

    @Override
    protected void precheck() {
        image = Module.getModule("Build Docker").image
    }

    @Override
    protected void execute() {
        Pipeline.sh "docker run $image"
    }
}

return Run.class