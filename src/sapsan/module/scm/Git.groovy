package sapsan.module.scm

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.InheritConstructors
import sapsan.core.Pipeline
import sapsan.module.Module

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

    String getInfo() {
        """
        [Git Information]
        Git.url=$url
        Git.branch=$branch
        """.stripIndent()
    }

    void checkout() {
        if (Pipeline.type = Pipeline.Type.MULTIBRANCH)
            script.checkout script.scm
        else {

        }
    }

    void checkout(String url, String branch = 'master') {
        checkout([$class           : 'GitSCM',
                  branches         : [[name: branch]],
                  userRemoteConfigs: [[credentialsId: 'scm-manager', url: url]]
        ])
    }

}
