import sapsan.core.Config
import sapsan.util.Log

String getStageName() {

}

void initProperties(Map properties) {

}

void checkProperties(Map properties) {

}

void execute(Map properties) {
    Log.info("This is custom build script. \
              This script located in '${Config.projectDir}'")
}

return this