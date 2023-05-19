import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Pipeline
import sapsan.module.Module

class Build extends Module {
    String name = "Run Docker"
    String image
    String dockerfile
    String target
    
    @Override
    protected void precheck() {
        image = Config.projectProperties["docker"]["image"]
        dockerfile = Config.projectProperties["docker"]["dockerfile"]
        target = Config.projectProperties["docker"]["target"]
    }

    @Override
    protected void execute() {
        Pipeline.sh "docker info"
        Pipeline.sh "ls -al"
        Pipeline.sh "docker build -t $image ${Context.pipeline.env.WORKSPACE}/$target"
    }
}


return new Build()