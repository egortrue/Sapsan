package sapsan.module.scm

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.InheritConstructors
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

@Singleton
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
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {

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
        Log.info(getInstance().info)
        Log.info properties.findAll({ !['class'].contains(it.key) })
//      if (!args.containsKey(property) || args[property] == null)

//        Pipeline.stage("Checkout") {
//            log "checkout repo from $instance.url"
//            if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
//                checkout scm
//            } else if (Pipeline.type == Pipeline.Type.CLASSIC) {
//                checkout([
//                        $class                           : 'GitSCM',
//                        branches                         : [[name: '*/master']],
//                        doGenerateSubmoduleConfigurations: false,
//                        extensions                       : [[$class: 'CleanCheckout']],
//                        submoduleCfg                     : [],
//                        userRemoteConfigs                : [[credentialsId: '<gitCredentials>', url: '<gitRepoURL>']]
//                ])
//            }
//        }
    }
}
