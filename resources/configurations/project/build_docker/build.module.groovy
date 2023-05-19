import groovy.transform.Field
import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Pipeline

@Field String name = "Build Docker"
@Field String image
@Field String dockerfile
@Field String targetDir

void precheck() {
    image = Config.projectProperties["docker"]["image"]
    dockerfile = Config.projectProperties["docker"]["dockerfile"]
    targetDir = Config.projectProperties["docker"]["targetDir"]
}

void execute() {
    Pipeline.sh "docker info"
    Pipeline.sh "ls -al"
    Pipeline.sh "docker build -t $image -f $dockerfile ${Context.pipeline.env.WORKSPACE}/$targetDir"
    Pipeline.sh "exit 1"
}

return this