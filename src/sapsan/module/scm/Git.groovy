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

    enum Type {
        CLASSIC,
        MULTIBRANCH
    }

    Type type
    String url
    String branch

    @Override
    @NonCPS
    void initParameters(Map parameters) {
        type = parameters["type"] as Type
        url = parameters["url"]
        branch = parameters["branch"]

        assert type != null
        assert url != null
        assert branch != null
    }

    static void checkout() {
        Pipeline.stage("Checkout") {
            log "checkout repo from $instance.url"
            if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
                checkout scm
            } else {
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
