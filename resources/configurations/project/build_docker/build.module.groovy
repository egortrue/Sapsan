import groovy.transform.Field
import sapsan.core.Config
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
    Pipeline.sh "docker build -t $image -f $dockerfile $targetDir"
    Pipeline.sh "exit 1"
}

return this