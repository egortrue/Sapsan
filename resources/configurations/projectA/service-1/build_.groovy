import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Build 'Service-1'"

void initProperties(Map properties) {

}

void checkProperties(Map properties) {

}

void execute() {
    Log.info("This is custom build script. It's invoked by 'CustomBuild' class")
}

return this