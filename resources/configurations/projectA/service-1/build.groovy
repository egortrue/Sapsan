import groovy.transform.Field
import sapsan.util.Log

@Field String name = "Service-1 Build"

void initProperties(Map properties) {

}

void checkProperties(Map properties) {

}

void call() {
    Log.info("This is custom build script. It's invoked by 'CustomBuild' class")
}

return this