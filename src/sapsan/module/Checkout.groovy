package sapsan.module

import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline

class Checkout extends Module {

    String url
    String branch

    @Override
    protected void precheck() {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            assert Config.projectProperties["checkout"] != null
            assert Config.projectProperties["checkout"]["url"] != null
            assert Config.projectProperties["checkout"]["branch"] != null
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            assert Context.pipeline.scm != null
        }
    }

    @Override
    protected void execute() {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = Config.projectProperties["checkout"]["url"]
            branch = Config.projectProperties["checkout"]["branch"]
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            url = Context.pipeline.scm.userRemoteConfigs[0].url
            branch = Job.branch
        }

        Context.pipeline.checkout([$class           : 'GitSCM',
                                   branches         : [[name: branch]],
                                   userRemoteConfigs: [[credentialsId: 'my-cred', url: url]]])
    }
}
