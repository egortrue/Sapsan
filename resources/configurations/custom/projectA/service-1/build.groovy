import sapsan.core.Config
import sapsan.util.Log

void initProperties(Map properties) {
    Log.var properties
}

void checkProperties(Map properties) {

}

void execute() {
    Log.info("This is custom build script. This script located in '${Config.projectDir}'")
}

return this