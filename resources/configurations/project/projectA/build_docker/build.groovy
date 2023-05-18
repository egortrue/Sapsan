import groovy.transform.Field
import sapsan.core.Config
import sapsan.core.Context
import sapsan.util.Log

@Field String src
@Field String dest

void precheck(Map properties) {
//    assert properties["src"] != null
//    assert properties["dest"] != null
}

void execute(Map properties) {
    src = properties["src"]
    dest = properties["dest"]
    Log.info("This is custom build script. This script located in '${Config.projectDir}'")
    Context.pipeline.zip(dir: src, zipFile: dest)
}

return this