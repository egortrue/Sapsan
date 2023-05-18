import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Useful Stage"
@Field String src
@Field String dest

void precheck(Map properties) {
}

void run(Map properties) {
    Log.info("This is useful!")
}

return this