package sapsan.module.scm

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.InheritConstructors
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

@InheritConstructors
class Git extends Module {
    String url
    String branch

    @Override
    @NonCPS
    void initParameters(Map parameters) {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = parameters["url"]
            branch = parameters["branch"]
            assert url != null
            assert branch != null
        } else {
//            url = script.scm.userRemoteConfigs[0].url
        }
    }

    static void checkout(String url = null, String branch = null) {

        if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            script.checkout script.scm
        } else if (Pipeline.type == Pipeline.Type.CLASSIC) {
            checkout([
                    $class           : 'GitSCM',
                    branches         : [[name: '*/master']],
                    userRemoteConfigs: [[credentialsId: '<gitCredentials>', url: '<gitRepoURL>']]
            ])
        }

    }

}
