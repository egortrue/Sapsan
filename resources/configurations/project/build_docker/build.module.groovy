import groovy.transform.Field
import sapsan.core.Context

@Field String name = "Build Docker"

void precheck() {
}

void execute() {
    Context.pipeline.sh "docker info"
}

return this