import groovy.transform.Field
import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Pipeline

@Field String name = "Build Docker"
@Field String image
@Field String dockerfile
@Field String target

void precheck() {
    image = Config.projectProperties["docker"]["image"]
    dockerfile = Config.projectProperties["docker"]["dockerfile"]
    target = Config.projectProperties["docker"]["target"]
}

void execute() {
    Pipeline.sh "docker info"
    Pipeline.sh "ls -al"
    Pipeline.sh "docker build -t $image ${Context.pipeline.env.WORKSPACE}/$target"
    Pipeline.sh "exit 1"
}

return this