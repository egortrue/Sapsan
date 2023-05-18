import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Empty Module"

void precheck(Map properties) {
}

void run(Map properties) {
    Log.info("This is empty module!")
    Log.error("ERROR")
}

return this