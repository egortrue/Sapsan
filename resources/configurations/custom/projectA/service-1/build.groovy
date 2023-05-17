import groovy.transform.Field
import sapsan.core.Config
import sapsan.core.Context
import sapsan.util.Log

@Field String src
@Field String dest

void checkProperties(Map properties) {
    assert properties["src"] != null
    assert properties["dest"] != null
}

void initProperties(Map properties) {
    src = properties["src"]
    dest = properties["dest"]
}

void execute() {
    Log.info("This is custom build script. This script located in '${Config.projectDir}'")
    Context.pipeline.zip(dir: src, zipFile: dest)
}

return this