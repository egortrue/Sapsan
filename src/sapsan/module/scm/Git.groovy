package sapsan.module.scm

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.InheritConstructors
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

@Singleton(lazy = true)
@InheritConstructors
class Git extends Module {

    String url
    String branch

    @Override
    @NonCPS
    void initParameters(Map parameters) {
        Log.info(Pipeline.type)
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = parameters["url"]
            branch = parameters["branch"]
            assert url != null
            assert branch != null
        } else {
//            url = script.scm.userRemoteConfigs[0].url
        }
    }

    String getInfo() {
        """
        [Git Information]
        Git.url=$url
        Git.branch=$branch
        """.stripIndent()
    }

    static void checkout() {
        Pipeline.stage("Checkout SCM") {
            Log.info(getInstance().info)

            if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
                script.checkout script.scm
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
