import groovy.transform.Field
import sapsan.core.Context

@Field String name = "Build Docker"
@Field String image = "my-image"
@Field String dockerfile = "./workspace/Dockerfile.agent"
@Field String targetDir = "."

void precheck() {
}

void execute() {
    Context.pipeline.sh "docker info"
    Context.pipeline.sh "docker build -t $image -f $dockerfile $targetDir"
}

return this