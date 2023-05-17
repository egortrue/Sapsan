package sapsan.service

import sapsan.core.Context

class Jenkins extends Context {
    static void publish(String wildcard) {
        Context.pipeline.archiveArtifacts(artifacts: wildcard)
    }

    static void download(String url) {
        Context.pipeline.sh "curl $url -o ./artifact.zip"
    }
}
