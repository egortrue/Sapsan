import sapsan.core.Config
import sapsan.core.Pipeline
import sapsan.module.Module

class Run extends Module {
    String name = "Run Docker"
    String image
    String version

    @Override
    protected void init() {
        image = Config.parameters["IMAGE"]
        version = Config.parameters["VERSION"]
    }

    @Override
    protected void execute() {
        Pipeline.sh "docker run $image:$version"
    }
}

return Run.class