package sapsan.module

import sapsan.core.Config
import sapsan.core.Job
import sapsan.core.Pipeline

class Checkout extends Module {
    String name = "Checkout"

    String url
    String branch

    @Override
    protected void init() {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            assert Config.properties["checkout"] != null
            assert Config.properties["checkout"]["url"] != null
            assert Config.properties["checkout"]["branch"] != null
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            assert Context.pipeline.scm != null
        }
    }

    @Override
    protected void execute() {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = Config.properties["checkout"]["url"]
            branch = Config.properties["checkout"]["branch"]
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            url = Context.pipeline.scm.userRemoteConfigs[0].url
            branch = Job.branch
        }

        Context.pipeline.checkout([$class           : 'GitSCM',
                                   branches         : [[name: branch]],
                                   userRemoteConfigs: [[credentialsId: 'my-cred', url: url]]])
    }
}
