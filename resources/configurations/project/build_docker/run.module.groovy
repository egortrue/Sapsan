import sapsan.core.Config
import sapsan.core.Pipeline
import sapsan.module.Module

class Run extends Module {
    String name = "Run Docker"
    String image

    @Override
    protected void precheck() {
        image = Config.properties["docker"]["image"]
    }

    @Override
    protected void execute() {
        // Можем обновить имя образа, если оно поменялось во время сборки
        image = Module.getModule("Build Docker").image

        Pipeline.sh "docker run $image"
    }
}

return Run.class