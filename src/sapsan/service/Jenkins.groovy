package sapsan.service

import sapsan.core.Context
import sapsan.util.Log

class Jenkins extends Context {

    static {
        Jenkins.metaClass.static.invokeMethod = { String name, args ->
            Log.info("hello from jenkins")
        }
    }


    static void publish(String wildcard) {
        Context.pipeline.archiveArtifacts(artifacts: wildcard)
    }

    static void download(String url) {
        Context.pipeline.sh "curl $url -o ./artifact.zip"
    }
}
