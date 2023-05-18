import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Build"

void precheck() {
}

void execute() {
    Log.info("This is a build stage")
    Log.error("Build error!")
}

return this