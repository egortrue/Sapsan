package sapsan.module.checkout


import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

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
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            assert properties["url"] != null
            assert properties["branch"] != null
        }
    }

    @Override
    def execute() {
        checkProperties()
        Pipeline.stage("Checkout SCM") {
            script.sh("ls -al")
            Log.info "$url + $branch"
        }
    }

    private void checkout(String url, String branch = 'master') {
        checkout([$class           : 'GitSCM',
                  branches         : [[name: branch]],
                  userRemoteConfigs: [[credentialsId: 'scm-manager', url: url]]
        ])
    }

}
