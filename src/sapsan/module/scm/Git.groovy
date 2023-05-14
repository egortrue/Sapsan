package sapsan.module.scm

import sapsan.core.Context
import sapsan.core.Pipeline
import sapsan.module.Module

@Singleton
class Git extends Context implements Module {
    String url
    String branch

    @Override
    void initProperties(Map properties) {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = properties["url"]
            branch = properties["branch"]
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            url = script.scm.userRemoteConfigs[0].url
            branch = script.scm.userRemoteConfigs[0].branch
        }
    }

    @Override
    void checkProperties(Map properties) {
        assert properties != null
        assert properties["url"] != null
        assert properties["branch"] != null
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
            script.sh("ls -al")
        }
    }

    void checkout(String url, String branch = 'master') {
        checkout([$class           : 'GitSCM',
                  branches         : [[name: branch]],
                  userRemoteConfigs: [[credentialsId: 'scm-manager', url: url]]
        ])
    }

}
