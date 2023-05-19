import groovy.transform.Field
import sapsan.core.Pipeline

@Field String name = "Build Docker"
@Field String image = "my-image"
@Field String dockerfile = "./workspace/Dockerfile.agent"
@Field String targetDir = "."

void precheck() {
}

void execute() {
    Pipeline.sh "docker info"
    Pipeline.sh "docker build -t $image -f $dockerfile $targetDir"
    Pipeline.sh "exit 1"
}

return this