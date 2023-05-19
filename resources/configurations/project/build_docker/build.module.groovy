import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Job
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
        dockerfile = Config.properties["docker"]["dockerfile"]
        target = Config.properties["docker"]["target"]
        image = Config.parameters["IMAGE"]
        version = Config.parameters["VERSION"]

        assert image != ''
        assert version != ''

        Job.buildName = "$image:$version"
    }

    @Override
    protected void execute() {
        Pipeline.sh "docker info"
        Pipeline.sh "ls -al"
        Pipeline.sh "docker build -t $image:$version ${Context.pipeline.env.WORKSPACE}/$target"
    }
}


return Build.class