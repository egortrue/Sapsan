package sapsan.module.checkout

import sapsan.core.Pipeline
import sapsan.module.Module

class Git extends Module {

    String url
    String branch

    @Override
    void initProperties() {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = properties["url"]
            branch = properties["branch"]
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            url = script.scm.userRemoteConfigs[0].url
            branch = script.scm.userRemoteConfigs[0].branch
        }
    }

    @Override
    void checkProperties() {
        assert properties != null
        assert properties["url"] != null
        assert properties["branch"] != null
    }

    @Override
    def execute() {
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
