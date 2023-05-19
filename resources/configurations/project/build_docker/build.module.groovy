import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Pipeline
import sapsan.module.Module

class Build extends Module {
    String name = "Build Docker"
    String image
    String dockerfile
    String target
    String version

    @Override
    protected void precheck() {
        image = Config.properties["docker"]["image"]
        dockerfile = Config.properties["docker"]["dockerfile"]
        target = Config.properties["docker"]["target"]
        version = Config.parameters["VERSION"]
    }

    @Override
    protected void execute() {
        Pipeline.sh "docker info"
        Pipeline.sh "ls -al"
        Pipeline.sh "docker build -t $image ${Context.pipeline.env.WORKSPACE}/$target"
    }
}


return Build.class