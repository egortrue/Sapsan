import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Empty Module"

void precheck(Map properties) {
}

void run(Map properties) {
    Log.info("This is a sample stage")
}

return this