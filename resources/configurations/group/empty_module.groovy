import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Empty Module"

void precheck() {
}

def execute() {
    Log.info("This is empty module!")
    Log.error("Module error!")
}

return this