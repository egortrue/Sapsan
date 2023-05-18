import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Empty Module"

void precheck() {
}

void run() {
    Log.info("This is empty module!")
    Log.error("ERROR")
}

return this