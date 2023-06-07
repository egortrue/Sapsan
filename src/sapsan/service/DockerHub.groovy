package sapsan.service

import sapsan.core.Context
import sapsan.core.Pipeline

class DockerHub extends Context {
    static void publish(String user, password) {
        Context.pipeline.wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: password]]]) {
            Pipeline.sh("docker login -u $user -p $password")
        }
    }
}
