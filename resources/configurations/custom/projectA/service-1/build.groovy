import sapsan.core.Config
import sapsan.util.Log

void checkProperties(Map properties) {

}

void initProperties(Map properties) {
    Log.var properties
}

void execute() {
    Log.info("This is custom build script. This script located in '${Config.projectDir}'")
}

return this