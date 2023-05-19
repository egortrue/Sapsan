import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Sample Module"

void precheck() {
}

void execute() {
    Log.info("This is empty module!")
}

return this