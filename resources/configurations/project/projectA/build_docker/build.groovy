import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Build"

void precheck() {
}

void execute() {
    Log.info("This is a build stage")
}

return this