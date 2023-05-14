import sapsan.util.Log
import groovy.transform.Field

@Field String name = "Service-1 Build"

void call() {
    Log.info("This is custom build script. It's invoked by 'CustomBuild' class")
}

return this