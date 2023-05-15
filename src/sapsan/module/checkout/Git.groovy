package sapsan.module.checkout

import sapsan.core.Job
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
            branch = Job.branch
        }
    }

    @Override
    void checkProperties() {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            assert properties["url"] != null
            assert properties["branch"] != null
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            assert script.scm != null
        }
    }

    @Override
    def execute() {
        checkProperties()
        Pipeline.stage("Checkout SCM") {
            initProperties()
            script.sh("ls -al")
            Log.info "$url + $branch"
            checkout(url, branch)
        }
    }

    private void checkout(String url, String branch = 'master') {
        script.checkout([$class           : 'GitSCM',
                         branches         : [[name: branch]],
                         userRemoteConfigs: [[credentialsId: 'my-cred', url: url]]
        ])
    }

}
