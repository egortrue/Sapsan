package sapsan.module.scm

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Pipeline
import sapsan.module.Module

class Git extends Module {

    private static Git _instance

    static Git getInstance() {
        if (_instance == null)
            _instance = new Git()
        return _instance
    }

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
        }
    }

    void checkout() {
        Pipeline.stage("Checkout") {
            log "checkout repo from $instance.url"
            if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
                checkout scm
            } else if (Pipeline.type == Pipeline.Type.CLASSIC) {
                checkout([
                        $class                           : 'GitSCM',
                        branches                         : [[name: '*/master']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [[$class: 'CleanCheckout']],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[credentialsId: '<gitCredentials>', url: '<gitRepoURL>']]
                ])
            }
        }
    }
}
