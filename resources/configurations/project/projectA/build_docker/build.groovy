import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Build"

void precheck(Map properties) {
}

void run(Map properties) {
    Log.info("This is a build stage")
}

return this